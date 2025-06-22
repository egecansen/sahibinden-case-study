#!/bin/bash
adb start-server
sleep 2
adb connect host.docker.internal:5555
adb devices

tail -f /dev/null