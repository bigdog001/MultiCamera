package apps.bigdog.com.multicamera.config.onTimers;

import android.content.Context;
import android.provider.Settings;

import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.util.LogUtil;

/**
 * Created by jw362j on 6/1/2016.
 */
public class NothingToDo implements InterfaceGenerator.timerAction {
    private long lastEventTime;
    private long myDelta = 1000 * 10;//10 seconds

    @Override
    public boolean isAllowedToExecute() {
        if ((System.currentTimeMillis() - lastEventTime) > myDelta) {
            return true;
        }
        return false;
    }

    @Override
    public void onTime(Context c, int flag, Object data) {
        LogUtil.log("NothingToDo is working now...");
        lastEventTime = System.currentTimeMillis();
    }
}
