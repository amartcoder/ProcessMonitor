//
// Created by zhulaixue on 2017/10/27.
//
#include "com_zlx_processmonitor_Watcher.h"
#include "process.h"
/**
 * 全局变量，代表应用程序进程
 */
ProcessBase *g_process = NULL;
/**
 * 应用程序的UID
 */
const char* g_userId = NULL;
/**
 * 全局的JNIEnv, 子进程有时会用到它
 */
JNIEnv* g_env = NULL;

JNIEXPORT jboolean JNICALL
Java_com_zlx_processmonitor_Watcher_createWathcer(JNIEnv* env, jobject thiz, jstring userId) {
    g_process = new Parent(env, thiz);//创建父进程
    g_userId = env->GetStringUTFChars(userId, 0);
    g_process->catch_child_dead_signal();//接收子线程死掉的信号

    if (!g_process->create_child()) {
        LOGE("<<crate child error!>>");
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

JNIEXPORT jboolean JNICALL
Java_com_zlx_processmonitor_Watcher_connectToMonitor(JNIEnv* env, jobject thiz) {
    LOGE("======try connect monitor==========");
    if (g_process != NULL) {
        LOGE("aaaaaaaaaaa");
        if (g_process->create_channel()) {
            return JNI_TRUE;
        }
        return JNI_FALSE;
    }
    return JNI_FALSE;
}