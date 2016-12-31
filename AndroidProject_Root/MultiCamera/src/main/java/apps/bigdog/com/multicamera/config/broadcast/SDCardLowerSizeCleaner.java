package apps.bigdog.com.multicamera.config.broadcast;

import android.content.Context;
import android.content.Intent;

import com.tool.mytool.lib.broadcast.BaseBroadCastRcv;

import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;

/**
 * Created by jw362j on 6/1/2016.
 */
public class SDCardLowerSizeCleaner extends BaseBroadCastRcv implements InterfaceGenerator.AppLifeCycle{
    public SDCardLowerSizeCleaner() {
        LocalApplication.addAppLifeCycle(this);
    }

    @Override
    public void onSafeReceive(Context context, Intent intent) {

    }

    @Override
    public void OnStop() {

    }
}
