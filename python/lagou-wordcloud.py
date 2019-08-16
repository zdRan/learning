import requests
import re
import json
import jieba
from wordcloud import WordCloud
from bs4 import BeautifulSoup
import matplotlib.pyplot as plt
from scipy.misc import imread
from wordcloud import WordCloud, STOPWORDS, ImageColorGenerator
def get_page():
	for index in range(1,11):
		page_index = "page="+str(index)+"&ka=page-"+str(index)
		print(page_index)
		zhipin_page(page_index)

def zhipin_page(page_index):
	header = {
		"Referer":"https://www.zhipin.com/",
		"Upgrade-Insecure-Requests": "1",
		"User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36"
	}
	session = requests.Session()
	page = session.get("https://www.zhipin.com/c101010100-p100101/?"+page_index,headers = header)
	soup = BeautifulSoup(page.text,"lxml")
	jobs = soup.find_all(href=re.compile("/job_detail/(.)*\\.html"))
	for item in jobs:
		zhipin_job_detail(page_index,item)
		
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

def cut_text():
	text_from_file = open("content.txt",encoding = "utf8").read()
	wordlist_after_jieba = jieba.cut(text_from_file, cut_all = False)
	wl_space_split = " ".join(wordlist_after_jieba)

	my_wordcloud = WordCloud(width=1400, height=1400,font_path=font).generate(wl_space_split)
	plt.imshow(my_wordcloud)
	plt.axis("off")
	plt.show()

def word_cloud():
	font = r'C:\Windows\Fonts\STKAITI.TTF'

	text_from_file = open("content.txt",encoding = "utf8").read()
	jieba.del_word("开发经验")
	jieba.del_word("技术")
	jieba.del_word("经验")
	jieba.del_word("优先")
	jieba.del_word("熟悉")
	jieba.del_word("系统")
	jieba.del_word("工作")
	jieba.del_word("熟练")
	jieba.del_word("使用")
	jieba.del_word("开发")
	jieba.del_word("相关")
	jieba.del_word("任职")
	jieba.del_word("要求")
	jieba.del_word("精通")
	jieba.del_word("经验者")
	jieba.del_word("负责")
	jieba.del_word("进行")
	jieba.del_word("参与")
	jieba.del_word("产品")
	jieba.del_word("业务")
	jieba.del_word("应用")
	jieba.del_word("项目")
	jieba.del_word("具有")
	jieba.del_word("以上")
	jieba.del_word("学历")
	jieba.del_word("能够")
	jieba.del_word("完成")
	jieba.del_word("研发")
	jieba.del_word("岗位")
	jieba.del_word("职责")
	jieba.del_word("岗位职责")
	jieba.del_word("能力")
	jieba.del_word("代码")
	jieba.del_word("编写")
	jieba.del_word("了解")
	jieba.del_word("具备")
	jieba.del_word("需求")
	jieba.del_word("常用")
	jieba.del_word("良好")
	jieba.del_word("平台")
	jieba.del_word("一定")
	jieba.del_word("以及")
	jieba.del_word("实际")
	jieba.del_word("互联网")
	jieba.del_word("公司")
	jieba.del_word("独立")
	jieba.del_word("熟练掌握")
	jieba.del_word("掌握")
	jieba.del_word("专业")
	jieba.del_word("以上学历")
	jieba.del_word("上学")
	jieba.del_word("设计")
	jieba.del_word("服务")
	jieba.del_word("实现")
	jieba.del_word("考虑")
	jieba.del_word("根据")
	jieba.del_word("有良")
	jieba.del_word("先考")
	jieba.del_word("核心")
	jieba.del_word("模块")
	jieba.suggest_freq('微服务', True)
	wordlist_after_jieba = jieba.cut(text_from_file, cut_all = False)
	wl_space_split = " ".join(wordlist_after_jieba)


	bg_pic = imread('bg.jpg')
	my_wordcloud = WordCloud(
            background_color='white',width=2100	, height=1500,font_path=font).generate(wl_space_split)

	image_colors = ImageColorGenerator(bg_pic)

	plt.imshow(my_wordcloud)
	plt.axis("off")
	plt.show()
	my_wordcloud.to_file('show_Chinese.png')

if __name__ == '__main__':
	word_cloud()
