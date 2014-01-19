import serial
import SimpleHTTPServer
import SocketServer

PORT = 8000

class Handler(SimpleHTTPServer.SimpleHTTPRequestHandler):
	def do_GET(self):
		print self.path
		if '/data' in self.path:
			ser.flushInput()
			line = ser.readline()
			x = line.split() [1]
			self.wfile.write(x)

httpd = SocketServer.TCPServer(("", PORT), Handler)

ser= serial.Serial("/dev/ttyACM0",115200)

print "serving at port", PORT
httpd.serve_forever()
