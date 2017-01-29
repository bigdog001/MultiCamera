package com.tool.bluetoothlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jw362j on 12/22/2016.
 */
public class IntervalChecker extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.log("in IntervalChecker......");
        if ((BluetoothManager.isBluetoothSupported())
                && (!BluetoothManager.isBluetoothEnabled())){
            boolean result  =  BluetoothManager.turnOnBluetooth();
            LogUtil.log("bluetooth status result in IntervalChecker is:"+result);
        }
    }
}
