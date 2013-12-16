/*
 * Utils.cpp
 *
 *  Created on: 2013年12月11日
 *      Author: proto
 */
#include <jni.h>
#include <stdio.h>
#include <iostream>

#include <opencv2/opencv.hpp>
#include <android/log.h>

using namespace std;
using namespace cv;

#include "liangjie_app_Utils.h"

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "Test", __VA_ARGS__))

JNIEXPORT jdouble JNICALL Java_liangjie_app_Utils_compareintBitmap(JNIEnv * env, jobject thiz, jintArray buff1,
		jint firstW, jint firstH, jintArray buff2, jint secondW, jint secondH) {
	jint *cbuf1 = env->GetIntArrayElements(buff1, 0);
	jint *cbuf2 = env->GetIntArrayElements(buff2, 0);

	Mat b1(firstH, firstW, CV_8UC1, (unsigned char*) cbuf1);
	Mat b2(secondH, secondW, CV_8UC1, (unsigned char*) cbuf2);
	//计算生成直方图矩阵
	int histSize = 256;
	float ranges[] = { 0, 255 };
	const float* histRange = { ranges };
	Mat hist1, hist2; //用来存放生成的直方图矩阵
	clock_t now = clock();
	calcHist(&b1, 1, 0, Mat(), hist1, 1, &histSize, &histRange);
	calcHist(&b2, 1, 0, Mat(), hist2, 1, &histSize, &histRange);
	LOGI("计算直方图耗时（毫秒）：%d", (clock() - now) / 1000);
	//对计算的直方图进行归一化处理
	normalize(hist1, hist1, 1.0);
	normalize(hist2, hist2, 1.0);
	//两个直方图进行比较b
	jdouble db;
	clock_t sec=clock();
	db = compareHist(hist1, hist2, 0);
	LOGI("比较直方图耗时（毫秒）：%d", (clock() - sec)/1000);
	LOGI("测试NDK！");
	env->ReleaseIntArrayElements(buff1, cbuf1, 0);
	env->ReleaseIntArrayElements(buff2, cbuf2, 0);
	return db;
}
