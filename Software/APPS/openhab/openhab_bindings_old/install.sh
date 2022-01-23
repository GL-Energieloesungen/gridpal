#!/bin/sh

OH_ADDON=/usr/share/openhab2/addons
JAR_PATTERN=org.openhab.binding.gridpal.*.jar

cd $(dirname $0)
sudo -v
sudo rm -f $OH_ADDON/$JAR_PATTERN
sudo cp -v release/$JAR_PATTERN $OH_ADDON/
