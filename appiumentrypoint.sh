#!/bin/bash
set -e

adb start-server
sleep 2
adb connect host.docker.internal:5555 || true
sleep 2
adb devices

exec appium --allow-insecure chromedriver_autodownload