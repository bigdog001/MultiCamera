package apps.bigdog.com.multicamera.app;

import android.content.Context;
import com.hadoopz.MyDroidLib.app.MyApplication;
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
