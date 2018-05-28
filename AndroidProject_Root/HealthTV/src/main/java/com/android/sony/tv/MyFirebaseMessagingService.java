package com.android.sony.tv;

import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.remoteLogic.DataProcesser;
import com.android.sony.tv.utils.ClassUtils;
import com.android.sony.tv.utils.Event;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private List<DataProcesser> dataProcessers = new ArrayList<>();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(dataProcessers.size() <= 0){
            initDataProcesser();
        }

        VariableHolder.logProvider.d(getClass().getSimpleName(), "From: " + remoteMessage.getFrom());
        VariableHolder.logProvider.d(getClass().getSimpleName(), "remoteMessage: " + remoteMessage);
        VariableHolder.logProvider.d(getClass().getSimpleName(), "remoteMessage.getMessageId: " + remoteMessage.getMessageId());
        VariableHolder.logProvider.d(getClass().getSimpleName(), "remoteMessage.getMessageId: " + remoteMessage.getMessageType());
//        VariableHolder.logProvider.d(getClass().getSimpleName(), "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Map<String,String> data =remoteMessage.getData();
        VariableHolder.logProvider.d(getClass().getSimpleName(), "remoteMessage.getData: " + data);
        if(data != null && !data.isEmpty()){
            String my_event = data.get("my_event");
            VariableHolder.logProvider.d(getClass().getSimpleName(), "my_event is :"+my_event);
            if(my_event != null && !"".equals(my_event) ){
                Event myEvent = Event.fromValue(my_event);
                DataProcesser processer = findProcesser(myEvent);
                if(processer != null){
                    processer.process(getApplicationContext(),data);
                }
            }
        }
    }

    private void initDataProcesser(){
        List<String>  clzs = ClassUtils.getClassName(getApplicationContext(),"com.android.sony.tv.config.remoteLogic.impl");
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"the size of clzs:"+clzs.size());
        for (String clz : clzs) {
            DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"the clz:"+clz);
            Object obj;
            try {
                Class theClz = Class.forName(clz);
                obj = theClz.newInstance();
                if(obj == null){
                    continue;
                }
                if((obj instanceof DataProcesser)){
                    DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"the obj is DataProcesser type");
                    dataProcessers.add((DataProcesser)obj);
                }else {
                    DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"the obj is not DataProcesser type");
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                DefaultLogUtil.getInstance().e(getClass().getSimpleName(),e.getMessage());
            }
        }
    }

    private DataProcesser findProcesser(Event event){
        if(event == null){
            return null;
        }
        DataProcesser processer = null;
        for(DataProcesser dp:dataProcessers){
            if(dp == null){
                continue;
            }
            if(event.equals(dp.getEvent())){
                processer = dp;
                break;
            }
        }
        return processer;
    }
}
