# -*- coding: UTF-8 -*-

import requests
from bs4 import BeautifulSoup
import json
from PIL import Image
import numpy as np
import pickle
import io
import os
import xlwt

'''
使用已知的图片解析出每个数字对应的数组，用于后期图片的解析参考
'''
def analyze_img():
    response = requests.get('http://static8.ziroom.com/phoenix/pc/images/price/9bbd4bf71c11e7c8149485d9f1ec5adbs.png')
    im = Image.open(io.BytesIO(response.content))
    im = im.convert('1')
    im.show()
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


'''
获取价格信息
'''
def get_room_price(room_id,house_id):
    room_price_header = {
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
        "Host": "www.ziroom.com",
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "Pragma": "no-cache"
    }
    price_img_url = "http://www.ziroom.com/detail/info?id={}&house_id={}".format(room_id,house_id)
    resp = requests.get(price_img_url,headers = room_price_header)
    price = json.loads(resp.text)['data']['price']
    price_pos = price[2]
    num_list = img_ocr('http:'+price[0])
    result = ""
    for i in price_pos:
        result += str(num_list[i])
    return result

def get_room_info(room_url):
    print("正在请求：{}".format(room_url))
    room_req_header = {
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
        "Host": "www.ziroom.com",
        "Referer": "http://www.ziroom.com/z/nl/z2.html?qwd=",
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "Pragma": "no-cache"
    }
    resp = requests.get(room_url,headers = room_req_header)
    soup = BeautifulSoup(resp.text, "html.parser")
    room_ul = soup.find_all("ul",class_="detail_room")
    room_info = {}
    i = 0
    # 获取基本信息
    for item in room_ul[0].children:
        if item.name == 'li':
            if i != 4:
                info = item.contents[1].string.replace(" ", "").replace('\n','').split('：')
                room_info[info[0]] = info[1]
            else:
                room_info['交通'] = item.find_all('span')[0].contents[0].string.replace(" ", "").replace('\n','')
            i+=1
    # 格式化面积
    room_info['面积'] = room_info['面积'].replace('㎡(建筑面积)','')
    # 获取价格信息
    price = get_room_price(soup.find('input',id = 'room_id')['value'],soup.find('input',id = 'house_id')['value'])
    room_info['季付'] = price
    # 获取房屋名称-卧室编号
    room_name_div = soup.find('div',class_='room_name')
    room_info['名称'] = room_name_div.contents[1].contents[0].string.replace(" ", "").replace('\n','')
    # 获取卧室布局图
    room_img = soup.find_all('img',class_='loadImgError')
    room_info['布局图'] = 'http:'+room_img[len(room_img)-1]['src']
    return room_info

def get_page(page_url):
    print("正在请求：{}".format(page_url))
    room_req_header = {
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
        "Host": "www.ziroom.com",
        "Referer": "http://www.ziroom.com/z/nl/z2.html?qwd=",
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "Pragma": "no-cache"
    }
    resp = requests.get(page_url,headers = room_req_header)
    soup = BeautifulSoup(resp.text,"html.parser")
    room_url_list = soup.find_all('a',class_= 't1')
    room_info_list = []
    for item in room_url_list:
        room_info = get_room_info('http:'+item['href'])
        room_info['URL'] = 'http:'+item['href']
        room_info_list.append(room_info)
    return room_info_list

def write_excel(sheet,data,row):
    for item in data:
        for v,col in zip(item.values(),range(len(item))):
            sheet.write(row,col,v)
        row+=1


if __name__ == '__main__':
    # 在自如网页面上选择好筛选条件，替换掉这个 url 即可
    url = 'http://www.ziroom.com/z/nl/z1-r0TO5000-s1%E5%8F%B7%E7%BA%BF-t%E8%8B%B9%E6%9E%9C%E5%9B%AD.html'
    room_req_header = {
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
        "Host": "www.ziroom.com",
        "Referer": "http://www.ziroom.com/z/nl/z2.html?qwd=",
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "Pragma": "no-cache"
    }
    resp = requests.get(url,headers = room_req_header)
    soup = BeautifulSoup(resp.text,"html.parser")
    page_total = int(soup.find('span',class_='marL20').previous_sibling.string[1:-1])

    workbook = xlwt.Workbook()
    ziroom = workbook.add_sheet('ziroom')
    row = 0
    for page_num in range(page_total):
        data = get_page(url+'?p='+str(page_num))
        write_excel(ziroom,data,row)
        row += len(data)

    workbook.save('./苹果园-整租-2居.xls')
    os._exit(0)