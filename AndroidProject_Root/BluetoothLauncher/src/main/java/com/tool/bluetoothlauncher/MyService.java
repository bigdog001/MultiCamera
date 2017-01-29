package com.tool.bluetoothlauncher;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by jw362j on 12/22/2016.
 */
public class MyService extends Service {
    String action = "com.tool.bluetoothlauncher.alarm";
    AlarmManager alarmManager;
    PendingIntent pi;
    BroadcastReceiver receiver;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init() {
        LogUtil.log("MyApplication init!");
//        AlarmUtil.startBroadcast(getApplicationContext(),1000*5,IntervalChecker.class,"com.tool.bluetoothlauncher.alarm");
         alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction( action);
         pi = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                0, 1000*30, pi);
         receiver = new IntervalChecker() ;
        registerReceiver(  receiver, new IntentFilter( action));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
