package apps.bigdog.com.multicamera.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import com.tool.mytool.lib.exceptions.BaseExceptionHandler;
import com.tool.mytool.lib.exceptions.LocalFileHandler;
import java.util.ArrayList;
import java.util.List;
import org.xutils.BuildConfig;
import org.xutils.x;
import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.config.BroadCastManager;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.config.initors.AppLogCachDirPrepare;
import apps.bigdog.com.multicamera.config.initors.MP4FilesStorageDirInit;

/**
 * Created by jw362j on 6/1/2016.
 */
public class LocalApplication extends BaseApplication implements InterfaceGenerator.ApplicationGlobalHolder,InterfaceGenerator.AppLifeCycle{
    private static LocalApplication instance;
    private VariableHolder variableHolder;
    private static List<InterfaceGenerator.AppLifeCycle> apps ;
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
        initBroadCastRcvs();
    }

    private void initBroadCastRcvs() {
        new BroadCastManager(getApplicationContext());
    }

    private void initVariables() {
        instance = this;
        variableHolder = new VariableHolder();
        variableHolder.setCommunicatables( new ArrayList<InterfaceGenerator.ICommunicatable>());
        variableHolder.setInflater((LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        // 得到屏幕的宽度和高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        variableHolder.setScreenW(dm.widthPixels);
        variableHolder.setScreenH(dm.heightPixels);
        variableHolder.setSp(instance.getSharedPreferences(VariableHolder.Constants.APP_SH_NAME,MODE_PRIVATE));
        apps = new ArrayList<InterfaceGenerator.AppLifeCycle>();
        apps.add(this);
    }

    private void initXUtil() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
//        x.Ext.setDebug( true);
    }

    private void setUpInitializers(){
        initializers = new ArrayList<InterfaceGenerator.Initializer>();

        initializers.add(new AppLogCachDirPrepare());
        initializers.add(new MP4FilesStorageDirInit());
        for (InterfaceGenerator.Initializer initializer:initializers) {
            if(initializer != null){
                initializer.init(getApplicationContext());
                initializer = null;
            }
        }
        initializers.clear();
        initializers = null;

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
        stopMe();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
