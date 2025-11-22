cmake_minimum_required(VERSION 3.10.2)
project("canny_native")

# Build native-lib
add_library( native-lib SHARED native-lib.cpp )

find_package(OpenCV REQUIRED)

if(OpenCV_FOUND)
    message(STATUS "OpenCV include dirs: ${OpenCV_INCLUDE_DIRS}")
    include_directories(${OpenCV_INCLUDE_DIRS})
    target_link_libraries(native-lib ${OpenCV_LIBS})
else()
    message(WARNING "OpenCV not found. If building natively, download OpenCV Android SDK and pass -DOpenCV_DIR=<path>/sdk/native/jni")
endif()
