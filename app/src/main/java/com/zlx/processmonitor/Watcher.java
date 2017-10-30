package com.zlx.processmonitor;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zhulaixue on 2017/10/27.
 */

public class Watcher {
    public static final String TAG = "Native";
    private String mMontoredService = "MyService";
    private Context mContext;

    public void createAppMonitor(String userId) {
        if (!createWathcer(userId)) {
            Log.i(TAG, "===========Monitor created failed=========");
        } else {
            Log.i(TAG, "===========Monitor created success=========");
        }

		if (!connectToMonitor()) {
			Log.i(TAG, "===========connect Monitor failed=========");
		} else {
			Log.i(TAG, "===========connect Monitor success=========");
		}
    }

    public Watcher(Context context) {
        mContext = context;
    }

    private int isServiceRunning() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) am.getRunningServices(1024);
        for (int i = 0; i < runningService.size(); ++i) {
            if (mMontoredService.equals(runningService.get(i).service.getClassName().toString())) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Native方法，创建一个监视子进程.
     *
     * @param userId
     *            当前进程的用户ID,子进程重启当前进程时需要用到当前进程的用户ID.
     * @return 如果子进程创建成功返回true，否则返回false
     */
    private native boolean createWathcer(String userId);
    /**
     * Native方法，让当前进程连接到监视进程.
     *
     * @return 连接成功返回true，否则返回false
     */
    private native boolean connectToMonitor();
    /**
     * Native方法，向监视进程发送任意信息
     *
     * @msg 发给monitor的信息
     * @return 实际发送的字节
     */
    private native int sendMsgToMonitor(String msg);


    static {
        System.loadLibrary("monitor");
    }
}
