package com.tool.bluetoothlauncher;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by jw362j on 12/22/2016.
 */
public class AlarmUtil {
    // 开启定时服务

    public static void startBroadcast(Context context, int seconds, Class<?> cls, String action) {

        // 获取AlarmManager系统服务

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, cls);

        intent.setAction(action);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), seconds, pendingIntent);

    }



    // 停止定时服务

    public static void stopBroadcast(Context context, Class<?> cls, String action) {

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, cls);

        intent.setAction(action);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 取消

        manager.cancel(pendingIntent);

    }

}
