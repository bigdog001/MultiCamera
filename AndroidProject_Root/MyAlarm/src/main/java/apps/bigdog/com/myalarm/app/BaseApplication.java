package apps.bigdog.com.myalarm.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.hadoopz.MyDroidLib.app.MyApplication;
import com.hadoopz.MyDroidLib.exceptions.BaseExceptionHandler;
import com.hadoopz.MyDroidLib.exceptions.LocalFileHandler;

/**
 * Created by jw362j on 6/1/2016.
 */
public abstract class BaseApplication extends MyApplication {
    public static Context applicationContext;


    @Override
    public void onCreate()
    {
        super.onCreate();

        applicationContext = getApplicationContext();

        if (getDefaultUncaughtExceptionHandler() == null)
        {
            Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(applicationContext));
        }else{
            Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
        }
    }

    @Override
    public String[] initRTC() {
        return null;
    }

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();
}
