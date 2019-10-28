from selenium import webdriver

from selenium.webdriver import ActionChains
from selenium.webdriver.support.select import Select
import time

def get_name(year,month,day):
	# 获取驱动
	browser = webdriver.Chrome(executable_path = '/Users/ranzd/workspace/python-workspace/bin/chromedriver')
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
	for ele in name_list:
		print(ele.text)
		pass
	time.sleep(5)

if __name__ == '__main__':
	get_name('2020','1','1')