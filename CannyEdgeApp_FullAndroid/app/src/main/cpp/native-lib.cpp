#include <jni.h>
#include <string>
#include <opencv2/opencv.hpp>

extern "C" JNIEXPORT void JNICALL
Java_com_example_canny_MainActivity_nativeProcess(
        JNIEnv* env,
        jobject /* this */,
        jlong matAddr) {

    cv::Mat &mat = *(cv::Mat*)matAddr;
    cv::Mat gray, edges;
    cv::cvtColor(mat, gray, cv::COLOR_RGBA2GRAY);
    cv::Canny(gray, edges, 80, 150);
    cv::cvtColor(edges, mat, cv::COLOR_GRAY2RGBA);
}
