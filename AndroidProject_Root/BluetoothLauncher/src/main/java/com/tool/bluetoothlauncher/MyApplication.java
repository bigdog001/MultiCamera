package com.tool.bluetoothlauncher;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by jw362j on 12/22/2016.
 */
public class MyApplication extends Application {

    protected PendingIntent pi;
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.log("MyApplication onCreate!");


    }




}
