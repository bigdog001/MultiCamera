package com.android.sony.tv.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.sony.tv.config.inits.TVCodeHandler;
import com.android.sony.tv.config.inits.TVServiceStart;
import com.android.sony.tv.config.inits.DeviceStatistics;
import com.hadoopz.MyDroidLib.app.MyApplication;
import com.hadoopz.MyDroidLib.exceptions.BaseExceptionHandler;
import com.hadoopz.MyDroidLib.exceptions.LocalFileHandler;
import java.util.ArrayList;
import java.util.List;
import com.hadoopz.MyDroidLib.inject.y;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.InterfaceGenerator;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import com.hadoopz.MyDroidLib.util.DroidLogProvider;
import com.hadoopz.MyDroidLib.util.TimeInterval;
import com.mycomm.itool.listener.T.MyLifeCycle;
import com.mycomm.itool.listener.T.MyTListener;
import com.mycomm.itool.listener.T.TCleaner;
import com.mycomm.itool.listener.T.TInitializer;
import com.mycomm.itool.listener.T.TScanner;
import com.mycomm.itool.logs.TheLogger;

public class LocalApplication extends MyApplication implements MyLifeCycle<Context> {
    private TimeInterval timeInterval ;
    private static LocalApplication instance;
    private static List<InterfaceGenerator.AppLifeCycle> apps ;
    private TheLogger theLogger = new TheLogger() {
        @Override
        public void info(String msg) {
            DefaultLogUtil.getInstance().i("LocalApplication",msg);
        }

        @Override
        public void debug(String msg) {
            DefaultLogUtil.getInstance().d("LocalApplication",msg);
        }

        @Override
        public void error(String msg) {
            DefaultLogUtil.getInstance().e("LocalApplication",msg);
        }
    };
    @Override
    public void onCreate() {
        y.logProvider = new DroidLogProvider() {
            @Override
            public void w(String tag, String text) {
                Log.w(tag,text);
            }

            @Override
            public void e(String tag, String text) {
                Log.e(tag,text);
            }

            @Override
            public void d(String tag, String text) {
                Log.d(tag,text);
            }

            @Override
            public void i(String tag, String text) {
                Log.i(tag,text);
            }

            @Override
            public void v(String tag, String text) {
                Log.v(tag,text);
            }
        };
        VariableHolder.logProvider = DefaultLogUtil.getInstance();
        super.onCreate();
        MyTListener<Context> myTListener = new MyTListener<>(LocalApplication.this);
        myTListener.TCreated(LocalApplication.this);
    }

    public static LocalApplication getInstance()
    {
        return instance;
    }

    @Override
    public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new LocalFileHandler(getApplicationContext());
    }

    @Override
    public TimeInterval initRTC() {
        if(timeInterval == null){
            timeInterval = new TimeInterval() {
                @Override
                public long getInterval() {
                    return 0;
                }
            };
        }
        return timeInterval;
    }

    @Override
    public void onTimer(Context context) {
        VariableHolder.logProvider.d(getClass().getSimpleName(),"the timer is ready now .......");
    }

    public static void addAppLifeCycle(InterfaceGenerator.AppLifeCycle app) {
        if (!apps.contains(app)) {
            apps.add(app);
        }
    }

    public void stopAll(){
        for (InterfaceGenerator.AppLifeCycle stop : apps) {
            if (stop != null) {
                stop.OnStop();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void LoadInitializers(List<TInitializer<Context>> tInitializers) {
        tInitializers.add(new TInitializer<Context>() {
            @Override
            public void onInit(Context context) {
                y.init(LocalApplication.this);

                instance = LocalApplication.this;
                // 得到屏幕的宽度和高度
                DisplayMetrics dm = getResources().getDisplayMetrics();
                VariableHolder.screenW = dm.widthPixels;
                VariableHolder.screenH = dm.heightPixels;
                apps = new ArrayList<>();

            }
        });
        tInitializers.add(new DeviceStatistics());
        tInitializers.add(new TVServiceStart());
        tInitializers.add(new TVCodeHandler());
    }

    @Override
    public void LoadScanner(List<TScanner<Context>> tScanners) {

    }

    @Override
    public void LoadCleaners(List<TCleaner<Context>> tCleaners) {

    }

    @Override
    public long getScannerPeriod() {
        return 0;
    }

    @Override
    public TheLogger loadLogger() {
        return theLogger;
    }
}
