#!/usr/bin/python

import urllib2
import time
import string

INTERVAL=100

i = 0

while True:
    contents = urllib2.urlopen("http://raspberrypi.local/").read()
#    print "%s" % contents.rstrip()
    i = i + 1
    if (not i % INTERVAL):
        print "iteration ", i

        
