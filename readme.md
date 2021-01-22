## 前端

### 需要的功能：

能够上传集装箱图片

能够显示返回的结果，如：TEMU 604225 1 45G1这样的字符串

## 后端

接收上传的图片然后传给算法

接收算法返回的**TEMU 604225 1 45G1这样的字符串**然后返回给前端显示

跨语言：socket  rabbitMQ（https://www.rabbitmq.com/）

## 算法

### 输入：

.jpg / .png

![image-20210116164152476](https://s3.ax1x.com/2021/01/16/sD6UOO.jpg)

### 输出：

![image-20210116164400275](https://s3.ax1x.com/2021/01/16/sD6ohn.png)

### 环境

cuda ==10.0

cudnn == 7.4.2

```
scikit_image==0.17.2
python_bidi==0.4.2
six==1.15.0
matplotlib==2.2.3
scipy==1.1.0
torch==1.2.0
numba==0.39.0
Shapely==1.7.1
numpy==1.18.5
Flask==1.0.2
tensorflow_gpu==1.15.0
opencv_python==4.4.0.44
pathlib2==2.3.2
torchvision==0.4.0
easyocr==1.2.2
Pillow==8.1.0
plumbum==1.6.9
skimage==0.0
tensorflow==1.15.0
```

### Model

#### EAST

> 这部分使用的是tensorflow

训练：

```
python multigpu_train.py
```

测试：

运行 eval.py

或者在其他文件中使用  eval.main()

> model 文件夹下是训练产生保存下来的模型
>
> training_samples是训练集所在路径
>
> nets 是网络结构
>
> out是eval后的输出

#### **OCR**

OCR使用的是下载下来的easyocr模型

easyocr仓库：https://gitee.com/mirrors/EasyOCR

代码内容可能和 仓库里直接git下来的有区别，因为我修改了

> character文件夹下是要分类出的各种字符
>
> model下有两个模型 craft_mlt_25k.pth是用来检测区域的。latin.pth是用来分类出字符的。
>
> ocr.py是主要使用的文件

**使用：**

```python
import model.OCR.ocr as ocr
    my_ocr = ocr.Ocr()
    my_ocr.get_result(pic_name, allowlist='ABCDEFGHIJKLMNOPQRSTWVUXYZ0123456789',
                                  canvas_size=2000)
```

