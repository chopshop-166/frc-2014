import SimpleHTTPServer
import SocketServer

PORT = 8000

class Handler(SimpleHTTPServer.SimpleHTTPRequestHandler):
	def do_GET(self):
		print self.path
		self.wfile.write("kyra")
		if '/data' in self.path:
			self.wfile.write("datagoeshere")
		if '/orange' in self.path:
			self.wfile.write("hi")

httpd = SocketServer.TCPServer(("", PORT), Handler)

print "serving at port", PORT
httpd.serve_forever()
