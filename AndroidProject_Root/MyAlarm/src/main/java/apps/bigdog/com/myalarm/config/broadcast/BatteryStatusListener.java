package apps.bigdog.com.myalarm.config.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.hadoopz.MyDroidLib.util.MyLogUtil;

import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.config.InterfaceGenerator;

import com.hadoopz.MyDroidLib.broadcast.BaseBroadCastRcv;

/**
 * Created by jw362j on 6/3/2016.
 */
public class BatteryStatusListener extends BaseBroadCastRcv  implements InterfaceGenerator.AppLifeCycle{
    @Override
    public void onSafeReceive(Context context, Intent intent) {

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        //当前剩余电量
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        //电量最大值
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        //电量百分比
        float batteryPct = level / (float) scale;
        LocalApplication.getInstance().getVariableHolder().setBatteryPercent(batteryPct);
        MyLogUtil.LogMe("isCharging :"+isCharging+",usbCharge:"+usbCharge+",acCharge:"+acCharge+",batteryPct:"+batteryPct);
    }

    @Override
    public void OnStop() {

    }
}
