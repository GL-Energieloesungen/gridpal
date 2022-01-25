import os
import sys
if not os.getegid() == 0:
    sys.exit('Script must be run as root')

from time import sleep
from pyA64.gpio import gpio
from pyA64.gpio import port
import urllib2

led = port.PE4
gpio.init()
gpio.setcfg(led, gpio.OUTPUT)

while(1):
    try:
        gpio.output(led, 0)
        sleep(1)
        gpio.output(led, 1)
        sleep(1)
    except:
        sys.exit('bye bye..')
