#!/bin/sh

# build
# mvn clean install

# run
INPUTFILENAME=$1
java -jar target/MowItNow-1.0-SNAPSHOT.jar $INPUTFILENAME
