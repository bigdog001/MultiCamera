package com.android.sony.tv.utils;

import com.android.sony.tv.activity.HealthCheckerActivity;
import com.android.sony.tv.beans.TimeCounter;
import com.android.sony.tv.beans.TimeRange;
import com.android.sony.tv.beans.VariableHolder;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {
    public static boolean isInLockInterval() {
        boolean result = false;
        Calendar calendar = new GregorianCalendar();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        TimeCounter counter_now = new TimeCounter(h, m);
        for (TimeRange tr : VariableHolder.ranges) {
            if ((tr.getStart().getTime() < counter_now.getTime() && tr.getEnd().getTime() > counter_now.getTime())) {
                VariableHolder.logProvider.d(Utils.class.getSimpleName(), counter_now + " is in " + tr);
                result = true;
                break;
            }
        }
        return result;
    }
}
