# -*- coding: UTF-8 -*-

import requests
from bs4 import BeautifulSoup
import json
from PIL import Image
import price_img_ocr
import io
import os
import xlwt

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
    price_img_url = "http://www.ziroom.com/detail/info?id={}&house_id=()".format(room_id,house_id)
    resp = requests.get(price_img_url,headers = room_price_header)
    price = json.loads(resp.text)['data']['price']
    price_pos = price[2]
    num_list = price_img_ocr.img_ocr('http:'+price[0])
    result = ""
    for i in price_pos:
        result += str(num_list[i])
    return result

def get_room_info(room_url):
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
        data = get_page(url+'?p='+str(1))
        write_excel(ziroom,data,row)
        row += len(data)

    workbook.save('./苹果园-整租-2居.xls')
    os._exit(0)