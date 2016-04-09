#!/bin/bash

export CLASSPATH=./src/:./libs/*:./bin/

if [ "$(ls -A bin/)" ];then
rm out/production/Refugees/*; 
echo "Deleting .class files"
fi

javac -d bin/ src/*.java ; optirun java -ea ProcessingMain
