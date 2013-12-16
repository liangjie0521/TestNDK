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

//JNIEXPORT jdoubleArray JNICALL Java_liangjie_app_Utils_compareBitmap(JNIEnv * env, jobject thiz, jbyteArray buff1,
//		jint width1, jint height1, jbyteArray buff2, jint width2, jint height2) {
//	clock_t now = clock();
//	jbyte* buf1 = env->GetByteArrayElements(buff1, 0);
//	jbyte* buf2 = env->GetByteArrayElements(buff2, 0);
//	//生成Mat,单通道
//	Mat b1(height1, width1, CV_8UC1, (unsigned char*) buf1);
//	Mat b2(height2, width2, CV_8UC1, (unsigned char*) buf2);
//	cout << "M = " << endl << " " << b1 << endl << endl;
//	//计算生成直方图矩阵
//	int histSize = 256;
//	float ranges[] = { 0, 255 };
//	const float* histRange = { ranges };
//	Mat hist1, hist2; //用来存放生成的直方图矩阵
//	calcHist(&b1, 1, 0, Mat(), hist1, 1, &histSize, &histRange);
//	calcHist(&b2, 1, 0, Mat(), hist2, 1, &histSize, &histRange);
//	//对计算的直方图进行归一化处理
//	normalize(hist1, hist1, 1.0);
//	normalize(hist2, hist2, 1.0);
//	//两个直方图进行比较b
//	jdoubleArray jd = env->NewDoubleArray(4);
//	jdouble db[] = { };
//	for (int i = 0; i < 4; i++) {
//		db[i] = compareHist(hist1, hist2, i);
//		LOGI("第%i对比值：%d", i, db[i]);
//	}
//	env->SetDoubleArrayRegion(jd, 0, 4, db);
//	LOGI("测试NDK！");
//	env->ReleaseByteArrayElements(buff1, buf1, 0);
//	env->ReleaseByteArrayElements(buff2, buf2, 0);
//	return jd;
//}

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
	calcHist(&b1, 1, 0, Mat(), hist1, 1, &histSize, &histRange);
	calcHist(&b2, 1, 0, Mat(), hist2, 1, &histSize, &histRange);
	//对计算的直方图进行归一化处理
	normalize(hist1, hist1, 1.0);
	normalize(hist2, hist2, 1.0);
	//两个直方图进行比较b
	jdouble db;
	db= compareHist(hist1, hist2, 0);

	LOGI("测试NDK！");
	env->ReleaseIntArrayElements(buff1, cbuf1, 0);
	env->ReleaseIntArrayElements(buff2, cbuf2, 0);
	return db;
}
