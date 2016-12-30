package apps.bigdog.com.multicamera.config.initors;

import android.content.Context;

import com.tool.mytool.lib.util.JFileKit;
import com.tool.mytool.lib.util.LogUtil;

import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;

/**
 * Created by jw362j on 6/1/2016.
 */
public class MP4FilesStorageDirInit implements InterfaceGenerator.Initializer {
    @Override
    public void init(Context context) {
       String mp4_files = JFileKit.getMp4FileStorageDir(context,null);
        LocalApplication.getInstance().getVariableHolder().getSp().edit().putString(VariableHolder.Constants.MP4_FILE_STORAGED_IN_SP,mp4_files).commit();
        LogUtil.log("the mp4 is:"+mp4_files);
    }
}
