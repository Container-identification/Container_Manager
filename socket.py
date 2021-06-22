# encoding: utf-8

import socket
import threading
import json
import time
import re
from remake import east_pred
from model.OCR.init import init_detector, init_recognizer

def main():

    serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # host = socket.gethostname()
    host = '127.0.0.1'
    port = 55533
    serversocket.bind((host, port))
    serversocket.listen(5)
    myaddr = serversocket.getsockname()
    print("服务器地址:%s" % str(myaddr))
    while True:
        clientsocket, addr = serversocket.accept()
        print("连接地址:%s" % str(addr))
        try:
            t = ServerThreading(clientsocket)
            t.start()

        except Exception as identifier:
            print(identifier)


class ServerThreading(threading.Thread):

    def __init__(self, clientsocket, recvsize=1024 * 1024, encoding="utf-8"):
        threading.Thread.__init__(self)
        self._socket = clientsocket
        self._recvsize = recvsize
        self._encoding = encoding

    def run(self):
        print("开启线程.....")

        while True:
            try:
                msg = ''
                while True:
                    rec = self._socket.recv(self._recvsize)
                    msg += rec.decode(self._encoding)

                    if msg.strip().endswith('over'):
                        msg = re.match(r'\{[^\}]+\}', msg).group()
                        # 文件名用{}框起来
                        break
                    if msg.strip().endswith('end'):
                        self._socket.close()
                        break
                if msg.strip().endswith('end'):
                    break

                print(msg)
                msg_dict = json.loads(msg)

                file_name = msg_dict['file_name']
                print(file_name)



                # east_pred(file_name, detector, recognizer, converter)


                # pred =
                # print(pred)

                pred = '1'
                sendmsg = json.dumps(pred)
                self._socket.send(sendmsg.encode(self._encoding) + bytes('\n', encoding=self._encoding))


            except Exception as identifier:
                self._socket.send("500".encode(self._encoding))
                print(identifier)

        print("任务结束.....")


if __name__ == "__main__":
    main()
