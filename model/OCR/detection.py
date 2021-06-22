import torch
import torch.backends.cudnn as cudnn
from torch.autograd import Variable
from PIL import Image
from collections import OrderedDict

import cv2
import numpy as np
from .craft_utils import getDetBoxes, adjustResultCoordinates
from .imgproc import resize_aspect_ratio, normalizeMeanVariance
from .craft import CRAFT

def copyStateDict(state_dict):
    if list(state_dict.keys())[0].startswith("module"):
        start_idx = 1
    else:
        start_idx = 0
    new_state_dict = OrderedDict()
    for k, v in state_dict.items():
        name = ".".join(k.split(".")[start_idx:])
        new_state_dict[name] = v
    return new_state_dict

def test_net(canvas_size, mag_ratio, net, image, text_threshold, link_threshold, low_text, poly, device):
    # resize
    img_resized, target_ratio, size_heatmap = resize_aspect_ratio(image, canvas_size,\
                                                                          interpolation=cv2.INTER_LINEAR, mag_ratio=mag_ratio)
    # 插值方式 interpolation=cv2.INTER_LINEAR 双线性插值
    ratio_h = ratio_w = 1 / target_ratio

    # preprocessing
    x = normalizeMeanVariance(img_resized)  # 归一化方差
    x = torch.from_numpy(x).permute(2, 0, 1)    # [h, w, c] to [c, h, w]  将np.array 转换为torch张量，然后重新排列
    x = Variable(x.unsqueeze(0))                # [c, h, w] to [b, c, h, w]  维度扩充，在0位置加一个维度
    x = x.to(device)  # 将x加载到device

    # forward pass
    with torch.no_grad():
        y, feature = net(x)
        # net: detector模型
    # 上下文管理器，被该语句 wrap 起来的部分将不会track 梯度。

    # make score and link map
    score_text = y[0,:,:,0].cpu().data.numpy()
    score_link = y[0,:,:,1].cpu().data.numpy()

    # Post-processing
    boxes, polys = getDetBoxes(score_text, score_link, text_threshold, link_threshold, low_text, poly)
    # text_threshold: 文本置信度阈值
    # link_threshold: 链接置信度阈值
    # low_text: 文本下限分数

    # coordinate adjustment
    # 调整坐标

    boxes = adjustResultCoordinates(boxes, ratio_w, ratio_h)
    polys = adjustResultCoordinates(polys, ratio_w, ratio_h)
    for k in range(len(polys)):
        if polys[k] is None: polys[k] = boxes[k]  # poly = box

    # for poly in polys:
    #     print(poly)

    return boxes, polys

def get_detector(trained_model, device='cpu'):
    net = CRAFT()
    if device == 'cpu':
        net.load_state_dict(copyStateDict(torch.load(trained_model, map_location=device)))  # 加载模型
    else:
        net.load_state_dict(copyStateDict(torch.load(trained_model, map_location=device)))
        net = torch.nn.DataParallel(net).to(device)
        cudnn.benchmark = False

    net.eval()
    return net

def get_textbox(detector, image, canvas_size, mag_ratio, text_threshold, link_threshold, low_text, poly, device):
    result = []
    bboxes, polys = test_net(canvas_size, mag_ratio, detector, image, text_threshold, link_threshold, low_text, poly, device)

    for i, box in enumerate(polys):
        poly = np.array(box).astype(np.int32).reshape((-1))
        # print(poly)
        result.append(poly)

    # 不识别验证码位
    if len(result) == 4:
        area = [0] * 4
        for i, each in enumerate(result):
            each = each.tolist()
            area[i] = (each[2] - each[0]) * (each[7] - each[1])
        result.pop(np.argmin(area))

    return result
