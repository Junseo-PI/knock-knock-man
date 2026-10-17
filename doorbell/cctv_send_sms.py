# -*- coding: utf8 -*-

"""
 vi:set et ts=4 fenc=utf8:
 Copyright (C) 2008-2010 D&SOFT
 http://open.coolsms.co.kr
"""
import os
import sys
import coolsms

def main():
	api_key = os.getenv('COOLSMS_API_KEY')
	api_secret = os.getenv('COOLSMS_API_SECRET')
	to = os.getenv('OWNER_PHONE')
	sender = os.getenv('SENDER_PHONE')
	message = "방문자가 벨을 눌렀습니다. 앱을 켜 방문자를 확인하고, 메세지를 보내세요!"
	cool = coolsms.rest(api_key, api_secret)
	status = cool.send(to,message,sender)
	print status

if __name__ == "__main__":
    global share_file_url

    main()
    sys.exit(0)
