# -*- coding: utf-8 -*-

from .detection import get_detector, get_textbox
from .recognition import get_recognizer, get_text
from .utils import group_text_box, get_image_list, calculate_md5, get_paragraph,\
                   download_and_unzip, printProgressBar, diff, reformat_input
# from bidi.algorithm import get_display
import numpy as np
import cv2
import torch
import os
import sys
from PIL import Image
from logging import getLogger

if sys.version_info[0] == 2:
    from io import open
    from six.moves.urllib.request import urlretrieve
    from pathlib2 import Path
else:
    from urllib.request import urlretrieve
    from pathlib import Path

os.environ["LRU_CACHE_CAPACITY"] = "1"
LOGGER = getLogger(__name__)

BASE_PATH = os.path.dirname(__file__)
MODULE_PATH = 'model/OCR/model/'
# detector parameters
DETECTOR_FILENAME = 'craft_mlt_25k.pth'

# recognizer parameters

imgH = 64
input_channel = 1
output_channel = 512
hidden_size = 512

number = '0123456789'
symbol  = '!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ '
en_char = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'


class Ocr(object):

    def __init__(self, detector_model, recognizer_model, converter_model, gpu=True, detector=True, recognizer=True):
    # def __init__(self, gpu=True, detector=True, recognizer=True):

        self.model_storage_directory = MODULE_PATH + '/model'

        if gpu is False:
            self.device = 'cpu'
            LOGGER.warning('Using CPU. Note: This module is much faster with a GPU.')
        elif not torch.cuda.is_available():
            self.device = 'cpu'
            LOGGER.warning('CUDA not available - defaulting to CPU. Note: This module is much faster with a GPU.')
        elif gpu is True:
            self.device = 'cuda'
        else:
            self.device = gpu

        all_char = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz' + \
                   'ÀÁÂÃÄÅÆÇÈÉÊËÍÎÑÒÓÔÕÖØÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿąęĮįıŁłŒœŠšųŽž'
        self.character = number + symbol + all_char
        model_file = 'latin.pth'
        dict_list = {}
        dict_list['en'] = os.path.join(BASE_PATH,'dict','en_char.txt')
        self.lang_char = []

        char_file = os.path.join(BASE_PATH,'character','en_char.txt')
        with open(char_file,'r',encoding='utf-8-sig') as input_file:
            char_list = input_file.read().splitlines()
        self.lang_char += char_list
        self.lang_char = set(self.lang_char).union(set(number+symbol))
        self.lang_char = ''.join(self.lang_char)


        model_path = os.path.join(self.model_storage_directory, model_file)
        corrupt_msg = 'MD5 hash mismatch, possible file corruption'
        detector_path = os.path.join(self.model_storage_directory, DETECTOR_FILENAME)
        # print(detector_path)
        # print(self.device)
        separator_list = {}


        if detector:
            # self.detector = get_detector(detector_path, self.device)
            self.detector = detector_model

        if recognizer:
            # self.recognizer, self.converter = get_recognizer(input_channel, output_channel,\
            #                                              hidden_size, self.character, separator_list,\
            #                                              dict_list, model_path, device = self.device)

            self.recognizer, self.converter = recognizer_model, converter_model

    def detect(self, img, min_size = 20, text_threshold = 0.7, low_text = 0.4,\
               link_threshold = 0.4,canvas_size = 2560, mag_ratio = 1.,\
               slope_ths = 0.1, ycenter_ths = 0.5, height_ths = 0.5,\
               width_ths = 0.5, add_margin = 0.1, reformat=True):
        # 检测 部分

        if reformat:
            img, img_cv_grey = reformat_input(img)
            # 重整图片格式， 拿到他的BGR图和灰阶图

        text_box = get_textbox(self.detector, img, canvas_size, mag_ratio,\
                               text_threshold, link_threshold, low_text,\
                               False, self.device)
        # textbox  4 个框 16 个坐标
        # [274  42 302  42 302  80 274  80]
        # [137  41 245  42 245  81 137  81]
        # [34  44 108  44 108  82  34  82]
        # [134  88 208  88 208 126 134 126]


        # 文本框 ocr_detector
        # detector
        horizontal_list, free_list = group_text_box(text_box, slope_ths,\
                                                    ycenter_ths, height_ths,\
                                                    width_ths, add_margin)

        if min_size:
            horizontal_list = [i for i in horizontal_list if max(i[1]-i[0],i[3]-i[2]) > min_size]
            free_list = [i for i in free_list if max(diff([c[0] for c in i]), diff([c[1] for c in i]))>min_size]

        return horizontal_list, free_list
    # 返回一个矩形文本框和一个自由文本框

    def recognize(self, img_cv_grey, horizontal_list=None, free_list=None,\
                  decoder = 'greedy', beamWidth= 5, batch_size = 1,\
                  workers = 0, allowlist = None, blocklist = None, detail = 1,\
                  paragraph = False,\
                  contrast_ths = 0.1,adjust_contrast = 0.5, filter_ths = 0.003,\
                  reformat=True):
    # 识别 部分
    # 如果不给多个文本框参数，则识别整幅图

        if reformat:
            img, img_cv_grey = reformat_input(img_cv_grey)
            # 图像， 灰度图

        if (horizontal_list==None) and (free_list==None):
            y_max, x_max = img_cv_grey.shape
            ratio = x_max/y_max  # 宽高比 灰度图（格式化过的
            max_width = int(imgH*ratio)
            crop_img = cv2.resize(img_cv_grey, (max_width, imgH), interpolation =  Image.ANTIALIAS)
            image_list = [([[0,0],[x_max,0],[x_max,y_max],[0,y_max]] ,crop_img)]  # 四角坐标 + cv图片
        else:
            image_list, max_width = get_image_list(horizontal_list, free_list, img_cv_grey, model_height = imgH)
            # 多个文本框

        if allowlist:
            ignore_char = ''.join(set(self.character)-set(allowlist))
        elif blocklist:
            ignore_char = ''.join(set(blocklist))
        else:
            ignore_char = ''.join(set(self.character)-set(self.lang_char))

        result = get_text(self.character, imgH, int(max_width), self.recognizer, self.converter, image_list,\
                      ignore_char, decoder, beamWidth, batch_size, contrast_ths, adjust_contrast, filter_ths,\
                      workers, self.device)

        # recognizer，converter


        direction_mode = 'ltr'

        if paragraph:  # 结果组成段落
            result = get_paragraph(result, mode = direction_mode)

        if detail == 0:  # 坐标 置信度 信息
            return [item[1] for item in result]
        else:
            return result
    # 返回识别结果

    def get_result(self, image, decoder = 'greedy', beamWidth= 5, batch_size = 1,\
                 workers = 0, allowlist = None, blocklist = None, detail = 1,\
                 paragraph = False, min_size = 20,\
                 contrast_ths = 0.1,adjust_contrast = 0.5, filter_ths = 0.003,\
                 text_threshold = 0.7, low_text = 0.4, link_threshold = 0.4,\
                 canvas_size = 2560, mag_ratio = 1.,\
                 slope_ths = 0.1, ycenter_ths = 0.5, height_ths = 0.5,\
                 width_ths = 0.5, add_margin = 0.1):
        '''
        Parameters:
        image: file path or numpy-array or a byte stream object
        '''
        img, img_cv_grey = reformat_input(image)  # 根据图片的不同形式， 拿到他的BGR图和灰阶图

        horizontal_list, free_list = self.detect(img, min_size, text_threshold,\
                                                 low_text, link_threshold,\
                                                 canvas_size, mag_ratio,\
                                                 slope_ths, ycenter_ths,\
                                                 height_ths,width_ths,\
                                                 add_margin, False)
        # 返回一个矩形文本框和一个自由文本框
        # ocr 里的选区

        result = self.recognize(img_cv_grey, horizontal_list, free_list,\
                                decoder, beamWidth, batch_size,\
                                workers, allowlist, blocklist, detail,\
                                paragraph, contrast_ths, adjust_contrast,\
                                filter_ths, False)

        return result
