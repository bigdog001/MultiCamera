package apps.bigdog.com.myalarm.config.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import apps.bigdog.com.myalarm.config.BaseBroadCastRcv;
import apps.bigdog.com.myalarm.util.LogUtil;

/**
 * Created by leilei on 8/28/2016.
 */
public class PowerKeyClick extends BaseBroadCastRcv {
    private long previousClick ;
    private boolean isAlarmOnGoing;
    private int clickCounter ;
    private Vibrator vibrator;
    @Override
    public void onSafeReceive(Context context, Intent intent) {
        if (vibrator == null) {
            vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        }
        final String action = intent.getAction();
        LogUtil.log("get power key click :"+action);

        runLogic(intent);
    }

    private void runLogic(Intent intent){
        //to check the last two onclick event which shorter than 2 second
        if ( (System.currentTimeMillis() - previousClick) < 2000 && !isAlarmOnGoing) {
            //ready,then check the logic chain...
            clickCounter ++;
            LogUtil.log("ok,just double click the power key.....");
            if (clickCounter >= 3) {
                clickCounter = 0;
                isAlarmOnGoing =true;
                vibrate();
                sendAlarm();
            }
        }
        previousClick = System.currentTimeMillis();
    }

    private void sendAlarm() {
        LogUtil.log("ok========================================================================================.....");
        isAlarmOnGoing = false;
    }

    private void vibrate() {
        long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern,-1);
    }


    @Override
    public void OnStop() {
        vibrator.cancel();
    }
}
