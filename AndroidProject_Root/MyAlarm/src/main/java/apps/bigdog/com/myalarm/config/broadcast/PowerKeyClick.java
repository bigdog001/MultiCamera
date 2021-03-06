package apps.bigdog.com.myalarm.config.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.telephony.SmsManager;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.beans.EmergencyContactor;
import apps.bigdog.com.myalarm.beans.VariableHolder;
import com.hadoopz.MyDroidLib.broadcast.BaseBroadCastRcv;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import apps.bigdog.com.myalarm.config.InterfaceGenerator;
import apps.bigdog.com.myalarm.util.SystemUtils;

/**
 * Created by leilei on 8/28/2016.
 */
public class PowerKeyClick extends BaseBroadCastRcv implements InterfaceGenerator.timerAction, InterfaceGenerator.AppLifeCycle{
    private long previousClick ;
    private boolean isAlarmOnGoing;
    private int clickCounter ;
    private Vibrator vibrator;
    long [] pattern_startIndicator = {100,400,100,400};   // 停止 开启 停止 开启
    private long lastEventTime;
    private long myDelta = 1000 * 60 * 60 * 2;// 2 hours
    private SmsManager smsManager;
    @Override
    public void onSafeReceive(Context context, Intent intent) {
        if (vibrator == null) {
            vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        }
        final String action = intent.getAction();
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"get power key click :"+action);

        runLogic(intent);
    }

    private void runLogic(Intent intent){
        //to check the last two onclick event which shorter than 2 second
        if ( (System.currentTimeMillis() - previousClick) < 2000 && !isAlarmOnGoing) {
            //ready,then check the logic chain...
            clickCounter ++;
            DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"ok,just double click the power key.....");
            if (clickCounter >= 3) {
                LocalApplication.getInstance().getVariableHolder().getSp().edit().putBoolean(VariableHolder.Constants.APP_ISINEMERGENCYMODEl_NAME,true);
                clickCounter = 0;
                isAlarmOnGoing =true;
                gotoEmergencyModel();
                vibrate();
                sendAlarm();
            }
        }
        previousClick = System.currentTimeMillis();
    }

    private void gotoEmergencyModel() {
        //keep the phone in silent model

        //turn off the LED indicator

        //intercept the power off event to prevent anybody shutdown the power

    }

    @Override
    public void OnStop() {
       if(vibrator != null) vibrator.cancel();
    }

    private void vibrate() {
        vibrator.vibrate(pattern_startIndicator,-1);
    }

    private void sendAlarm() {
        isAlarmOnGoing = true;
        if (smsManager == null) {
            smsManager = SmsManager.getDefault();
        }
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"ok========================================================================================.....");
        Map<String,EmergencyContactor> contactors = LocalApplication.getInstance().getVariableHolder().getContactors();
        if (contactors == null || contactors.size() <= 0) {
            //do not have any emergency contactors,please give ....
            isAlarmOnGoing = false;
            return;
        }

        Set<String> cellNumbers = contactors.keySet();
        String smsContent = "";
        for (String cellNumber:cellNumbers) {
            if (cellNumber == null || "".equals(cellNumber) ) {
                continue;
            }
            //give user`s current location (latitude and longitude) , battery status and current time
            smsContent = smsContent +"I am in dangerous,please help! Will give the location later! Battery remain:"+ LocalApplication.getInstance().getVariableHolder().getBatteryPercent()*(100f)+"%,Current time :"+new Date();
            smsManager.sendTextMessage(cellNumber, null, smsContent, null, null);
        }
        lastEventTime = System.currentTimeMillis();
        isAlarmOnGoing = false;
    }




    @Override
    public boolean isAllowedToExecute() {
        if ((System.currentTimeMillis() - lastEventTime) > myDelta && SystemUtils.isInEmergencyModel()) {
            return true;
        }
        return false;
    }

    @Override
    public void onTime(Context c, int flag, Object data) {

        sendAlarm();
        lastEventTime = System.currentTimeMillis();
    }
}
