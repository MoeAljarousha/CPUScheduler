#!/bin/bash

if [ "$#" -lt 2 ]; then
    echo "Usage: ./run.sh input.json output.json"
    exit 1
fi

INPUT=$1
OUTPUT=$2

echo "Compiling..."

javac -cp ".:lib/*" src/*.java

echo "Running scheduler..."

java -cp ".:lib/*:src" Main $INPUT $OUTPUT