package apps.bigdog.com.multicamera.config;

import android.content.Context;
import android.content.Intent;

import com.tool.mytool.lib.broadcast.BroadcastReceiverModule;

import java.util.ArrayList;
import java.util.List;

import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.config.broadcast.BatteryStatusListener;
import apps.bigdog.com.multicamera.config.broadcast.TimerManager;

/**
 * Created by jw362j on 6/1/2016.
 */
public class BroadCastManager extends AppObject {
    private static List<BroadcastReceiverModule> broadcastReceiverModules;
    private Context mContext;
    public BroadCastManager(Context c) {
        LocalApplication.addAppLifeCycle(BroadCastManager.this);
        mContext = c;
        broadcastReceiverModules = new ArrayList<BroadcastReceiverModule>();
        InitBroadCast();

    }

    public void InitBroadCast() {
        //系统的核心心脏
        List<String> intents4Timer = new ArrayList<String>();
        intents4Timer.add(VariableHolder.Constants.TIMER_BROADCAST_UNIT_NAME);
        broadcastReceiverModules.add(new BroadcastReceiverModule(new TimerManager(),intents4Timer,0));

        List<String> intents4Battery = new ArrayList<String>();
        intents4Battery.add(Intent.ACTION_BATTERY_CHANGED);
        intents4Battery.add(Intent.ACTION_POWER_CONNECTED);
        intents4Battery.add(Intent.ACTION_POWER_DISCONNECTED);
        broadcastReceiverModules.add(new BroadcastReceiverModule(new BatteryStatusListener(),intents4Battery,0));


        regist();
    }

    private void regist() {
        for(BroadcastReceiverModule brm:broadcastReceiverModules){
            if(brm != null )mContext.registerReceiver(brm.getBr(),brm.getIntentFilter());
        }
    }
    @Override
    public void OnStop() {
        if (broadcastReceiverModules != null) {
            for(BroadcastReceiverModule brm:broadcastReceiverModules){
                 mContext.unregisterReceiver(brm.getBr());
            }
            broadcastReceiverModules.clear();
            broadcastReceiverModules = null;
        }

    }
}
