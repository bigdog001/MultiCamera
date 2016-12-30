package apps.bigdog.com.multicamera.config.initors;

import android.content.Context;

import com.tool.mytool.lib.util.JFileKit;

import java.io.File;

import apps.bigdog.com.multicamera.config.InterfaceGenerator;

/**
 * Created by jw362j on 6/1/2016.
 */
public class AppLogCachDirPrepare implements InterfaceGenerator.Initializer {
    @Override
    public void init(Context context) {
        // 创建APP崩溃日志目录
        File appFolder = new File(JFileKit.getDiskCacheDir(context) + "/log");
        if (!appFolder.exists())
        {
            appFolder.mkdirs();
        }
    }
}
