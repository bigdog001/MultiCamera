package com.android.sony.tv.config;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.sony.tv.beans.VariableHolder;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;

public class TVService extends Service {
    AlarmManager alarmManager;
    private boolean startStatus;
    private PendingIntent pi;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "TVService started....");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "TVService onStartCommand....");
        if (!startStatus) {
            startStatus = true;
            DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "TVService is not started yet,let me start it now....");
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            pi = PendingIntent.getBroadcast(this, 0, new Intent(VariableHolder.Constants.TIMER_BROADCAST_UNIT_NAME), 0);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, VariableHolder.Constants.INTERVAL_UNIT, pi);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "TVService is onDestroy,game over....");
        if (alarmManager != null) {
            alarmManager.cancel(pi);
            alarmManager = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
