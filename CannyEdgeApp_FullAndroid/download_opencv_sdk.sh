#!/usr/bin/env bash
set -e
# This script downloads OpenCV Android SDK (example 4.8.0) and extracts it to the 'sdk' folder.
# You can change the URL to the version you want (see https://opencv.org/releases/).
OPENCV_ZIP_URL="https://sourceforge.net/projects/opencvlibrary/files/4.8.0/opencv-4.8.0-android-sdk.zip/download"
OUT_ZIP="opencv-android-sdk.zip"
TARGET_DIR="sdk"

echo "Downloading OpenCV Android SDK..."
curl -L -o "$OUT_ZIP" "$OPENCV_ZIP_URL"

echo "Extracting to $TARGET_DIR..."
unzip -o "$OUT_ZIP" -d "$TARGET_DIR"
echo "Done. Place or point -DOpenCV_DIR to $TARGET_DIR/OpenCV-android-sdk/sdk/native/jni when building with CMake."
