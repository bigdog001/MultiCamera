package apps.bigdog.com.multicamera.config;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.config.broadcast.SDCardLowerSizeCleaner;
import apps.bigdog.com.multicamera.config.broadcast.TimerManager;

/**
 * Created by jw362j on 6/1/2016.
 */
public class BroadCastManager extends AppObject implements InterfaceGenerator.AppLifeCycle {
    private static List<BroadcastReceiverModule> broadcastReceiverModules;
    private Context mContext;
    public BroadCastManager(Context c) {
        mContext = c;
        broadcastReceiverModules = new ArrayList<BroadcastReceiverModule>();
        InitBroadCast();

    }

    public void InitBroadCast() {


        //系统的核心心脏
        List<String> intents4Timer = new ArrayList<String>();
        intents4Timer.add(VariableHolder.Constants.TIMER_BROADCAST_UNIT_NAME);
        broadcastReceiverModules.add(new BroadcastReceiverModule(new TimerManager(),intents4Timer,0));


        regist();
    }

    private void regist() {
        for(BroadcastReceiverModule brm:broadcastReceiverModules){
            if(brm != null )mContext.registerReceiver(brm.getBr(),brm.getIntentFilter());
        }
    }
    @Override
    public void OnStop() {
        broadcastReceiverModules.clear();
        broadcastReceiverModules = null;
    }
}
