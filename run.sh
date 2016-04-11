#!/bin/bash

export CLASSPATH=./src/:./libs/*:./bin/



javac -d bin/ src/*.java ; optirun java -ea ProcessingMain
