from .craft import CRAFT

import torch
import torch.backends.cudnn as cudnn
import torch.utils.data
from collections import OrderedDict

from .model import Model
from .utils import CTCLabelConverter


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


def init_detector(trained_model, device='cpu'):
    # 需要训练好的模型
    net = CRAFT()  # CRAFT类 实例
    if device == 'cpu':
        net.load_state_dict(copyStateDict(torch.load(trained_model, map_location=device)))
    else:
        net.load_state_dict(copyStateDict(torch.load(trained_model, map_location=device)))  # 使用反序列化的状态dict加载模型的参数字典
        net = torch.nn.DataParallel(net).to(device)  # 多GPU 计算
        cudnn.benchmark = False  # 自动匹配最优卷积 默认关闭

    net.eval()  # 指定 验证模式 不使用BatchNormalization()和Dropout() self.training = False
    return net  # nn.Module


def init_recognizer(input_channel, output_channel, hidden_size, character, \
                    separator_list, dict_list, model_path, device='cpu'):
    converter = CTCLabelConverter(character, separator_list, dict_list)  # CTC module
    num_class = len(converter.character)  # ['[blank]']
    model = Model(input_channel, output_channel, hidden_size, num_class)  # CRNN 模型

    if device == 'cpu':
        state_dict = torch.load(model_path, map_location=device)
        new_state_dict = OrderedDict()
        for key, value in state_dict.items():
            new_key = key[7:]
            new_state_dict[new_key] = value
        model.load_state_dict(new_state_dict)
    else:

        model = torch.nn.DataParallel(model).to(device)  # 多GPU
        model.load_state_dict(torch.load(model_path, map_location=device))  # 加载模型

    return model, converter  # CRNN 模型， CTC模型
