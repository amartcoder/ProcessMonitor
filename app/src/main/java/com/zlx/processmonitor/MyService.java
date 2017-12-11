package com.zlx.processmonitor;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    Timer timer;
    int i = 0;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PackageManager pm = getPackageManager();
        try {
            @SuppressLint("WrongConstant") ApplicationInfo ai = pm.getApplicationInfo("com.zlx.processmonitor", PackageManager.GET_SERVICES);
            int userId = ai.uid;
            Log.i(Watcher.TAG, "userId=" + userId);
            Watcher watcher = new Watcher(this);
            watcher.createAppMonitor(String.valueOf(userId));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.i(Watcher.TAG, "EXEC " + i);
                i ++;
            }
        }, 0, 1000*3);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
