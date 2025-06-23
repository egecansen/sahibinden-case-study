#!/bin/bash
set -e

# Start ADB server and connect to Android emulator/device
adb start-server
sleep 2
adb connect host.docker.internal:5555 || true
sleep 2
adb devices

# Start Jenkins as the main process
exec /usr/local/bin/jenkins.sh "$@"
