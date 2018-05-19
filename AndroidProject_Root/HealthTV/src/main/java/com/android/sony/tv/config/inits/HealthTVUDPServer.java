package com.android.sony.tv.config.inits;

import android.content.Context;
import android.text.TextUtils;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.udpLogic.DataProcesser;
import com.android.sony.tv.config.udpLogic.OpenBrowser;
import com.android.sony.tv.config.udpLogic.UDPEcho;
import com.android.sony.tv.utils.BusyTaskRunner;
import com.android.sony.tv.utils.Event;
import com.mycomm.MyConveyor.core.Tasker;
import com.mycomm.itool.listener.T.TInitializer;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class HealthTVUDPServer implements TInitializer<Context> {
    private DatagramSocket socket;
    private Context mContext;
    private String currentRemoteIp;
    private String currentRemoteAddress;
    private String currentRemoteHostname;
    private List<DataProcesser> dataProcessers = new ArrayList<>();

    @Override
    public void onInit(Context context) {
        VariableHolder.logProvider.d(getClass().getSimpleName(), "onInit in  HealthTV UDP Server" + VariableHolder.UDP_SERVER_PORT);
        mContext = context;
        dataProcessers.add(new OpenBrowser());
        dataProcessers.add(new UDPEcho());
        BusyTaskRunner.executeTask(new Tasker() {
            @Override
            public void onTask() {
                try {
                    socket = new DatagramSocket(VariableHolder.UDP_SERVER_PORT);
                    VariableHolder.logProvider.d(getClass().getSimpleName(), "Start UDP Server at: " + VariableHolder.UDP_SERVER_PORT);
                    while (true) {
                        byte data[] = new byte[512];
                        DatagramPacket packet = new DatagramPacket(data, data.length);

                        try {
                            VariableHolder.logProvider.d(getClass().getSimpleName(), "UDP is waiting for client's request at:" + VariableHolder.UDP_SERVER_PORT);
                            socket.receive(packet);
                            currentRemoteIp = packet.getAddress().getHostAddress();
                            currentRemoteAddress = new String(packet.getAddress().getAddress());
                            currentRemoteHostname = packet.getAddress().getHostName();
                            VariableHolder.logProvider.d(getClass().getSimpleName(), "currentRemoteIp:" + currentRemoteIp+",currentRemoteAddress:"+currentRemoteAddress+",currentRemoteHostname:"+currentRemoteHostname);
                            String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
                            VariableHolder.logProvider.d(getClass().getSimpleName(), "receive client's data: " + result);
                            coreProcesser(result);
                        } catch (IOException e) {
                            VariableHolder.logProvider.e(getClass().getSimpleName(), e.getMessage());
                        }
                    }

                } catch (Exception e) {
                    VariableHolder.logProvider.e(getClass().getSimpleName(), e.getMessage());
                }

            }
        });
    }

    private void coreProcesser(String result) {
        if (result == null || TextUtils.isEmpty(result)) {
            VariableHolder.logProvider.d(getClass().getSimpleName(), "result is null at coreProcesser");
            return;
        }
        result = result.trim();
        try {
            JSONObject obj_tmp = new JSONObject(result);

            String my_event = null;

            if (obj_tmp.has("my_event")) {
                my_event = obj_tmp.getString("my_event");//open_browser
            }
            if(my_event == null || "".equals(my_event) ){
                VariableHolder.logProvider.d(getClass().getSimpleName(), "my_event is null at coreProcesser,give upe...");
                return;
            }
            Event myEvent = Event.fromValue(my_event);
            DataProcesser processer = findProcesser(myEvent);
            if(processer != null){
                obj_tmp.put("currentRemoteIp",currentRemoteIp);
                obj_tmp.put("currentRemoteAddress",currentRemoteAddress);
                obj_tmp.put("currentRemoteHostname",currentRemoteHostname);
                processer.process(mContext,obj_tmp);
            }
        } catch (JSONException e) {
            VariableHolder.logProvider.e(getClass().getSimpleName(), "JSONException in coreProcesser:" + e.getMessage());
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
