package com.tool.mytool.lib.app;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import com.tool.mytool.lib.exceptions.BaseExceptionHandler;
import com.tool.mytool.lib.exceptions.LocalFileHandler;
import com.tool.mytool.lib.util.JStringKit;
import com.tool.mytool.lib.util.LogUtil;

/**
 * Created by jw362j on 12/30/2016.
 */
public abstract class MyApplication extends Application {
    private PendingIntent pi;
    private AlarmManager alarmManager;
    @Override
    public void onCreate()
    {
        super.onCreate();
        initExceptionHandler();
        initRTCTimer();
    }

    private void initExceptionHandler(){
        if (getDefaultUncaughtExceptionHandler() == null)
        {
            Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(getApplicationContext()));
        } else
        {
            Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
        }
    }

    private void initRTCTimer(){
        if (initRTC() == null) {
            return;
        }
        int x = 0;
        LogUtil.log("the length of initRTC is:"+ initRTC().length);
        for (String item:initRTC()) {
            LogUtil.log("the item("+ (x++) +")is:"+ item);
        }
        if(initRTC().length >= 2 && JStringKit.isNumber(initRTC()[1])){
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent();
            intent.setAction(initRTC()[0]);
            pi = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    0, Long.valueOf(initRTC()[1]), pi);
        }else {
            LogUtil.log("no alarm service available...");
        }
    }

    protected void stopMe(){
        alarmManager.cancel(pi);
        alarmManager = null;
        pi = null;
    }

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();

    public abstract String[]initRTC();//返回的第一个参数代表广播的action 第二个代表间隔时长
}
