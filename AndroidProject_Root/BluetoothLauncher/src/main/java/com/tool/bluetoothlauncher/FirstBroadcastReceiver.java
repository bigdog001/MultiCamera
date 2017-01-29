package com.tool.bluetoothlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * Created by jw362j on 12/22/2016.
 */
public class FirstBroadcastReceiver extends BroadcastReceiver {
    private static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    private Context mContext;



    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.log("i am here in the BootBroadcastReceiver:"+intent.getAction());
        mContext = context;
        init();
    }
    private void init(){
        initTimer();
    }

    private void initTimer() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*30);
                    LogUtil.log("start the main activity...........");
                    Intent i=new Intent(mContext,MyActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
