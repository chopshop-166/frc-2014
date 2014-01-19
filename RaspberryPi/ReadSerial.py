import serial 
ser = serial.Serial("/dev/ttyACM0",115200)
while True : 
	line= ser.readline()
	x= line.split()[1]
	print x
ser.close()
