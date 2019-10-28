# -*- coding: UTF-8 -*-

import requests
from bs4 import BeautifulSoup
import json
from PIL import Image
import io
import os
import xlwt
import re
import time
import random
'''
获取目录

需要修改：
Referer
Accept
Host
'''

def get_list(page_num):
    url_header = {
        "Referer":"http://m.778buy.com/614/614482/",
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
        "Host": "m.778buy.com",
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "Pragma": "no-cache"
    }
    page_url = "http://m.778buy.com/614/614482_{}".format(page_num)
    resp = requests.get(page_url,headers = url_header)
    resp.encoding = 'gbk'

    soup = BeautifulSoup(resp.text, "html.parser")
    item_list = soup.find_all(href=re.compile("/614/614482/"))


    for item in item_list:
        print(item.text)
        time.sleep(random.randint(3,5))
        get_context(page_url,item['href'],item.text)

   
    
'''
获取某一页的内容
'''
def get_context(page_url,item_url,name):
    url_header = {
        "Referer":page_url,
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
        "Host": "m.778buy.com",
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36"
    }
    context_url = "http://m.778buy.com/{}".format(item_url)

    resp = requests.get(context_url,headers = url_header)
    resp.encoding = 'gbk'
    soup = BeautifulSoup(resp.text, "html.parser")
    div = soup.find(id = 'nr1')
    fo = open("江流花生.txt", "a",encoding="utf-8")
    fo.write(name+'\n')
    fo.write('\n')
    fo.write( "".join(div.text.split()))
    fo.write('\n')
    print("".join(div.text.split()))
    fo.flush()
    fo.close()



if __name__ == '__main__':
    for page_num in range(33,65,1):
        time.sleep(random.randint(2,5))
        get_list(page_num)

 