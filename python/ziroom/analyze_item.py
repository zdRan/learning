import requests
from bs4 import BeautifulSoup

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
    for item in room_ul[0].children:
        print("-----------------")
        print(type(item))

if __name__ == '__main__':
    url = 'http://www.ziroom.com/z/vr/62086044.html'
    get_room_info(url)