package apps.bigdog.com.myalarm.config.broadcast;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import apps.bigdog.com.myalarm.config.BaseBroadCastRcv;
import apps.bigdog.com.myalarm.util.LogUtil;

/**
 * Created by leilei on 8/28/2016.
 */
public class PowerKeyClick extends BaseBroadCastRcv {
    private long previousClick ;
    private boolean isAlarmOnGoing;
    @Override
    public void onSafeReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        LogUtil.log("get power key click :"+action);
        runLogic(intent);
    }

    private void runLogic(Intent intent){
        //to check the last two onclick event which shorter than 2 second
        if ( (System.currentTimeMillis() - previousClick) < 2000 && !isAlarmOnGoing) {
            //ready,then check the logic chain...
            isAlarmOnGoing = true;
            LogUtil.log("ok,just double click the power key.....");
        }
        previousClick = System.currentTimeMillis();
    }

    @Override
    public void OnStop() {

    }
}
