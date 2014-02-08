
import cv2
import numpy
import math

from pynetworktables import *

NetworkTable.SetIPAddress("10.1.66.2") #Connect to network tables on robot
NetworkTable.SetClientMode()
NetworkTable.Initialize()

visionDataTable = NetworkTable.GetTable("visionDataTable") #Connect specifically to vision NetworkTable

visionDataTable.PutBoolean("isHot",False) #Define default values for Network Table Variables
visionDataTable.PutNumber("skinnyOffset",0.0)

def find_distance(x1,y1,x2,y2):
    root = math.sqrt(  ((x2 - x1) ** 2) + ((y2 - y1) ** 2)  )
    rootInt = int(root)
    return rootInt

def threshold_range(im, lo, hi):
    '''Returns a binary image if the values are between a certain value'''
    
    unused, t1 = cv2.threshold(im, lo, 255, type=cv2.THRESH_BINARY)
    unused, t2 = cv2.threshold(im, hi, 255, type=cv2.THRESH_BINARY_INV)
    return cv2.bitwise_and(t1, t2)

vc = cv2.VideoCapture()


if not vc.open('http://10.1.66.11/mjpg/video.mjpg'): #connect to Axis Camera
#if not vc.open(0): #connect to Webcam
    print "Could not connect to camera"
    exit(1)
while cv2.waitKey(10) <= 0:
#while i == 0:
    success, img = vc.read()
    if not success:
        print ("Failure")
        break

    #image processing
    hsv = cv2.cvtColor(img,cv2.cv.CV_BGR2HSV) # Convert original color image to hsv image

    h, s, v = cv2.split(hsv) #Split hsv image into hue/saturation/value images

    h = threshold_range(h, 40, 110) #Isolate only the green in the image
    s = threshold_range(s, 90, 255)
    v = threshold_range(v, 20, 255)

    threshold = cv2.bitwise_and(h, cv2.bitwise_and(s, v)) #Combine 3 thresholds into one final threshold image

    kernal = cv2.getStructuringElement(cv2.MORPH_RECT, (2,2), anchor=(1,1))

    morphed = cv2.morphologyEx(threshold, cv2.MORPH_CLOSE, kernal, iterations=5) #make threshold more solid

    contours, hierarchy = cv2.findContours(morphed.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_TC89_KCOS)
    simplecontours = [cv2.approxPolyDP (cnt, 5, True) for cnt in contours]

    color = cv2.cvtColor(morphed,cv2.cv.CV_GRAY2BGR) # Change binary to color

    cv2.drawContours(color, simplecontours, -1, (0,0,255), thickness = 2)

    x =[]
    y = []
    w = []
    h = []
    a = []
    fatParticles = []
    skinnyParticles = []
    isFat = 0
    isSkinny = 0
    for partCount, contour, in enumerate(simplecontours):
               (xtemp, ytemp, wtemp, htemp) = cv2.boundingRect(contour) #Determine x,y,w,h by drawing a rectangle on contour
               
               x.append(xtemp + (wtemp/2)) #put x,y,w,h for each particle in an array
               y.append(ytemp + (htemp/2))
               w.append(wtemp)
               h.append(htemp)
               a.append(wtemp * htemp)
               atemp = wtemp * htemp       

               #Keep in mind, x and y are the actual centers, but xtemp and ytemp is the upper left corner
               
               if atemp > 200: #Print particle data if area > 200
                          cv2.circle(color,(xtemp + (wtemp/2) ,ytemp + (htemp/2)),2,(0,255,0),thickness = -1)
                          tempstring = " (%d,%d)" % (xtemp, ytemp)
                          cv2.putText(color, tempstring, (xtemp + wtemp,ytemp + (htemp/2)), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)
                          cv2.putText(color, "%d" %(wtemp), (xtemp + (wtemp/2),ytemp + htemp + 30), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)
                          cv2.putText(color, "%d" %(htemp), (xtemp - 30 ,ytemp + (htemp/2)), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)

               if (wtemp > (htemp * 3)):
                         fatParticles.append(partCount)

               if (htemp > (wtemp * 3)):
                         skinnyParticles.append(partCount)
               
    cv2.putText(color, "Skinny Particles: %d" %(len(skinnyParticles)) , (320,470), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)
    cv2.putText(color, "Fat Particles: %d" %(len(fatParticles)) , (0,470), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)
    i = 0
    hot = 0
    if (len(skinnyParticles) > 0):
        if (len(fatParticles) > 0):
                   for i in range(0, len(fatParticles)):
                       for j in range(0, len(skinnyParticles)):
                             #print find_distance(x[fatParticles[i]],y[fatParticles[i]],x[skinnyParticles[j]], y[skinnyParticles[j]])
                             cv2.line(color,(x[fatParticles[i]], y[fatParticles[i]]),(x[skinnyParticles[j]], y[skinnyParticles[j]]),(255,0,0), thickness = 1)
                                       
                             cv2.putText(color, " %d" %(find_distance(x[fatParticles[i]],y[fatParticles[i]],x[skinnyParticles[j]], y[skinnyParticles[j]])) ,
                                               (((x[fatParticles[i]] + x[skinnyParticles[j]])/2 ),(y[fatParticles[i]] + y[skinnyParticles[j]]) / 2 ),
                                               cv2.FONT_HERSHEY_PLAIN, 1, (255,0,0))
                             
                             if (find_distance(x[fatParticles[i]],y[fatParticles[i]],x[skinnyParticles[j]], y[skinnyParticles[j]]) < 150) :
                                 

                                       chosenSkinny = j            
                                       cv2.putText(color, "Close enough: HOT" , (0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)
                                       hot = 1
                
                   if (hot==0):
                       cv2.putText(color, "Far away: NOT HOT" , (0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)
       

        else:
                   cv2.putText(color, "No fat Particles, eat more" , (0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)

    else:
        if (len(fatParticles) > 0):
            cv2.putText(color, "Get me a skinny particle, then we can talk" , (0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)


    if (len(fatParticles) == 0) and (len(skinnyParticles) == 0):
        cv2.putText(color, "No particles, we are blind" , (0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)

    if (hot == 1):
        visionDataTable.PutBoolean("isHot",True)

    else:
        visionDataTable.PutBoolean("isHot",False)


        
    if (len(x) > 0):
        skinnyOffset = (x[0] - 320.0) / 320.0
        cv2.putText(color, "Skinny Offset: %f" %(skinnyOffset) , (320,440), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 1)
        visionDataTable.PutNumber("skinnyOffset",skinnyOffset)

    else:
        visionDataTable.PutNumber("skinnyOffset",0.0)
        

    
    cv2.imshow("Cameras are awesome :D", color)

    
    

   
    
    
