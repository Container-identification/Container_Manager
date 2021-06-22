import os
import other.guolv22
from time import *
import model.EAST.eval as eval # 模型1
from numba import cuda
import tensorflow as tf


tf.app.flags.DEFINE_string('test_data_path', 'test_data', '') # 检测图片地址
tf.app.flags.DEFINE_string('gpu_list', '0', '')
tf.app.flags.DEFINE_string('checkpoint_path', 'model//EAST//model', '')
tf.app.flags.DEFINE_string('output_dir', 'model//EAST//out', '')
tf.app.flags.DEFINE_bool('no_write_images', False, 'do not write images')
# （变量名称，默认值，用法描述）


def east_pred(file_name, detector, recognizer, converter):
    time1 = time()
    # shutil.rmtree('model//EAST//out')
    # 递归删除目录下所有文件
    # os.mkdir('model//EAST//out')

    # east输出
    # 裁取集装箱号图片到out文件夹中
    time_eval = time()

    print("1")
    # tf.reset_default_graph()
    # eval.main(file_name)
    eval.main()

    from model.OCR.init import init_detector, init_recognizer
    time_dec = time()

    detector = init_detector(r'model/OCR/model//model\craft_mlt_25k.pth', 'cuda')

    print("detector耗时：" + str(time() - time_dec))

    time_reg = time()

    character = '0123456789!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk' \
                'lmnopqrstuvwxyzÀÁÂÃÄÅÆÇÈÉÊËÍÎÑÒÓÔÕÖØÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿąęĮįıŁłŒœŠšųŽž'
    separator_list = {}
    dict_list = {'en': 'D:\\集装箱\\集装箱\\model\\OCR\\dict\\en_char.txt'}
    model_path = r'model/OCR/model//model\latin.pth'
    recognizer, converter = init_recognizer(1, 512, 512, character, separator_list, dict_list, model_path, 'cuda')
    print("recognizer耗时：" + str(time() - time_reg))

    print("1")
    print("eval耗时：" + str(time() - time_eval))

    # ocr
    east_out_path = 'model//EAST//out'
    cuda.select_device(0)
    cuda.close()

    # time_ocr = time()
    import model.OCR.ocr as ocr # 模型2
    my_ocr = ocr.Ocr(detector_model=detector, recognizer_model=recognizer, converter_model=converter) # 调用OCR
    # 识别out文件夹中的图片

    # print("ocr耗时：" + str(time() - time_ocr))

    # time_file = time()

    picture_list = os.listdir(east_out_path)
    result = "" # 输出

    for a in picture_list: # 对每张图片进行ocr识别
        if a.endswith('.jpg') or a.endswith('.png'):
            filter_result = a + '\n' + str(
                my_ocr.get_result(east_out_path + '//' + a, allowlist='ABCDEFGHIJKLMNOPQRSTWVUXYZ0123456789',
                                  canvas_size=2000)) + '\n'
            result = result + filter_result
            print(filter_result)

    fh = open('other/result_nofilter.txt', 'w', encoding='utf-8')
    fh.write(str(result))
    order_dict = {}
    fh.close()

    # print("file耗时：" + str(time() - time_file))

    # time_filter = time()

    other.guolv22.chuMain()

    # print("filter耗时：" + str(time() - time_filter))

    time2 = time()
    print("耗时间：" + str(time2 - time1))


if __name__ == '__main__':

    east_pred('90.jpg', detector=detector, recognizer=recognizer, converter=converter)
    # eval.main()