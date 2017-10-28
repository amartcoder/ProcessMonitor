//
// Created by zhulaixue on 2017/10/27.
//
#include <jni.h>

#ifndef PROCESSMONITOR_COM_ZLX_PROCESSMONITOR_WATCHER_H
#define PROCESSMONITOR_COM_ZLX_PROCESSMONITOR_WATCHER_H
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jboolean JNICALL
Java_com_zlx_processmonitor_Watcher_createWathcer(JNIEnv *, jobject, jstring);

JNIEXPORT jboolean JNICALL
Java_com_zlx_processmonitor_Watcher_connectToMonitor(JNIEnv *, jobject);

JNIEXPORT jboolean JNICALL
Java_com_zlx_processmonitor_Watcher_sendMsgToMonitor(JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif //PROCESSMONITOR_COM_ZLX_PROCESSMONITOR_WATCHER_H
