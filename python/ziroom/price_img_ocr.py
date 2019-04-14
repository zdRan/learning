from PIL import Image
import numpy as np
import pickle
import requests
import io
import os
'''
使用已知的图片解析出每个数字对应的数组，用于后期图片的解析参考
'''
def analyze_img():
    response = requests.get('http://static8.ziroom.com/phoenix/pc/images/price/9bbd4bf71c11e7c8149485d9f1ec5adbs.png')
    im = Image.open(io.BytesIO(response.content))
    im = im.convert('1')
    num = [0,1,4,8,9,3,6,2,7,5]
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
        flag = False
        for item in num_dict:
            if (num_dict[item] == data).all():
                flag = True
                result.append(item)
        # 数字 8 的图片有两种情况
        if not flag:
            result.append(8)
    return result

if __name__ == '__main__':
    #analyze_img()
    url = 'http://static8.ziroom.com/phoenix/pc/images/price/e72ac241b410eac63a652dc1349521fds.png'
    print(img_ocr(url))
    os._exit(0)