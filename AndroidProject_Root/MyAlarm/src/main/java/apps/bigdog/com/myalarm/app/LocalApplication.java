package apps.bigdog.com.myalarm.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.xutils.BuildConfig;
import org.xutils.x;

import apps.bigdog.com.myalarm.beans.EmergencyContactor;
import apps.bigdog.com.myalarm.beans.VariableHolder;
import apps.bigdog.com.myalarm.config.BroadCastManager;
import apps.bigdog.com.myalarm.config.InterfaceGenerator;
import apps.bigdog.com.myalarm.config.initors.AppLogCachDirPrepare;
import apps.bigdog.com.myalarm.config.initors.MP4FilesStorageDirInit;
import com.hadoopz.MyDroidLib.exceptions.BaseExceptionHandler;
import com.hadoopz.MyDroidLib.exceptions.LocalFileHandler;
import com.hadoopz.MyDroidLib.util.JFileKit;
import com.hadoopz.MyDroidLib.util.TimeInterval;

import apps.bigdog.com.myalarm.util.SystemUtils;

/**
 * Created by jw362j on 6/1/2016.
 */
public class LocalApplication extends BaseApplication implements InterfaceGenerator.ApplicationGlobalHolder, InterfaceGenerator.AppLifeCycle {
    private static LocalApplication instance;
    private VariableHolder variableHolder;
    private PendingIntent pi;
    private AlarmManager alarmManager;
    private static List<InterfaceGenerator.AppLifeCycle> apps;
    private List<InterfaceGenerator.Initializer> initializers;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initXUtil();
        initVariables();
        setUpInitializers();
        initTimer();
        initBroadCastRcvs();
    }

    private void initBroadCastRcvs() {
        new BroadCastManager(getApplicationContext());
    }

    private void initVariables() {
        instance = this;
        variableHolder = new VariableHolder();
        variableHolder.setCommunicatables(new ArrayList<InterfaceGenerator.ICommunicatable>());
        variableHolder.setContactors(new HashMap<String, EmergencyContactor>());
        variableHolder.setInflater((LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        // 得到屏幕的宽度和高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        variableHolder.setScreenW(dm.widthPixels);
        variableHolder.setScreenH(dm.heightPixels);
        variableHolder.setSp(instance.getSharedPreferences(VariableHolder.Constants.APP_SH_NAME, MODE_PRIVATE));
        apps = new ArrayList<InterfaceGenerator.AppLifeCycle>();
        apps.add(this);
        loadEmergencyConactors();
    }

    private void loadEmergencyConactors() {
       SystemUtils.reloadContactor();
    }


    private void initXUtil() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
//        x.Ext.setDebug( true);
    }

    private void setUpInitializers() {
        initializers = new ArrayList<InterfaceGenerator.Initializer>();

        initializers.add(new AppLogCachDirPrepare());
        initializers.add(new MP4FilesStorageDirInit());
        for (InterfaceGenerator.Initializer initializer : initializers) {
            if (initializer != null) {
                initializer.init(getApplicationContext());
                initializer = null;
            }
        }
        initializers.clear();
        initializers = null;

    }


    private void initTimer() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction(VariableHolder.Constants.TIMER_BROADCAST_UNIT_NAME);
        pi = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                0, VariableHolder.Constants.INTERVAL_UNIT, pi);
    }

    public static LocalApplication getInstance() {
        return instance;
    }

    @Override
    public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new LocalFileHandler(applicationContext);
    }

    @Override
    public TimeInterval initRTC() {
        return null;
    }

    @Override
    public void onTimer(Context context) {

    }

    public static void addAppLifeCycle(InterfaceGenerator.AppLifeCycle app) {
        if (!apps.contains(app)) {
            apps.add(app);
        }
    }

    public void stopAll() {
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


    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
