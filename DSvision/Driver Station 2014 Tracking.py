
import cv2
import numpy
    

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
i = 0
while cv2.waitKey(1) <= 0:
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
    skinnyParticle = 0
    fatParticle = 0
    skinnyX = 0
    for hidx, contour, in enumerate(simplecontours):
               (xtemp, ytemp, wtemp, htemp) = cv2.boundingRect(contour) #Determine x,y,w,h by drawing a rectangle on contour
               
               x.append(xtemp + (wtemp/2)) #put x,y,w,h for each particle in an array
               y.append(ytemp + (htemp/2))
               w.append(wtemp)
               h.append(htemp)
               a.append(wtemp * htemp)
               atemp = wtemp * htemp       

               if atemp > 200: #Print particle data if area > 400
                          cv2.circle(color,(xtemp + (wtemp/2) ,ytemp + (htemp/2)),2,(0,255,0),thickness = -1)
                          tempstring = " (%d,%d)" % (xtemp, ytemp)
                          cv2.putText(color, tempstring, (xtemp + wtemp,ytemp + (htemp/2)), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)
                          cv2.putText(color, "%d" %(wtemp), (xtemp + (wtemp/2),ytemp + htemp + 30), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)
                          cv2.putText(color, "%d" %(htemp), (xtemp - 30 ,ytemp + (htemp/2)), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)

               if (wtemp > (htemp * 3)):
                         fatParticle = 1

               if (htemp > (wtemp * 3)):
                         skinnyParticle = 1
                         skinnyX = (xtemp + (wtemp/2)) - 180

    if fatParticle == 1 and skinnyParticle == 1:
               cv2.putText(color, "That's Hot" , (0,32), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255), thickness = 2) #Print ridiculous statement if both skinny/fat present



    cv2.imshow("Contours", color)
    i = 1

    
    

   
    
    
