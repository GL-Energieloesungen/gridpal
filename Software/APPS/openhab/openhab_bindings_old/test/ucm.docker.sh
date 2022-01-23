#!/bin/sh

VERSION=$1

case $VERSION in
    "2.2") docker run --rm --net=host --device=/dev/ttyUSB0 -v /work/repo/gridpal.bindings/release/plugins:/openhab/addons -v /work/openhabdocker/conf:/openhab/conf -v /work/openhabdocker/userdata24:/openhab/userdata -v /work/openhabdocker/conf:/etc/openhab2 -it openhab/openhab:2.2.0
    ;;
    "2.3") docker run --rm --privileged --net=host --device=/dev/ttyUSB0 -v /work/repo/gridpal.bindings/release/plugins:/openhab/addons -v /work/openhabdocker/conf:/openhab/conf -v /work/openhabdocker/userdata23:/openhab/userdata -v /work/openhabdocker/conf:/etc/openhab2 -it openhab/openhab:2.3.0
    ;;
    "M|m") docker run --rm --net=host --device=/dev/ttyUSB0 -v /work/repo/gridpal.bindings/release/plugins:/openhab/addons -v /work/openhabdocker/conf:/openhab/conf -v /work/openhabdocker/userdataM:/openhab/userdata -v /work/openhabdocker/conf:/etc/openhab2 -it openhab/openhab:milestone
    ;;
    *) echo "Invalid argument"
    ;;
esac
