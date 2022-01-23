#! /bin/bash

# Name: UCM Binding Deploy Script
#Author: Md. Farhabi Helal Ayon

#JOB_NAME="UCM-Project-BackEnd"

BASE_DIR=$(pwd)
DEFAULT_PARENT_DIR="openhab2-addons"
DEFAULT_NAME_PATTERN="org.openhab.binding.gridpal."
PARAM_PARENT_DIR=""
PARAM_NAME_PATTERN=""
DATE=$(date '+%Y%m%d_%H%M%S')

JOB_DIR="$JENKINS_HOME/userContent/$JOB_NAME"
RELEASE_DIR="$JOB_DIR/$DATE"
LATEST_DIR="$JOB_DIR/latest"


if [ $# -eq "0" ]
then
    echo "No parameter specified! Using default values."
    
    PARAM_PARENT_DIR=$DEFAULT_PARENT_DIR
    PARAM_NAME_PATTERN=$DEFAULT_NAME_PATTERN

elif [ $# -eq "1" ]
then
    echo "Only ONE parameter specified! Using default values for the <name-pattern>."

    PARAM_PARENT_DIR=$1
    PARAM_NAME_PATTERN=$DEFAULT_NAME_PATTERN

elif [ $# -eq "2" ]
then
    PARAM_PARENT_DIR=$1
    PARAM_NAME_PATTERN=$2

else [ $# -gt "2" ]
    echo "Usage: $0 <parent-dir> <name-pattern>"
    exit 1
fi

PARENT_DIR="$BASE_DIR/$PARAM_PARENT_DIR"
NAME_PATTERN=$PARAM_NAME_PATTERN



[ ! -d "$JOB_DIR" ] && mkdir "$JOB_DIR"


mkdir "$RELEASE_DIR"
rm -f "$LATEST_DIR"
ln -s "$RELEASE_DIR" "$LATEST_DIR"


cd "$PARENT_DIR"

PROJECTS=$(ls | grep $NAME_PATTERN)

for PROJECT in $PROJECTS
do
    JAR_DIR="$PARENT_DIR/$PROJECT/target"
    cd "$JAR_DIR"
    JAR=$(ls "$PROJECT"*.jar)
    # echo -e "Jar Dir: $JAR_DIR\n"
    # echo -e "Jar: $JAR\n"
    cp "$JAR_DIR/$JAR" "$RELEASE_DIR"
done


echo "Deployment : SUCCESSFUL!"
