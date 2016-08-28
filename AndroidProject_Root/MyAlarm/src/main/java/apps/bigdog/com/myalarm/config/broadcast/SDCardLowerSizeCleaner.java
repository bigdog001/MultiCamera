package apps.bigdog.com.myalarm.config.broadcast;

import android.content.Context;
import android.content.Intent;

import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.config.BaseBroadCastRcv;
import apps.bigdog.com.myalarm.config.InterfaceGenerator;

/**
 * Created by jw362j on 6/1/2016.
 */
public class SDCardLowerSizeCleaner extends BaseBroadCastRcv {
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
