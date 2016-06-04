package apps.bigdog.com.multicamera.util;

import android.util.Log;

/**
 * Created by jw362j on 6/1/2016.
 */
public class LogUtil {
    public static void log(String tag,String logContent){
        Log.d(tag, logContent);
    }
    public static void log(String logContent){
        log("mylog", logContent);
    }
}
