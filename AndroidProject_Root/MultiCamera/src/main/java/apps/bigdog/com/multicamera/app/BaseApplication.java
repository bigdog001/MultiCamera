package apps.bigdog.com.multicamera.app;

import android.content.Context;
import com.tool.mytool.lib.app.MyApplication;
import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.config.initors.AppLogCachDirPrepare;
import apps.bigdog.com.multicamera.config.initors.MP4FilesStorageDirInit;

/**
 * Created by jw362j on 6/1/2016.
 */
public abstract class BaseApplication extends MyApplication {
    public static Context applicationContext;
    private String [] rtc_config;
    private InterfaceGenerator.Initializer[]initializers;

    @Override
    public void onCreate(){
        super.onCreate();
        applicationContext = getApplicationContext();
    }


    @Override
    public String[] initRTC() {
        if(rtc_config == null){
            rtc_config = new String[]{VariableHolder.Constants.TIMER_BROADCAST_UNIT_NAME,VariableHolder.Constants.INTERVAL_UNIT+""};
        }
        return rtc_config;
    }

    protected InterfaceGenerator.Initializer[]LoadInitializers(){
        if (initializers == null) {
            initializers = new InterfaceGenerator.Initializer[]{
                    new AppLogCachDirPrepare(),
                    new MP4FilesStorageDirInit()
            };
        }
        return initializers;
    }


}
