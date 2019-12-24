from selenium import webdriver

from selenium.webdriver import ActionChains
from selenium.webdriver.support.select import Select
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
import time
import json

def get_name(year,month,day):
	options = webdriver.ChromeOptions()
	options.add_argument('--headless')
	options.add_argument('--disable-gpu')
	options.add_argument('--window-size=1920x1080');
	
	# 获取驱动
	browser = webdriver.Chrome(options = options,executable_path = '/Users/ranzd/workspace/python-workspace/bin/chromedriver')
	# 打开页面
	browser.get('https://www.meimingteng.com/naming/')
	time.sleep(2)
	# 输入数据
	browser.find_element_by_id('ctl00_ContentPlaceHolder1_InputBasicInfo1_tbXing').send_keys('冉');
	time.sleep(1)
	# 点击空白标签关闭弹窗
	ele = browser.find_element_by_id('ctl00_ContentPlaceHolder1_InputBasicInfo1_lbXingMing')
	ActionChains(browser).move_to_element(ele).click(ele).perform()
	time.sleep(1)
	# 下拉框选择
	select_year = Select(browser.find_element_by_id('ctl00_ContentPlaceHolder1_InputBasicInfo1_ddlYear'));
	select_year.select_by_value(year)

	select_month = Select(browser.find_element_by_id('ctl00_ContentPlaceHolder1_InputBasicInfo1_ddlMonth'));
	select_month.select_by_value(month)

	select_day = Select(browser.find_element_by_id('ctl00_ContentPlaceHolder1_InputBasicInfo1_ddlDay'));
	select_day.select_by_value(day)

	btSubmit = browser.find_element_by_id('btSubmit')
	ActionChains(browser).move_to_element(btSubmit).click(btSubmit).perform()
	# 获取姓名
	name_list = browser.find_elements_by_class_name('name')
	len = 0
	line = ''
	for ele in name_list:
		name_score = get_detail(ele,browser,ele.text)
		line = line + "{}:{} ".format(ele.text,round(name_score,1))
		# 滑动，如果当前元素不在可视范围内，则无法触发点击动作
		len+=250
		browser.execute_script("window.scrollTo(0,{})".format(str(len)))
		time.sleep(1)
		pass

	browser.quit()
	return line

def get_detail(ele,browser,name):

	home = browser.current_window_handle
	ActionChains(browser).move_to_element(ele).click(ele).perform()
	browser.switch_to.window(browser.window_handles[1])
	try:
		wenhua_score = browser.find_element_by_xpath('//*[@id="ctl00_ContentPlaceHolder1_ShowNameDetails1_lbNameScore"]/font[2]/b').text
	except Exception as e:
		wenhua_score = '100'

	try:
		wuxing_score = browser.find_element_by_xpath('//*[@id="bdAppSummDiv"]/table[5]/tbody/tr/td/font[1]/b').text
	except Exception as e:
		wuxing_score = '100'

	try:
		shengxiao_score = browser.find_element_by_xpath('//*[@id="bdAppSummDiv"]/table[5]/tbody/tr/td/font[2]/b').text
	except Exception as e:
		shengxiao_score = '100'

	try:
		wuge_score = browser.find_element_by_xpath('//*[@id="bdAppSummDiv"]/table[5]/tbody/tr/td/font[3]/b').text
	except Exception as e:
		wuge_score = '100'
		
	name_score = float(wenhua_score)*0.3+float(wuxing_score)*0.3+float(shengxiao_score)*0.2+float(wuge_score)*0.2
	browser.close()
	browser.switch_to.window(home)
	return name_score


def write_file(context):
	# 打开文件
	file = open('name2.txt','a')
	file.write(context)
	# 写入换行符
	file.write('\n')
	# 刷新到文件中
	file.flush()
	file.close()


def remove_duplicates():
	name_dict = {}
	for line in open('name.txt', 'r'):
		for item in line.strip().split(' '):
			name = item.split(':')
			name_dict[name[0]] = float(name[1])
	filter_name(name_dict)

def filter_name(name_dict):
	filter_key = ['军','民','林','东','胜','灵','轩','瑞','君','发','祥']
	new_name_dict = {}
	for (name,score) in name_dict.items():
		flag = True

		for i in range(len(filter_key)):
			if name.find(filter_key[i]) != -1:
				flag = False
				break;
		if flag:
			new_name_dict[name] = score

	new_name_dict = sorted(new_name_dict.items(), key=lambda d:d[1], reverse = True)
	print(new_name_dict)
	count = 0
	line = ''
	for i in range(len(new_name_dict)):
		line += "{}:{} ".format(new_name_dict[i][0],new_name_dict[i][1]);
		count+=1
		if count == 10:
			write_file(line)
			line = ''
			count = 0



def get_all_name():
	#data = {'1':'31','2':'29','3':'32','4':'31','5':'32','6':'31','7':'32','8':'32','9':'31','10':'32','11':'31','12':'32'}
	data = {'12':'32'}
	for (k,v) in data.items():
		for day in range(1,int(v)):
			print("m:{},d:{}".format(k,str(day)))
			line = get_name('2020',k,str(day))
			time.sleep(2)
			write_file(line)
			pass
		pass

if __name__ == '__main__':
	remove_duplicates()



	
	




