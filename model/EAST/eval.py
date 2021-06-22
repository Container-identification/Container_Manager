import cv2
import time
import math
import os
import numpy as np
import tensorflow as tf
from math import *
from PIL import Image

from ..EAST import locality_aware_nms as nms_locality

# import lanms

# tf.app.flags.DEFINE_string('test_data_path', 'test', '')
# tf.app.flags.DEFINE_string('gpu_list', '0', '')
# tf.app.flags.DEFINE_string('checkpoint_path', 'model', '')
# tf.app.flags.DEFINE_string('output_dir', 'out', '')
# tf.app.flags.DEFINE_bool('no_write_images', False, 'do not write images')

from ..EAST import model as model1
from ..EAST.icdar import restore_rectangle

FLAGS = tf.app.flags.FLAGS


def get_images():
    '''
    find image files in test data path
    :return: list of files found
    '''
    files = []
    exts = ['jpg', 'png', 'jpeg', 'JPG']
    for parent, dirnames, filenames in os.walk(FLAGS.test_data_path):
        for filename in filenames:
            for ext in exts:
                if filename.endswith(ext):
                    files.append(os.path.join(parent, filename))
                    break
    print('Find {} images'.format(len(files)))
    return files
    # 从FLAGS.test_data_path整个文件夹中找图片


