package com.android.sony.tv.beans;
import com.hadoopz.MyDroidLib.util.DroidLogProvider;
public class VariableHolder {
    public static DroidLogProvider logProvider;
    public static String appGlobalSharedPreFileName = "HealthTV";
    public static String deviceTokenName = "deviceToken";
    public static String needToUpdatedeviceTokenName = "needToUpdatedeviceToken";
    public static String tvCodeName = "tvCode";
    public static final int UDP_SERVER_PORT = 18888;
    public static int screenW = 0;
    public static int screenH = 0;
    public static TimeRange[] ranges = {
            new TimeRange(new TimeCounter(21, 30), new TimeCounter(23, 59)),
            new TimeRange(new TimeCounter(0, 0), new TimeCounter(5, 0))
    };
    public static final class Constants {
        public static final String TIMER_BROADCAST_UNIT_NAME = "com.android.sony.tv.monitor";
        public static final long INTERVAL_UNIT = 1000 * 10;
    }
}