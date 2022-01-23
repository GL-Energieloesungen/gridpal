#! /bin/bash

# Author: Md. Farhabi Helal Ayon
OH2_ADDONS="/usr/share/openhab2/addons"

BINDING_NAME=$1

if [ "$BINDING_NAME" == "" ]
then
    echo "error: no binding is specified."
    exit
fi


BINDING_NAME=$(ls $BINDING_NAME | cut -d'_' -f1)
rm -f `ls $OH2_ADDONS/$BINDING_NAME*.jar`
