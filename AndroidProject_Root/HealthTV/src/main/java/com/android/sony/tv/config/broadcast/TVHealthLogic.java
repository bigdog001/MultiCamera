package com.android.sony.tv.config.broadcast;

import android.content.Context;
import android.content.Intent;

import com.android.sony.tv.activity.HealthChecker;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.InterfaceGenerator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TVHealthLogic implements InterfaceGenerator.timerAction {
    public static long lock_extention;

    @Override
    public boolean isAllowedToExecute() {
        return true;
    }

    @Override
    public void onTime(Context c, int flag, Object data) {

        Calendar calendar = new GregorianCalendar();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        VariableHolder.logProvider.d(getClass().getSimpleName(), "diff: "+(System.currentTimeMillis())/1000+",Current hour:" + h + ",mins:" + m);
        if ((h >= 21 && m >= 30) || h < 5 ) {
            if (lock_extention > System.currentTimeMillis()) {
                VariableHolder.logProvider.d(getClass().getSimpleName(), "Lock the tv later ,until :" + new Date(lock_extention));
                return;
            }
            lock_extention = 0;
            VariableHolder.logProvider.d(getClass().getSimpleName(), "it is "+new Date()+",disable the TV now!");
//            Intent intent = new Intent(c, HealthChecker.class);
//            c.startActivity(intent);
        }


    }
}
