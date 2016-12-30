package apps.bigdog.com.multicamera.config.onTimers;

import android.content.Context;

import com.tool.mytool.lib.util.LogUtil;

import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.fragment.BaseFragment;

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