def resize_image(im, max_side_len=1000):
    '''
    resize image to a size multiple of 32 which is required by the network
    :param im: the resized image
    :param max_side_len: limit of max image size to avoid out of memory in gpu
    :return: the resized image and the resize ratio
    '''
    h, w, _ = im.shape

    resize_w = w
    resize_h = h

    # limit the max side
    if max(resize_h, resize_w) > max_side_len:
        ratio = float(max_side_len) / resize_h if resize_h > resize_w else float(max_side_len) / resize_w
    else:
        ratio = 1.
    resize_h = int(resize_h * ratio)
    resize_w = int(resize_w * ratio)

    resize_h = resize_h if resize_h % 32 == 0 else (resize_h // 32 - 1) * 32
    resize_w = resize_w if resize_w % 32 == 0 else (resize_w // 32 - 1) * 32
    resize_h = max(32, resize_h)
    resize_w = max(32, resize_w)
    im = cv2.resize(im, (int(resize_w), int(resize_h)))
    # 灰度化
    # im = cv2.cvtColor(im, cv2.COLOR_BGR2GRAY)
    ratio_h = resize_h / float(h)
    ratio_w = resize_w / float(w)

    return im, (ratio_h, ratio_w)


# def resize_image(im, max_side_len=2400):
#     '''
#     resize image to a size multiple of 32 which is required by the network
#     :param im: the resized image
#     :param max_side_len: limit of max image size to avoid out of memory in gpu
#     :return: the resized image and the resize ratio
#     '''
#
#     height, width = im.shape[0], im.shape[1]
#     # 设置新的图片分辨率框架
#     width_new = 640
#     height_new = 600
#     # 判断图片的长宽比率
#     if width / height >= width_new / height_new:
#         im = cv2.resize(im, (width_new, int(height * width_new / width)))
#     else:
#         im = cv2.resize(im, (int(width * height_new / height), height_new))
#
#     resize_w = width
#     resize_h = height
#
#     # limit the max side
#     if max(resize_h, resize_w) > max_side_len:
#         ratio = float(max_side_len) / resize_h if resize_h > resize_w else float(max_side_len) / resize_w
#     else:
#         ratio = 1.
#     resize_h = int(resize_h * ratio)
#     resize_w = int(resize_w * ratio)
#
#     resize_h = resize_h if resize_h % 32 == 0 else (resize_h // 32 - 1) * 32
#     resize_w = resize_w if resize_w % 32 == 0 else (resize_w // 32 - 1) * 32
#     resize_h = max(32, resize_h)
#     resize_w = max(32, resize_w)
#     im = cv2.resize(im, (int(resize_w), int(resize_h)))
#
#     ratio_h = resize_h / float(height)
#     ratio_w = resize_w / float(width)
#
#     return im, (ratio_h, ratio_w)


def detect(score_map, geo_map, timer, score_map_thresh=0.8, box_thresh=0.1, nms_thres=0.2):
    '''
    restore text boxes from score map and geo map
    :param score_map:
    :param geo_map:
    :param timer:
    :param score_map_thresh: threshhold for score map
    :param box_thresh: threshhold for boxes
    :param nms_thres: threshold for nms
    :return:
    '''
    if len(score_map.shape) == 4:
        score_map = score_map[0, :, :, 0]
        geo_map = geo_map[0, :, :, ]
    # filter the score map
    xy_text = np.argwhere(score_map > score_map_thresh)
    # sort the text boxes via the y axis
    xy_text = xy_text[np.argsort(xy_text[:, 0])]
    # restore
    start = time.time()
    text_box_restored = restore_rectangle(xy_text[:, ::-1] * 4, geo_map[xy_text[:, 0], xy_text[:, 1], :])  # N*4*2
    print('{} text boxes before nms'.format(text_box_restored.shape[0]))
    # 输出找到几个文本框
    boxes = np.zeros((text_box_restored.shape[0], 9), dtype=np.float32)
    boxes[:, :8] = text_box_restored.reshape((-1, 8))
    boxes[:, 8] = score_map[xy_text[:, 0], xy_text[:, 1]]
    timer['restore'] = time.time() - start
    # nms part
    start = time.time()
    # boxes = nms_locality.nms_locality(boxes.astype(np.float64), nms_thres)
    # boxes = lanms.merge_quadrangle_n9(boxes.astype('float32'), nms_thres)
    boxes = nms_locality.nms_locality(boxes.astype(np.float64), nms_thres)
    timer['nms'] = time.time() - start

    if boxes.shape[0] == 0:
        return None, timer

    # here we filter some low score boxes by the average score map, this is different from the orginal paper
    for i, box in enumerate(boxes):
        mask = np.zeros_like(score_map, dtype=np.uint8)
        cv2.fillPoly(mask, box[:8].reshape((-1, 4, 2)).astype(np.int32) // 4, 1)
        boxes[i, 8] = cv2.mean(score_map, mask)[0]
    boxes = boxes[boxes[:, 8] > box_thresh]

    return boxes, timer


def sort_poly(p): # 多边形排序？
    min_axis = np.argmin(np.sum(p, axis=1))
    p = p[[min_axis, (min_axis + 1) % 4, (min_axis + 2) % 4, (min_axis + 3) % 4]]
    if abs(p[0, 0] - p[1, 0]) > abs(p[0, 1] - p[1, 1]):
        return p
    else:
        return p[[0, 3, 2, 1]]


# def main(file_name):
def main():
    import os
    os.environ['CUDA_VISIBLE_DEVICES'] = FLAGS.gpu_list

    # try:
    #     # os.makedirs(FLAGS.output_dir)
    #     # 新建输出文件夹
    # except OSError as e:
    #     if e.errno != 17:
    #         raise

    with tf.get_default_graph().as_default():
        input_images = tf.placeholder(tf.float32, shape=[None, None, None, 3], name='input_images') # 分配内存
        global_step = tf.get_variable('global_step', [], initializer=tf.constant_initializer(0), trainable=False) # 设置变量

        f_score, f_geometry = model1.model(input_images, is_training=False)
        # 获得文本框的预测分数和几何图形

        variable_averages = tf.train.ExponentialMovingAverage(0.997, global_step)
        saver = tf.train.Saver(variable_averages.variables_to_restore())
        # 储存模型模块

        with tf.Session(config=tf.ConfigProto(allow_soft_placement=True)) as sess: # 使用GPU和自由分配
            ckpt_state = tf.train.get_checkpoint_state(FLAGS.checkpoint_path)
            model_path = os.path.join(FLAGS.checkpoint_path, os.path.basename(ckpt_state.model_checkpoint_path))
            print('Restore from {}'.format(model_path))
            # 输出读取模型路径
            saver.restore(sess, model_path)
            # 加载/读取模型

            im_fn_list = get_images()
            # im_fn_list = 'test_data//' + file_name
            # 图片列表
            # 输出 'Find {} images'
            # 处理找到的图片
            # while True:
            for im_fn in im_fn_list:
            #     im_fn = im_fn_list
                im = Image.open(im_fn).convert('RGB')
                im = np.array(im)[:, :, ::-1]
                # im = cv2.fastNlMeansDenoisingColored(im, None, 10, 10, 7, 21)
                # im = cv2.imread(im_fn)[:, :, ::-1]

                start_time = time.time()
                im_resized, (ratio_h, ratio_w) = resize_image(im)

                timer = {'net': 0, 'restore': 0, 'nms': 0}
                start = time.time()
                score, geometry = sess.run([f_score, f_geometry], feed_dict={input_images: [im_resized]}) # 启动神经网络图计算
                timer['net'] = time.time() - start

                boxes, timer = detect(score_map=score, geo_map=geometry, timer=timer) # 检测 返回文本框
                # 输出找到几个文本框 {} text boxes before nms
                print('{} : net {:.0f}ms, restore {:.0f}ms, nms {:.0f}ms'.format(
                    im_fn, timer['net'] * 1000, timer['restore'] * 1000, timer['nms'] * 1000))
                # 输出图片名， 网络时间， 读取时间，非极大值抑制时间

                if boxes is not None: # 如果文本框存在
                    boxes = boxes[:, :8].reshape((-1, 4, 2))
                    boxes[:, :, 0] /= ratio_w
                    boxes[:, :, 1] /= ratio_h

                duration = time.time() - start_time
                print('[timing] {}'.format(duration))
                # 输出检测时间
                # save to file

                if boxes is not None:
                    # res_file = os.path.join(
                    #     FLAGS.output_dir,
                    #     '{}.txt'.format(
                    #         os.path.basename(im_fn).split('.')[0]))
                    # with open(res_file, 'w',encoding='utf-8') as f:
                    dict_value = {}
                    if(len(boxes)>1): # 如果检测到多个文本框
                        max_value = 0
                        for box in boxes:
                            value = (box[0,0]+box[1,0]+box[2,0]+box[3,0])/(box[0,1]+box[1,1]+box[2,1]+box[3,1])
                            if(value>max_value):
                                max_value = value
                                max_box = box
                        boxes = [box] # ？？？？

                    for index, box in enumerate(boxes):
                        # to avoid submitting errors
                        box = sort_poly(box.astype(np.int32))
                        if np.linalg.norm(box[0] - box[1]) < 5 or np.linalg.norm(box[3] - box[0]) < 5:
                            continue
                        # f.write('{},{},{},{},{},{},{},{}\r\n'.format(
                        #     box[0, 0], box[0, 1], box[1, 0], box[1, 1], box[2, 0], box[2, 1], box[3, 0], box[3, 1],
                        # ))
                        # print("所有的Bounding-box" + '{},{},{},{},{},{},{},{}\r\n'.format(
                        #     box[0, 0], box[0, 1], box[1, 0], box[1, 1], box[2, 0], box[2, 1], box[3, 0], box[3, 1],
                        # ))
                        # img_path_line = os.path.join('out2', 'line_'+os.path.basename(im_fn))
                        # 带框
                        # cv2.polylines(im[:, :, ::-1], [box.astype(np.int32).reshape((-1, 1, 2))], True, color=(255, 255, 0), thickness=1)
                        # cv2.imwrite(img_path_line,im)
                        # cv2.imshow(im_fn, im)
                        # cv2.waitKey()
                        highest = min(box[0,1],box[1,1])
                        lowest = max(box[2,1],box[3,1])
                        zuoest = min(box[0,0],box[3,0])
                        youest = max(box[1,0],box[2,0])

                        margin = int((box[1, 0] - box[0, 0]) / 10)

                        shang = highest - margin
                        xia = lowest
                        zuo = zuoest - int(margin / 2)
                        you = youest + int(margin / 2)
                        if(shang<0):
                            shang = 0
                        if(xia>im.shape[0]):
                            xia = im.shape[0]
                        if(zuo<0):
                            zuo = 0
                        if(you>im.shape[1]):
                            you = im.shape[1]
                        # im = im[box[0, 1] - margin:box[2, 1], box[0, 0] - int(margin / 2):box[1, 0] + int(margin / 2),
                        #      ::]
                        im = im[shang:xia,zuo:you,::]
                        #cv2.imshow('this',im)
                        #cv2.waitKey()

                if not FLAGS.no_write_images:
                    img_path = os.path.join(FLAGS.output_dir, os.path.basename(im_fn))
                    img_path_huidu = img_path_add = os.path.join(FLAGS.output_dir, os.path.basename(im_fn))
                    print(img_path)
                    # 输出图片路径
                    # 输出灰度化
                    # 输出结束
                    huidu = cv2.cvtColor(im[:, :, ::], cv2.COLOR_BGR2GRAY)
                    cv2.imwrite(img_path_huidu, huidu)
                    # 保存灰度化图像到out
                    # 有待优化 处理后的图片
                    # EAST模型 角度调整
                    # 针对 模糊 雨天 污渍 的 干扰
                # break


if __name__ == '__main__':
    tf.app.run()

# 输出运行时间
