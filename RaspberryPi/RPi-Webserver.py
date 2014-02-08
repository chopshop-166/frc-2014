#!/usr/bin/python
########################################################################
###
### Simple proof of concept webserver that allows to reading info from
### an arduino over the serial port.  Also send into to the arduino to
### have it turn on/off an LED
###
### Display the data from the arduino:
### 
###     http://localhost:8000/   
### 
### Turn on/off LED:
### 
###     http://localhost:8000/ON 
###     http://localhost:8000/OFF
### 
########################################################################
import SimpleHTTPServer
import StringIO
import SocketServer
import serial 
import re

port = 80
DEBUG = True

########################################################################

class serialcontroller(object):

    def __init__(self):
        print "setting up serialcontroller"
        self.ser = serial.Serial('/dev/ttyACM0', baudrate=115200, timeout=500)
        
    def __del__(self):
        print "closing serial"
        self.ser.close()

    def sendChar(self, c):
	if DEBUG:
		print "write to the serial line this: %s" % c
        self.ser.write(c)

    def readdata(self):
        while True: 
            instring = self.ser.readline()
            
            if DEBUG: 
               print instring 

            return (instring)

########################################################################

class Handler(SimpleHTTPServer.SimpleHTTPRequestHandler):
    def do_GET(self):
       # print "The URL they are looking for is:" + self.path

        self.send_response(200)
        self.send_header('Content-type', 'text/plain')
        self.end_headers()

        arduino.sendChar("1")
        distance = arduino.readdata()
        self.wfile.write(distance)
        
########################################################################

print "Server listening on port %d" % port

arduino = serialcontroller();

httpd = SocketServer.TCPServer(('', port), Handler)
httpd.serve_forever()





