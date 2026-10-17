#!/usr/bin/python
# -*- coding: UTF-8
import Tkinter as tk
from subprocess import call
from firebase import firebase
from PIL import ImageTk, Image
import sys,os,time
import tkFont

firebase = firebase.FirebaseApplication(os.getenv('FIREBASE_URL'), None)

class Window:
    def __init__(self, master):
        self.master = master

        pad=3
        self._geom='200x200+0+0'
        master.geometry("{0}x{1}+0+0".format(
            master.winfo_screenwidth()-pad, master.winfo_screenheight()-pad))
        master.bind('<Escape>',self.toggle_geom)

        self.frame = tk.Frame(self.master)
        self.image = tk.PhotoImage(file = '/home/pi/KnockMan/showing_images/doorbell.png')
        self.button1 = tk.Button(self.frame, image = self.image, command = self.new_dialog)
        self.button1.pack()
        self.frame.pack()

        self.v = tk.StringVar()
        self.v.set("↑ 버튼을 눌러주세요 ↑")
        self.label = tk.Label(self.frame, font=("Consolas", 30, "bold"), textvariable = self.v)
        self.label.pack()
        self.frame.pack()

    def toggle_geom(self,event):
        geom=self.master.winfo_geometry()
        print(geom,self._geom)
        self.master.geometry(self._geom)
        self._geom=geom

    def new_dialog(self):
        # cmd ='python supernova_camera.py'
        # call([cmd],shell=True)
        # self.newDialog = tk.Toplevel(self.master)
        # self.app = Dialog(self.newDialog)
        cmd = 'python cctv_send_sms.py'
        call([cmd], shell=True)
        self.newDialog = tk.Toplevel(self.master)
        self.app = Dialog(self.newDialog)

class Dialog:
    def __init__(self,master):
        self.master = master

        pad=3
        self._geom='200x200+0+0'
        master.geometry("{0}x{1}+0+0".format(
            master.winfo_screenwidth()-pad, master.winfo_screenheight()-pad))
        master.bind('<Escape>',self.toggle_geom)

        self.frame = tk.Frame(self.master)

        self.v = tk.StringVar()
        self.v.set("로딩중...")
        self.label = tk.Label(self.frame, textvariable = self.v)
        self.label.pack()
        self.frame.pack()

        self.getMessage = False
        self.master.update()
        self.dialog_event()


    def toggle_geom(self,event):
        geom=self.master.winfo_geometry()
        print(geom,self._geom)
        self.master.geometry(self._geom)
        self._geom=geom


    def dialog_event(self):
        self.v.set("로딩중...")
        self.master.update()
        self.get_response()


    def get_response(self):
        index = 0
        self.view_image("1-1")
        cmd = 'mplayer ~/doorbell.mp3'
        call([cmd], shell=True)
        while index < 600:
            index = index + 1
            self.v.set("로딩중..." + repr(index))
            self.master.update()
            result = firebase.get('/homeCCTV', 'test')
            time.sleep(0.1)
            if result == "\"1-1\"":
                self.view_image("1-1")
                self.getMessage = True

            elif result == "\"1-2\"":
                self.view_image("1-2")
                self.getMessage = True

            elif result == "\"1-3\"":
                self.view_image("1-3")
                self.getMessage = True

            elif result == "\"2-1\"":
                self.view_image("2-1")
                self.getMessage = True

            elif result == "\"2-2\"":
                self.view_image("2-2")
                self.getMessage = True

            elif result == "\"2-3\"":
                self.view_image("2-3")
                self.getMessage = True
            elif result == "\"3-1\"":
                self.view_image("3-1")
                self.getMessage = True

            elif result == "\"3-2\"":
                self.view_image("3-2")
                self.getMessage = True

            elif result == "\"4-1\"":
                self.view_image("4-1")
                self.getMessage = True

            else :
                self.view_image("1-1")
        self.master.destroy()


    def view_image(self, mark):
        img = ImageTk.PhotoImage(Image.open('/home/pi/KnockMan/showing_images/' + mark +'.jpg'))
        self.label.configure(image = img)
        self.label.image = img
        self.master.update()

def main():
    root = tk.Tk()
    app = Window(root)
    root.mainloop()

if __name__ == '__main__':
    main()
