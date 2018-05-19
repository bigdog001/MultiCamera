package com.android.sony.tv.config.broadcast;

import android.content.Context;
import android.content.Intent;

import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.InterfaceGenerator;
import com.android.sony.tv.config.TVService;
import com.hadoopz.MyDroidLib.broadcast.BaseBroadCastRcv;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;

public class BootBroadcastReceiver extends BaseBroadCastRcv implements InterfaceGenerator.AppLifeCycle {
    @Override
    public void onSafeReceive(Context context, Intent intent) {
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"TV start in BootBroadcastReceiver......");
        Intent intent1 = new Intent(context, TVService.class);
        context.startService(intent1);
    }

    @Override
    public void OnStop() {

    }
}