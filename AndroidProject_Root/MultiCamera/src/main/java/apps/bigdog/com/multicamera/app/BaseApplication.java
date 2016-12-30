package apps.bigdog.com.multicamera.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.tool.mytool.lib.app.MyApplication;

import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.exception.BaseExceptionHandler;
import apps.bigdog.com.multicamera.exception.LocalFileHandler;

/**
 * Created by jw362j on 6/1/2016.
 */
public abstract class BaseApplication extends MyApplication {
    public static Context applicationContext;
    private String [] rtc_config;

    @Override
    public void onCreate()
    {
        super.onCreate();

        applicationContext = getApplicationContext();

        if (getDefaultUncaughtExceptionHandler() == null)
        {
            Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(applicationContext));
        } else
        {
            Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
        }
    }


    @Override
    public String[] initRTC() {
        if(rtc_config == null){
            rtc_config = new String[]{VariableHolder.Constants.TIMER_BROADCAST_UNIT_NAME,VariableHolder.Constants.INTERVAL_UNIT+""};
        }
        return rtc_config;
    }

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();
}
