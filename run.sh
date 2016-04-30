#!/bin/bash

export CLASSPATH=./src/main/:./libs/*:./build/



#javac -d bin/ $(find  src/main/ -iname *.java) ;
 optirun java -ea main.ProcessingMain
