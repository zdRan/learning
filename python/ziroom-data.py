import requests
import re
import json
import jieba
from wordcloud import WordCloud
from bs4 import BeautifulSoup
import matplotlib.pyplot as plt
from scipy.misc import imread
from wordcloud import WordCloud, STOPWORDS, ImageColorGenerator

def ziroom_page():
	header = {
		"Referer":"http://www.ziroom.com/",
		"Host": "www.ziroom.com",
		"Upgrade-Insecure-Requests": "1",
		"User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36"
	}
	session = requests.Session()
	page = session.get("http://www.ziroom.com/z/nl/z2.html?qwd=",headers = header)
	soup = BeautifulSoup(page.text,"lxml")
	div_list = soup.find_all("p",class_ = "price")
	for div in div_list:
		print(str(div))

def zhipin_job_detail(page_index,item):
	print(item["href"])
	header = {
		"Referer":"https://www.zhipin.com/c101010100-p100101/?"+page_index,
		"Upgrade-Insecure-Requests": "1",
		"User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36",
		"x-requested-with":"XMLHttpRequest"
	}
	session = requests.Session()

	json_str = session.get("https://www.zhipin.com/view/job/card.json?jid="+item["data-jid"]+"&lid="+item["data-lid"],headers = header)
	detail = json.loads(json_str.text)
	soup = BeautifulSoup(detail["html"],"lxml")
	content = soup.find("div",class_="detail-bottom-text")
	file = open("content.txt","a+",encoding = "utf8")
	file.write(content.text.upper())
	file.flush()


if __name__ == '__main__':
	ziroom_page()
