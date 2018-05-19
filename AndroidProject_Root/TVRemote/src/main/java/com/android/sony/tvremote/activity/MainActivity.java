package com.android.sony.tvremote.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.sony.tvremote.activity.base.BaseActivity;
import com.android.sony.tvremote.R;
import com.hadoopz.MyDroidLib.inject.annotation.ContentView;
import com.hadoopz.MyDroidLib.inject.annotation.Event;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import com.hadoopz.MyDroidLib.inject.annotation.ViewInject;
import com.mycomm.MyConveyor.core.AbsTask;
import com.mycomm.MyConveyor.core.MyConveyor;
import com.mycomm.MyConveyor.core.Tasker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static android.R.attr.data;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private MulticastSocket sender;
    private DatagramPacket dj;
    private InetAddress group;
    String TVip = "192.168.0.100";
    int udp_port = 18888;

    private JSONObject dataJson ;


    @ViewInject(value = R.id.the_url_add)
    private EditText the_url_add;
    @ViewInject(value = R.id.udp_sender)
    private Button udp_sender;
    @ViewInject(value = R.id.udp_sender_tv_status)
    private Button udp_sender_tv_status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        VariableHolder.logProvider.d(getClass().getSimpleName(), "radioBtn_home1:" + (radioBtn_home1));

    }


    @Event(value = {R.id.udp_sender, R.id.udp_sender_tv_status}, type = View.OnClickListener.class)
    private void allListener(View view) {
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "the id in allListener:" + view.getId());
        switch (view.getId()) {
            case R.id.udp_sender:
                sendUrlToTv();
                break;
            case R.id.udp_sender_tv_status:
                TVStatus();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isRecycled", true);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void TVStatus() {

        try {
            dataJson = new JSONObject();
            dataJson.put("my_event","udp_echo");
        } catch (JSONException e) {
            DefaultLogUtil.getInstance().e(getClass().getSimpleName(), e.getMessage());
        }
        Toast.makeText(getApplicationContext(), "Loading TV Status!", Toast.LENGTH_SHORT).show();
        netWorkSend();
    }

    private void sendUrlToTv() {
        if (TextUtils.isEmpty(the_url_add.getText())) {
            Toast.makeText(getApplicationContext(), "The url is null ,please enter the correct url!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            dataJson = new JSONObject();
            dataJson.put("url4open",the_url_add.getText());
            dataJson.put("my_event","open_url");
        } catch (JSONException e) {
            DefaultLogUtil.getInstance().e(getClass().getSimpleName(), e.getMessage());
        }
        Toast.makeText(getApplicationContext(), "TV will open the url soon!", Toast.LENGTH_SHORT).show();
        netWorkSend();
    }

    private void netWorkSend(){
        final byte[] data = dataJson.toString().getBytes();
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
                            e.printStackTrace();
                        }
                    }
                };
            }
        });
    }

}
