package com.zlx.processmonitor;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.os.Process.myUid();
        android.os.Process.myTid();
        android.os.Process.myPid();

        Log.i(Watcher.TAG, "myUid="+android.os.Process.myUid()+" myTid="+android.os.Process.myTid()+" myPid="+android.os.Process.myPid());

        if (!worked()) {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
            Toast.makeText(MainActivity.this, "服务启动成功", Toast.LENGTH_LONG).show();
        }
    }

    private boolean worked() {
        ActivityManager myManager = (ActivityManager) this.getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(Integer.MAX_VALUE);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals("com.zlx.processmonitor.MyService")) {
                return true;
            }
        }
        return false;
    }
}
