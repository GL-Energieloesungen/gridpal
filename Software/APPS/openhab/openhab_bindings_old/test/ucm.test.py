from paho.mqtt import publish
import json
import random
import requests
import time
import unittest

# CHANGE HERE
TARGET_HOST = "localhost"
DEVICE_COUNT = 5

# DO NOT CHANGE AFTER THIS LINE

REST_URL = "http://" + TARGET_HOST + ":8080/rest"
MQTT_HOST = TARGET_HOST
MQTT_AUTH = {'username':'gridpal', 'password':'gridpal'}
headers = {'Content-Type': 'application/json','Accept': 'application/json'}

data_modbus = {"name":"","connection":["/dev/ttyUSB0","19200","8","none","1","rtu","1000","5000"],"interface-type":"modbus - serial","register-map":[{"valuetype":"uint16","unit":"Hz","start":"23340","length":"1","description":"Frequency","multiply":"0.01","type":"holding","offset":""}],"manufacturer":"ABB Group","devicetype":"B23 Modbus Meter","id":"1"}
data_mbus = {"name":"","connection":["/dev/ttyUSB0","19200","8","none","1","rtu","1000","5000"],"interface-type":"mbus - serial","register-map":[{"valuetype":"","unit":"m3","start":"13","length":"","description":"Volume","multiply":"0.001","type":"","offset":""},{"valuetype":"","unit":"kWh","start":"13","length":"","description":"Power","multiply":"0.001","type":"","offset":"1000"}],"manufacturer":"Other","devicetype":"MBus Pulse Counter","id":"1"}

class UCM_API_Test(unittest.TestCase):
    @staticmethod
    def is_valid_json(url):
        try:
            response = requests.get(url)
            j = json.loads(response.text)
            return True
        except:
            return False
        
    def test_mbus(self):
        self.mbus_add()
        self.assertTrue(self.device_count() == DEVICE_COUNT)
        self.mbus_update()
        self.mbus_remove()
        print("MBus Device Works")
        
    def test_modbus(self):
        self.modbus_add()
        self.assertTrue(self.device_count() == DEVICE_COUNT)
        self.modbus_update()
        self.modbus_remove()
        print("Modbus Device Works")
        
    def device_count(self):
        response = requests.get(REST_URL + '/devices')
        return len(response.json())
        
    def remove_all(self):
        response = requests.get(REST_URL + '/devices/remove/all')
        
    def mbus_add(self):
        for i in range(DEVICE_COUNT):
            data_mbus["name"] = "mbus" + str(i)
            print("Adding " + data_mbus["name"])
            response = requests.post(REST_URL + '/devices/add', headers=headers, data=json.dumps(data_mbus))
            self.assertTrue("success" == response.json()["status"])
            time.sleep(1)
            
    def mbus_remove(self):
        for i in range(DEVICE_COUNT):
            name = "mbus" + str(i)
            print("Removing " + name)
            response = requests.get(REST_URL + '/devices/remove/' + name)
            self.assertTrue("success" == response.json()["status"])
            time.sleep(1)
            
    def mbus_update(self):
        rval = random.randint(1, 1000)
        for i in range(DEVICE_COUNT):
            props = ["Volume", "Power"]
            for p in props:
                name = "mbus" + str(i)
                print("Updating " + name)
                topic = "ucm/devices/" + name + "/" + name + "__" + p
                payload = json.dumps({"value": rval})
                publish.single(topic, payload, hostname=MQTT_HOST, auth=MQTT_AUTH, qos=2)
                time.sleep(1)
                
        time.sleep(5)
        
        for i in range(DEVICE_COUNT):
            props = ["Volume", "Power"]
            for p in props:
                name = "mbus" + str(i)
                print("Checking " + name)
                item = name + "__" + p
                response = requests.get(REST_URL + "/items/" + item)
                self.assertTrue(int(response.json()["state"]) == rval)
                
    def modbus_add(self):
        for i in range(DEVICE_COUNT):
            data_modbus["name"] = "modbus" + str(i)
            print("Adding " + data_modbus["name"])
            response = requests.post(REST_URL + '/devices/add', headers=headers, data=json.dumps(data_modbus))
            self.assertTrue("success" == response.json()["status"])
            time.sleep(1)
            
    def modbus_remove(self):
        for i in range(DEVICE_COUNT):
            name = "modbus" + str(i)
            print("Removing " + name)
            response = requests.get(REST_URL + '/devices/remove/' + name)
            self.assertTrue("success" == response.json()["status"])
            time.sleep(1)
            
    def modbus_update(self):
        print("Waiting for Modbus Binding to read some data")
        time.sleep(5)
        for i in range(DEVICE_COUNT):
            name = "modbus" + str(i)
            print("Checking " + name)
            item = name + "__Frequency"
            response = requests.get(REST_URL + "/items/" + item)
            self.assertTrue(response.json()["state"] != "NULL")
            
    #@unittest.skip("System Resource Works")
    def test_system_resources(self):
        self.assertTrue(self.is_valid_json(REST_URL + '/system/resources'))
        self.assertTrue(self.is_valid_json(REST_URL + '/system/internet/status'))
        self.assertTrue(self.is_valid_json(REST_URL + '/system/buses'))
        self.assertTrue(self.is_valid_json(REST_URL + '/system/serials'))
        print("System Resource Works")
    
    #@unittest.skip("Vendor Database Works")
    def test_vendor_db(self):
        self.assertTrue(self.is_valid_json(REST_URL + '/vendors/interface'))
        self.assertTrue(self.is_valid_json(REST_URL + '/vendors/registermap?id_device=1'))
        self.assertTrue(self.is_valid_json(REST_URL + '/vendors/device/config?id=1'))
        self.assertTrue(self.is_valid_json(REST_URL + '/vendors/manufacturer?id_interface=1'))
        print("Vendor Database Works")
        
if __name__ == '__main__':
    unittest.main()
