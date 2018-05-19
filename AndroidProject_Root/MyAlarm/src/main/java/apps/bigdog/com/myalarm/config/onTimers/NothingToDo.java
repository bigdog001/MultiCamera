package apps.bigdog.com.myalarm.config.onTimers;

import android.content.Context;
import com.hadoopz.MyDroidLib.util.MyLogUtil;
import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.config.InterfaceGenerator;

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
            MyLogUtil.LogMe("the length of communicatables is :"+ LocalApplication.getInstance().getVariableHolder().getCommunicatables().size());
            for (InterfaceGenerator.ICommunicatable cx: LocalApplication.getInstance().getVariableHolder().getCommunicatables()) {
                if( cx != null )cx.DataIn("...");
            }
        }
        lastEventTime = System.currentTimeMillis();
    }
}
