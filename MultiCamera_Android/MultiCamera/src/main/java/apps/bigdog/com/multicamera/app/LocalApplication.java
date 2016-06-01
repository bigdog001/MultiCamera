package apps.bigdog.com.multicamera.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.DisplayMetrics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.config.BroadCastManager;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.exception.BaseExceptionHandler;
import apps.bigdog.com.multicamera.exception.LocalFileHandler;
import apps.bigdog.com.multicamera.util.JFileKit;

/**
 * Created by jw362j on 6/1/2016.
 */
public class LocalApplication extends BaseApplication implements InterfaceGenerator.ApplicationGlobalHolder,InterfaceGenerator.AppLifeCycle{
    private static LocalApplication instance;
    private VariableHolder variableHolder;
    private PendingIntent pi;
    private AlarmManager alarmManager;
    private static List<InterfaceGenerator.AppLifeCycle> apps ;
    private List<InterfaceGenerator.Initializer> initializers;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug( true);

        // 创建APP崩溃日志目录
        File appFolder = new File(JFileKit.getDiskCacheDir(this) + "/log");
        if (!appFolder.exists())
        {
            appFolder.mkdirs();
        }

        instance = this;
        init();
        // 得到屏幕的宽度和高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        variableHolder.setScreenW(dm.widthPixels);
        variableHolder.setScreenH(dm.heightPixels);
    }

    private void init() {
        variableHolder = new VariableHolder();
        apps = new ArrayList<InterfaceGenerator.AppLifeCycle>();
        apps.add(this);
        initTimer();
        initializers = new ArrayList<InterfaceGenerator.Initializer>();
        setUpInitializers();
        runInitializers();
        //初始化应用程序层广播
        BroadCastManager broadCastManager = new BroadCastManager(getApplicationContext());
        //系统初始化 在系统初始化前 各种广播必须注册上
    }

    private void setUpInitializers(){
//        initializers.add(new VariableInit());
    }

    private void runInitializers() {
        for (InterfaceGenerator.Initializer initializer:initializers) {
            if(initializer != null){
                initializer.init(getApplicationContext());
                initializer = null;
            }
        }
        initializers.clear();
        initializers = null;
    }

    private void initTimer(){
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction(VariableHolder.Constants.TIMER_BROADCAST_UNIT_NAME);
        pi = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                0, VariableHolder.Constants.INTERVAL_UNIT, pi);
    }

    public static LocalApplication getInstance()
    {
        return instance;
    }

    @Override
    public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new LocalFileHandler(applicationContext);
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
    public VariableHolder getVariableHolder() {
        return variableHolder;
    }

    @Override
    public void OnStop() {
        alarmManager.cancel(pi);
        alarmManager = null;
        pi = null;
    }
}
