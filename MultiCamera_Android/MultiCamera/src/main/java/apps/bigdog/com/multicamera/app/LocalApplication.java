package apps.bigdog.com.multicamera.app;

import android.util.DisplayMetrics;

import java.io.File;
import org.xutils.x;
import apps.bigdog.com.multicamera.exception.BaseExceptionHandler;
import apps.bigdog.com.multicamera.exception.LocalFileHandler;
import apps.bigdog.com.multicamera.util.JFileKit;

/**
 * Created by jw362j on 6/1/2016.
 */
public class LocalApplication extends BaseApplication {
    private static LocalApplication instance;
    // 当前屏幕的高宽
    public int screenW = 0;
    public int screenH = 0;

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

        // 得到屏幕的宽度和高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;
    }
    public static LocalApplication getInstance()
    {
        return instance;
    }

    @Override
    public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new LocalFileHandler(applicationContext);
    }

}
