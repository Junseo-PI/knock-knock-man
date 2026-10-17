
import os
from subprocess import call
import subprocess
import time
import sys

path="/home/pi/KnockMan/cctv_images/"
dropbox_uploader_path="/home/pi/dropbox/Dropbox-Uploader/"
upload_file_name=""
result =0
share_file_url = os.getenv('DROPBOX_SHARE_URL', '')

#Share remote file
def dropbox_file_share():
	# global share_file_url

	cmd ="./dropbox_uploader.sh share "+ upload_file_name
    	share_file_url = subprocess.check_output([cmd],shell = True)

def send_sms():
	cmd = "python cctv_send_sms.py " + share_file_url
        call([cmd], shell=True)

def upload_files():
	global upload_file_name


	if not os.path.exists(path):
		print "Path not Exist"
		return

	dir_list = os.listdir(path)
	print dir_list

	for file_name in dir_list:
		if "jpg" in file_name:
			cmd = "./dropbox_uploader.sh upload "+ path + file_name +" "+ file_name
			print cmd
			upload_file_name = file_name
			print "uploaded ", upload_file_name
			os.system(cmd)
			os.remove(path+file_name)


if __name__ == "__main__":
	upload_files()
	time.sleep(1)
#	dropbox_file_share()
	time.sleep(1)
	send_sms()
	sys.exit(0)
