import serial 
ser = serial.Serial("COM5",115200)
while True :
        print ("Sending a 1 :)")
        ser.write("1")
        ser.flush()
        line= ser.readline()
        #x= line.split()[1]
        print line
ser.close()
