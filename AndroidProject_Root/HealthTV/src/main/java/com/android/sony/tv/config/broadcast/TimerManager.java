package com.android.sony.tv.config.broadcast;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.InterfaceGenerator;
import com.android.sony.tv.config.TVService;
import com.android.sony.tv.utils.ClassUtils;
import com.android.sony.tv.utils.DeviceTokenManager;
import com.android.sony.tv.utils.UDPEcho;
import com.hadoopz.MyDroidLib.broadcast.BaseBroadCastRcv;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import java.util.ArrayList;
import java.util.List;

public class TimerManager extends BaseBroadCastRcv{
    private static List<InterfaceGenerator.timerAction> timerActions;
    private static boolean deviceTokenExchange;
    private void initActions(Context context) {
        List<String> clzs = ClassUtils.getClassName(context,"com.android.sony.tv.config.scanner");
        if(clzs == null || clzs.size() <= 0){
            VariableHolder.logProvider.d(getClass().getSimpleName(),"clzs is null in TimerManager...");
            return;
        }
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"in TimerManager,the size of clzs :"+clzs.size());
        for (String clz : clzs) {
            DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"in TimerManager, the clz:"+clz);
            Object obj;
            try {
                Class theClz = Class.forName(clz);
                obj = theClz.newInstance();
                if(obj == null){
                    continue;
                }
                if((obj instanceof InterfaceGenerator.timerAction)){
                    DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"the obj is InterfaceGenerator.timerAction type");
                    timerActions.add((InterfaceGenerator.timerAction)obj);
                }else {
                    DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"the obj is not InterfaceGenerator.timerAction type");
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                DefaultLogUtil.getInstance().e(getClass().getSimpleName(),e.getMessage());
            }
        }
    }

    @Override
    public void onSafeReceive(Context context, Intent intent) {

        if("android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction())){

            VariableHolder.logProvider.d(getClass().getSimpleName(), "in VolumeChange broadcast  is running...");
            Intent intentTVService = new Intent(context, TVService.class);
            context.startService(intentTVService);

            AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
            int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            VariableHolder.logProvider.d(getClass().getSimpleName(), "in VolumeChange:"+streamVolume);
            if (streamVolume == 8) {
                deviceTokenExchange = false;
            }
            if (streamVolume == 2 && !deviceTokenExchange) {
                deviceTokenExchange = true;
                //send broadcast to 255.255.255.255
                String deviceToken = DeviceTokenManager.getGoogleDeviceToken(context);
                if(deviceToken == null || "".equals(deviceToken)){
                    Toast.makeText(context,"The device Token is null !",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context,"TV is paring now,please open you phone APP !",Toast.LENGTH_SHORT).show();
                UDPEcho.UDPEcho(deviceToken);
            }
        }

        if(timerActions == null){
            timerActions = new ArrayList<>();
            initActions(context);
        }
        for (InterfaceGenerator.timerAction action : timerActions) {
            if (action != null && action.isAllowedToExecute()) {
                action.onTime(context, 0, null);
            }
        }

    }
}
