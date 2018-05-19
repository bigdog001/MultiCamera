package apps.bigdog.com.myalarm.config.initors;

import android.content.Context;

import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.beans.VariableHolder;
import apps.bigdog.com.myalarm.config.InterfaceGenerator;
import com.hadoopz.MyDroidLib.util.JFileKit;
import com.hadoopz.MyDroidLib.util.MyLogUtil;

/**
 * Created by jw362j on 6/1/2016.
 */
public class MP4FilesStorageDirInit implements InterfaceGenerator.Initializer {
    @Override
    public void init(Context context) {
       String mp4_files = JFileKit.getMp4FileStorageDir(context,null);
        LocalApplication.getInstance().getVariableHolder().getSp().edit().putString(VariableHolder.Constants.MP4_FILE_STORAGED_IN_SP,mp4_files).commit();
        MyLogUtil.LogMe("the mp4 is:"+mp4_files);
    }
}
