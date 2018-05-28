package com.android.sony.tv.config.remoteLogic.impl;

import android.content.Context;
import android.widget.Toast;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.remoteLogic.DataProcesser;
import com.android.sony.tv.utils.Event;
import com.mycomm.MyConveyor.core.AbsTask;
import com.mycomm.MyConveyor.core.MyConveyor;
import com.mycomm.MyConveyor.core.Tasker;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Map;

public class UDPEcho implements DataProcesser {
    private MulticastSocket sender;
    private DatagramPacket dj;
    private InetAddress group;
    String TVip = "255.255.255.255";
    int udp_port = 18889;
    private Context mContext;


    @Override
    public void process(Context context, Map<String,String> data) {
        mContext = context;
        VariableHolder.logProvider.d(getClass().getSimpleName(), "UDPEcho :The TV status:OK");
//        sendUrlToTv();
    }




    @Override
    public Event getEvent() {
        return Event.Event_Echo;
    }


    private void sendUrlToTv() {
        JSONObject data_ = new JSONObject();
        try {
            data_.put("status", "OK");
        } catch (JSONException e) {
            VariableHolder.logProvider.e(getClass().getSimpleName(), "JSONException in UDPEcho:" + e.getMessage());
        }
        final byte[] data = data_.toString().getBytes();
        Toast.makeText(mContext, "TV will open the url soon!", Toast.LENGTH_SHORT).show();
        MyConveyor.getInstance().execute(new AbsTask() {
            @Override
            public Tasker getTask() {
                return new Tasker() {
                    @Override
                    public void onTask() {
                        try {
                            sender = new MulticastSocket();
                            group = InetAddress.getByName(TVip);
                            dj = new DatagramPacket(data, data.length, group, udp_port);
                            sender.send(dj);
                            sender.close();
                        } catch (IOException e) {
                            VariableHolder.logProvider.e(getClass().getSimpleName(), "IOException in UDPEcho:" + e.getMessage());
                        }
                    }
                };
            }
        });
    }

    public String toString() {
        return "UDPEcho class";
    }
}
