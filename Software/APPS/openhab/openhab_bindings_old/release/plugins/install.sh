#! /bin/bash

# Author: Md. Farhabi Helal Ayon
OH2_ADDONS="/usr/share/openhab2/addons"
OH2_USERNAME="openhab"

JAR=$1

if [ "$JAR" == "" ]
then
    echo "error: no jar is specified."
    exit
fi

BINDING_NAME=$(ls $JAR | cut -d'_' -f1)

chmod +x $JAR

rm -f `ls $BINDING_NAME*.jar | grep -v "$JAR"`
rm -f `ls $OH2_ADDONS/$BINDING_NAME*.jar`

cp $JAR $OH2_ADDONS

chown $OH2_USERNAME:$OH2_USERNAME $OH2_ADDONS/$JAR
