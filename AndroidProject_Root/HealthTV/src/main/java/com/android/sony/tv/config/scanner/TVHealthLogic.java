package com.android.sony.tv.config.scanner;

import android.content.Context;
import com.android.sony.tv.activity.HealthCheckerActivity;
import com.android.sony.tv.beans.TimeCounter;
import com.android.sony.tv.beans.TimeRange;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.InterfaceGenerator;
import com.android.sony.tv.utils.Utils;

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
        TimeCounter counter_now = new TimeCounter(h, m);
        VariableHolder.logProvider.d(getClass().getSimpleName(), "diff: "+(System.currentTimeMillis())/1000+",Current hour:" + h + ",mins:" + m);
        if(Utils.isInLockInterval()){
            if (lock_extention > System.currentTimeMillis()) {
                VariableHolder.logProvider.d(getClass().getSimpleName(), "Lock the tv later ,until :" + new Date(lock_extention));
               return;
            }
            lock_extention = 0;
            VariableHolder.logProvider.d(getClass().getSimpleName(), "it is "+new Date()+",disable the TV now!");
            HealthCheckerActivity.startMe(c);
        }else {
            HealthCheckerActivity.tryToEnableTV();
        }
    }
}
