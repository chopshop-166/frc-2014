#!/usr/bin/python
########################################################################
import SimpleHTTPServer
import StringIO
import SocketServer
import serial 
import logging
import time

port = 80
DEBUG = True

########################################################################

class serialcontroller(object):
    
    def __init__(self):
        logging.info("setting up serialcontroller")
        connected = False

        while (not connected):
            for i in range(0, 3):
                p = "/dev/ttyACM%d" % i
                logging.info("Connecting to %s", p)
                try:
                    self.ser = serial.Serial(p, baudrate=115200, timeout=100)
                    connected = True
                    break
                except serial.SerialTimeoutException:
                    logging.warn("ack -- timeout can't connect to serial %s", p)
                    continue
                except serial.SerialException:
                    logging.warn("ack -- general can't connect to serial %s", p)
                    continue
                finally:
                    logging.warn("dealing with %s", p)
            time.sleep(2)

    def __del__(self):
        logging.info("closing serial")
        self.ser.close()

    def sendChar(self, c):
	if DEBUG:
		logging.debug("write to the serial line this: %s" % c)
        try:
            self.ser.write(c)
        except serial.SerialTimeoutException:
            logging.warning("ack - timeout couldn't write")
            connected = False
        except serial.SerialException:
            logging.warning("ack - couldn't write")
            connected = False

        if DEBUG:
            logging.info("done writing")
            
    def readdata(self):
        while True: 

            logging.info("reading...")

            instring = self.ser.readline()
            
            if DEBUG: 
               logging.debug(instring)

            return (instring)

########################################################################

class Handler(SimpleHTTPServer.SimpleHTTPRequestHandler):
    def do_GET(self):
        if DEBUG:
            logging.debug("The URL they are looking for is: %s", self.path)

        self.send_response(200)
        self.send_header('Content-type', 'text/plain')
        self.end_headers()

        arduino.sendChar("1")
        distance = arduino.readdata().strip()
        print "distance: ", distance
        self.wfile.write(distance)
        
########################################################################


logging.basicConfig(filename='/home/pi/sensor.log',level=logging.DEBUG)

logging.info("Initializing serial line...")

arduino = serialcontroller();

logging.info("Setting up httpd on %d" , port)

httpd = SocketServer.TCPServer(('', port), Handler)

logging.info("Serving content")

httpd.serve_forever()




