package com.android.sony.tv.config.broadcast;

import android.content.Context;
import android.content.Intent;
import com.android.sony.tv.config.InterfaceGenerator;
import com.hadoopz.MyDroidLib.broadcast.BaseBroadCastRcv;

import java.util.ArrayList;
import java.util.List;

public class TimerManager extends BaseBroadCastRcv{
    private final List<InterfaceGenerator.timerAction> timerActions;

    public TimerManager() {
        timerActions = new ArrayList<>();
        initActions();
    }

    private void initActions() {
        timerActions.add(new TVHealthLogic());
    }

    @Override
    public void onSafeReceive(Context context, Intent intent) {
        for (InterfaceGenerator.timerAction action : timerActions) {
            if (action != null && action.isAllowedToExecute()) {
                action.onTime(context, 0, null);
            }
        }

    }
}
