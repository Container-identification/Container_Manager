import sys
import os
import cv2
import string
import other.guolv22
from time import *
from model.EAST import eval as eval
from numba import cuda
import tensorflow as tf
import shutil
import numpy as np


tf.app.flags.DEFINE_string('test_data_path', 'test_data', '') # 检测图片地址
tf.app.flags.DEFINE_string('gpu_list', '0', '')
tf.app.flags.DEFINE_string('checkpoint_path', 'model//EAST//model', '')
tf.app.flags.DEFINE_string('output_dir', 'model//EAST//out', '')
tf.app.flags.DEFINE_bool('no_write_images', False, 'do not write images')

if __name__ == '__main__':
    # shutil.rmtree('model//EAST//out')
    # os.mkdir('model//EAST//out')

    # 运行EAST模型
    # eval.main()

    east_out_path = 'model//EAST//out'
    cuda.select_device(0)
    cuda.close()

    # ocr
    from model.OCR.init import init_detector, init_recognizer
    # 初始化easyocr所需的两个模型

    character = '0123456789!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk' \
                'lmnopqrstuvwxyzÀÁÂÃÄÅÆÇÈÉÊËÍÎÑÒÓÔÕÖØÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿąęĮįıŁłŒœŠšųŽž'
    separator_list = {}
    dict_list = {'en': 'D:\\集装箱\\集装箱\\model\\OCR\\dict\\en_char.txt'}
    model_path = r'model/OCR/model//model\latin.pth'

    detector = init_detector(r'model/OCR/model//model\craft_mlt_25k.pth', device='cuda')  # nn.Module
    recognizer, converter = init_recognizer(1, 512, 512, character, separator_list, dict_list, model_path, device='cuda')


    import model.OCR.ocr as ocr
    # 初始化ocr 三个模型
    # 分别是 CRAFT, CRNN , CTC
    my_ocr = ocr.Ocr(detector_model=detector, recognizer_model=recognizer, converter_model=converter)  # 调用OCR

    picture_list = os.listdir(east_out_path)
    result = ""
    for a in picture_list:  # 对每张图片进行ocr识别
        if a.endswith('.jpg') or a.endswith('.png'):

            ocr_result = my_ocr.get_result(east_out_path + '//' + a, allowlist='ABCDEFGHIJKLMNOPQRSTWVUXYZ0123456789',
                                  canvas_size=2000, detail=1)

            # 画CRAFT检测区域
            img = cv2.imread("model/EAST/out/{}".format(a))
            for each in ocr_result:
                coord = np.array(each[0], np.int32)
                img = cv2.polylines(img, [coord], True, (0, 0, 255), 2)
                cv2.imwrite("model/OCR/out/{}".format(a), img)

            filter_result = a + '\n' + (str([result[1] for result in ocr_result]) + '\n')


            result = result + filter_result
            print(filter_result)

    fh = open('other/result_nofilter.txt', 'w+', encoding='utf-8')
    fh.write(str(result))
    order_dict = {}
    fh.close()

    # other.guolv22.chuMain()  # 计算验证位 + 格式过滤（有待改进
