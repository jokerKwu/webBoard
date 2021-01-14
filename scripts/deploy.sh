#!/bin/bash

REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=webBoard

cd $REPOSITORY/$PROJECT_NAME/

echo "> git pull"

git pull

echo "> project build start"

./gradlew build

echo "> step1 directory move"

cd $REPOSITORY

echo "> Build file copy"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "currently application pid: $CURRENT_PID"
echo "> currently application pid check"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}*.jar)

echo "> currently application pid check"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}*.jar)


if [ -z "$CURRENT_PID" ]; then
    echo "> currently no application "
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> new application deploy"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep *.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &