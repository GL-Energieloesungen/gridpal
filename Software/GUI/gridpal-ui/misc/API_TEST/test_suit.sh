#!/bin/sh

HOST='localhost'
PORT='8080'

URL=http://$HOST:$PORT

echo "Home Page"

echo
echo "Interface"
curl -X GET $URL/rest/system/interfaces

echo
echo "Buses"
curl -X GET $URL/rest/system/buses

echo
echo "Resources"
curl -X GET $URL/rest/system/resources

echo
echo "Internet"

echo
echo "Status"
curl -X GET $URL/rest/system/internet/status

echo
echo "Device Manager"
echo "Devices"
curl -X GET $URL/rest/devices

echo
echo "Remove Device"
# curl -X GET $URL/rest/modbus/devices/remove?name=



echo
echo "Add Device"

echo
echo "Interface List"
curl -X GET $URL/rest/vendors/interface

echo
echo "manufacturer List"
curl -X GET $URL/rest/vendors/manufacturer?id_interface=1

echo
echo "Device Type List"
curl -X GET $URL/rest/vendors/device?id_interface=1&id_manufacturer=1

echo
echo "Serial Ports"
curl -X GET $URL/rest/vendors/device?id=1

# echo
# echo "Device Data"
# curl -X GET $URL/rest/vendors/devices/1

echo
echo "Register Map"
curl -X GET $URL/rest/vendors/registermap?id_device=1





























echo
