#! /usr/bin/python3


import time
import paho.mqtt.client as mqttClient



BROKER_ADDRESS = "192.168.11.133"
BROKER_PORT = 1883
BROKER_MAC = "f8:b5:68:a0:10:03"

TOPIC = "sh/command/" + BROKER_MAC



def main():

    client = mqttClient.Client(client_id="apl1") #, clean_session=True, userdata=None, protocol=MQTTv311, transport="tcp")    
    client.username_pw_set("kanok", "kanok")
    client.on_connect = on_connect
    client.on_message = on_message

    client.connect(host=BROKER_ADDRESS, port=BROKER_PORT, keepalive=60, bind_address="")

    flag = True

    command = []

    while True:
        time.sleep(3)

        if flag == True:
            flag = False
            command = [ 128, 74, 17, 1, 2, 1, 255, 255, 255, 255, 255, 255 ]          
            
        else:
            flag = True
            command = [ 128, 74, 17, 1, 2, 0, 255, 255, 255, 255, 255, 255 ]

        print("Payload = ", bytearray(command))
        client.publish(topic=TOPIC, payload=bytearray(command), qos=0, retain=False)

        client.loop(timeout=1.0)



def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("{} connected.".format(client._client_id.decode("utf-8")))    
        client.subscribe(TOPIC)


def on_message(client, userdata, msg):
    print(">> [ feedback received ]")



if __name__ == "__main__" : main()