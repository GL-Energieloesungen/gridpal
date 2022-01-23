#! /usr/bin/python3


import time
import paho.mqtt.client as mqttClient



BROKER_ADDRESS = "192.168.11.133"
BROKER_PORT = 1883

TOPIC = "sh/greeting"



def main():

    client1 = mqttClient.Client(client_id="apl1") #, clean_session=True, userdata=None, protocol=MQTTv311, transport="tcp")    
    client1.username_pw_set("kanok", "kanok")
    client1.on_connect = on_connect
    client1.on_message = on_message

    client1.connect(host=BROKER_ADDRESS) #, port=BROKER_PORT, keepalive=60, bind_address="")


    client2 = mqttClient.Client(client_id="apl2") #, clean_session=True, userdata=None, protocol=MQTTv311, transport="tcp")
    client2.username_pw_set("kanok", "kanok")
    client2.on_connect = on_connect
    client2.on_message = on_message

    client2.connect(host=BROKER_ADDRESS) #, port=BROKER_PORT, keepalive=60, bind_address="")


    flag = True
    
    while True:
        time.sleep(2)

        client1.loop(timeout=1.0)
        client2.loop(timeout=1.0)

        if flag == True:
            client1.publish(topic=TOPIC, payload="{} speaking : Hello!".format(client1._client_id), qos=0, retain=False).decode("utf-8")
            flag = False

    


def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("{} connected.".format(client._client_id).decode("utf-8"))    
        client.subscribe(TOPIC)


def on_message(client, userdata, msg):
    print("\n\nNew message received for {} : {}".format(client._client_id, msg.payload).decode("utf-8"))
    client.publish(topic=TOPIC, payload="{} speaking : Hello!".format(client._client_id), qos=0, retain=False)
    



if __name__ == "__main__" : main()