package apps.bigdog.com.multicamera.config.broadcast;

import android.content.Context;
import android.content.Intent;

import com.tool.mytool.lib.broadcast.BaseBroadCastRcv;
import com.tool.mytool.lib.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.config.onTimers.NothingToDo;

/**
 * Created by jw362j on 6/1/2016.
 */
public class TimerManager extends BaseBroadCastRcv implements InterfaceGenerator.AppLifeCycle{
    private List<InterfaceGenerator.timerAction> timerActions;

    public TimerManager() {
        LocalApplication.addAppLifeCycle(TimerManager.this);
        timerActions = new ArrayList<InterfaceGenerator.timerAction>();
        initActions();
    }

    private void initActions() {
        timerActions.add(new NothingToDo());
//        //检测刻录线程是否在工作
//        timerActions.add(new recordStatusTimerAction());
//        //不断扫描dev下的video*是否具备0666 或者0777权限
//        timerActions.add(new DevScannerTimerAction());
//        timerActions.add(new SystemGCManager());
    }

    //负责接收系统的时钟脉冲 以决定什么时候发生什么事件
    @Override
    public void onSafeReceive(Context context, Intent intent) {

        LogUtil.log("脉冲到......");
        for (InterfaceGenerator.timerAction action : timerActions) {
            if (action != null && action.isAllowedToExecute()) {
                action.onTime(context, 0, null);
            }
        }

    }

    @Override
    public void OnStop() {
        if (timerActions != null) {
            timerActions.clear();
            timerActions = null;
        }
    }
}
