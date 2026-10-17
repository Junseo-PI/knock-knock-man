
#!/usr/bin/python
import RPi.GPIO as GPIO
import time

from time import sleep
from subprocess import call
from datetime import datetime
import sys
import subprocess

imagePath ="/home/pi/KnockMan/cctv_images/"

def photo():
	i = datetime.now()
        now = i.strftime('%Y%m%d-%H%M%S')
        photo_name= 'a.jpg'
        cmd = 'raspistill -t 1  -w 640 -h 480 -o '+imagePath +photo_name

	call([cmd], shell=True)
	print cmd
	return photo_name

def upload_file_to_dropbox():
	cmd ='python supernova_dropbox.py'
	call([cmd],shell=True)


print 'start'
current_state = False


photo()
time.sleep(1)
upload_file_to_dropbox()
time.sleep(1)
