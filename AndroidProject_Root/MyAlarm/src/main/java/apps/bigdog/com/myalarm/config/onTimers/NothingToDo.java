package apps.bigdog.com.myalarm.config.onTimers;

import android.content.Context;
import android.provider.Settings;

import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.config.InterfaceGenerator;
import apps.bigdog.com.myalarm.fragment.BaseFragment;
import apps.bigdog.com.myalarm.util.LogUtil;

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
        if (LocalApplication.getInstance().getVariableHolder().getCommunicatables() != null) {
            LogUtil.log("the length of communicatables is :"+ LocalApplication.getInstance().getVariableHolder().getCommunicatables().size());
            for (InterfaceGenerator.ICommunicatable cx: LocalApplication.getInstance().getVariableHolder().getCommunicatables()) {
                if( cx != null )cx.DataIn("...");
            }
        }
        lastEventTime = System.currentTimeMillis();
    }
}
