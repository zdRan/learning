from PIL import Image
import numpy as np
import pickle
import requests
import io

'''
使用已知的图片解析出每个数字对应的数组，用于后期图片的解析参考
'''
def analyze_img():
    img_url = 'http://static8.ziroom.com/phoenix/pc/images/price/9f3ec377d708b42e848160072ba83b6as.png'

    response = requests.get(img_url)
    im = Image.open(io.BytesIO(response.content))
    im = im.convert('1')
    num = [1,2,7,5,4,9,6,0,3,8]
    num_dict = {}
    for i in range(10):
        data = im.crop((i*30,0,(i+1)*30,30)).getdata()
        data = np.matrix(data,dtype='int')/255
        num_dict[num[i]] =data

    fp = open('num_dict.num', 'wb')
    pickle.dump(num_dict, fp, protocol=-1)
    fp.close()
    return num_dict

'''
获取指定url的图片，并解析出图片中的数字
'''
def img_ocr(img_url):
    response = requests.get(img_url)
    im = Image.open(io.BytesIO(response.content))
    im = im.convert('1')
    fp = open('num_dict.num', 'rb')
    num_dict = pickle.load(fp)
    result = []
    for i in range(10):
        data = im.crop((i*30,0,(i+1)*30,30)).getdata()
        data = np.matrix(data,dtype='int')/255
        for item in num_dict:
            if (num_dict[item] == data).all():
                result.append(item)
    return result

if __name__ == '__main__':
    #analyze_img()
    url = 'http://static8.ziroom.com/phoenix/pc/images/price/aacd14fbc53a106c7f0f0d667535683as.png'
    print(img_ocr(url))