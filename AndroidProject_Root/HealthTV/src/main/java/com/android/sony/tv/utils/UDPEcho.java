package com.android.sony.tv.utils;

import android.text.TextUtils;
import com.android.sony.tv.beans.VariableHolder;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import com.mycomm.MyConveyor.core.Tasker;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPEcho {
    public static void UDPEcho( final String deviceToken) {
        if (TextUtils.isEmpty(deviceToken)) {
            return;
        }
        BusyTaskRunner.executeTask(new Tasker() {
            @Override
            public void onTask() {
                MulticastSocket sender;
                DatagramPacket dj;
                InetAddress group;
                String TVip = "255.255.255.255";
                int udp_port = VariableHolder.UDP_SERVER_PORT;
                JSONObject data_ = new JSONObject();
                try {
                    data_.put("deviceToken", deviceToken);
                    final byte[] data = data_.toString().getBytes();
                    sender = new MulticastSocket();
                    group = InetAddress.getByName(TVip);
                    dj = new DatagramPacket(data, data.length, group, udp_port);
                    sender.send(dj);
                    sender.close();
                } catch (JSONException e) {
                    DefaultLogUtil.getInstance().e(getClass().getSimpleName(), "JSONException in UDPEcho:" + e.getMessage());
                } catch (IOException e) {
                    DefaultLogUtil.getInstance().e(getClass().getSimpleName(), "IOException in UDPEcho:" + e.getMessage());
                }


            }
        });
    }
}
