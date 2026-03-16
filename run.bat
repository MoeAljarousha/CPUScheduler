@echo off

if "%1"=="" (
    echo Usage: run input.json output.json
    exit /b
)

set INPUT=%1
set OUTPUT=%2

echo Compiling...

javac -cp ".;lib/*" src\*.java

echo Running scheduler...

java -cp ".;lib/*;src" Main %INPUT% %OUTPUT%