import requests
from bs4 import BeautifulSoup
import re
import time
def get_list(group_id,page_number):
	header = {
		"Referer":"https://www.douban.com/group/"+str(group_id)+"/discussion?start=50",
		"Host": "www.douban.com",
		"Upgrade-Insecure-Requests": "1",
		"Sec-Fetch-Mode": "navigate",
		"Sec-Fetch-Site": "same-origin",
		"Sec-Fetch-User": "?1",
		"Upgrade-Insecure-Requests": "1",
		"User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36"
	}

	session = requests.Session()
	page = session.get("https://www.douban.com/group/"+str(group_id)+"/discussion?start="+str(page_number),headers = header)
	soup = BeautifulSoup(page.text,"lxml")

	item_list = soup.find_all("a",href=re.compile("https://www.douban.com/group/topic/"))
	item_dict = {}
	for item in item_list:
		#判断是否是关注的item
		if filter(item['href'],item['title']):
			item_dict[item['href']] = item['title']
	return item_dict

def filter(k,v):
	if v.rfind('求租') != -1:
		return False;
	if v.rfind('主卧') != -1:
		return False;
	if v.rfind('次卧') != -1:
		return False;
	if v.rfind('合租') != -1:
		return False;
	if v.rfind('一居')!=-1 and v.rfind("两居") == -1:
		return False;
	return True;

def write_file(context):
	# 打开文件
	file = open('douban.txt','a')
	file.write(context)
	# 写入换行符
	file.write('\n')
	# 刷新到文件中
	file.flush()
	file.close()

if __name__ == '__main__':
	for x in range(0,500):
		print("get page:"+str(x))
		time.sleep(1)
		item_dict = get_list('26926',x)
		## 写入文件
		for k,v in item_dict.items():
			write_file(k+":"+v)

	
