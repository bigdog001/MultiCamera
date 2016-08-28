package apps.bigdog.com.multicamera.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import apps.bigdog.com.multicamera.exception.BaseExceptionHandler;
import apps.bigdog.com.multicamera.exception.LocalFileHandler;

/**
 * Created by jw362j on 6/1/2016.
 */
public abstract class BaseApplication extends Application {
    public static Context applicationContext;


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

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();
}
