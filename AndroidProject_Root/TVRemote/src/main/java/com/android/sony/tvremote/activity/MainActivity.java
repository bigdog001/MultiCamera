package com.android.sony.tvremote.activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.sony.tvremote.activity.base.BaseActivity;
import com.android.sony.tvremote.R;
import com.android.sony.tvremote.activity.utils.DeviceTokenManager;
import com.android.sony.tvremote.activity.utils.NetWorkLoader;
import com.android.sony.tvremote.activity.utils.VarsHolder;
import com.hadoopz.MyDroidLib.inject.annotation.ContentView;
import com.hadoopz.MyDroidLib.inject.annotation.Event;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import com.hadoopz.MyDroidLib.inject.annotation.ViewInject;
import com.mycomm.MyConveyor.core.Tasker;
import com.mycomm.YesHttp.core.JsonRequest;
import com.mycomm.YesHttp.core.Request;
import com.mycomm.YesHttp.core.Response;
import com.mycomm.YesHttp.core.TextBaseResponseListener;
import com.mycomm.YesHttp.core.YesHttpEngine;
import com.mycomm.YesHttp.core.YesHttpError;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private DatagramSocket socket;
    private int udp_port = 18888;
    private String currentRemoteIp;
    private String currentRemoteAddress;
    private String currentRemoteHostname;

//    private String registrationDeviceToken = "cQaM3G_-P6M:APA91bE0o_MZg9dJKTRmmo1uZrO9fenpKDRyJ5oQEbHoiacbHTrZ8IPI4F_pkkV7YAgQ3Fa2_UkS6VD8AiwqW8Z0OelW0uKcTKLvk0aCOzVLAleVbPS3vYOqiX6igoL4j7fbiwhT11pz";

    @ViewInject(value = R.id.the_url_add)
    private EditText the_url_add;
    @ViewInject(value = R.id.udp_sender_tv_status)
    private Button udp_sender_tv_status;

    @ViewInject(value = R.id.googlepush_sender_tv)
    private Button googlepush_sender_tv;
    private ProgressDialog mDialog;


    private Request.YesLog yeslog = new Request.YesLog() {
        @Override
        public void w(String text) {
            DefaultLogUtil.getInstance().w(getClass().getSimpleName(),text);
        }

        @Override
        public void e(String text) {
            DefaultLogUtil.getInstance().e(getClass().getSimpleName(),text);
        }

        @Override
        public void d(String text) {
            DefaultLogUtil.getInstance().d(getClass().getSimpleName(),text);
        }

        @Override
        public void i(String text) {
            DefaultLogUtil.getInstance().i(getClass().getSimpleName(),text);
        }

        @Override
        public void v(String text) {
            DefaultLogUtil.getInstance().v(getClass().getSimpleName(),text);
        }
    };

    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        public void run() {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "tv:" + DeviceTokenManager.getGoogleDeviceToken(getApplicationContext()), Toast.LENGTH_SHORT).show();
                }
            });

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        VariableHolder.logProvider.d(getClass().getSimpleName(), "radioBtn_home1:" + (radioBtn_home1));

    }


    @Event(value = {R.id.udp_sender_tv_status, R.id.googlepush_sender_tv}, type = View.OnClickListener.class)
    private void allListener(View view) {
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "the id in allListener:" + view.getId());
        switch (view.getId()) {
            case R.id.udp_sender_tv_status:
                FindTV();
                startTimer();
                break;
            case R.id.googlepush_sender_tv:
                googlePush();
                break;
            default:
                break;
        }
    }


    private void startTimer() {
        timer.schedule(task, 10000);
    }

    private void googlePush() {
        final String deviceToken = DeviceTokenManager.getGoogleDeviceToken(getApplicationContext());
        if (TextUtils.isEmpty(deviceToken)) {
            Toast.makeText(getApplicationContext(), "Please pair your TV first!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(the_url_add.getText())) {
            Toast.makeText(getApplicationContext(), "The url is null ,please enter the correct url!", Toast.LENGTH_SHORT).show();
            return;
        }
        NetWorkLoader.runHttpTask(new Tasker() {
            @Override
            public void onTask() {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "key=" + VarsHolder.serverKey);
                headers.put("Content-Type", "application/json");
                Request request = new JsonRequest("https://fcm.googleapis.com/fcm/send", new TextBaseResponseListener() {
                    @Override
                    public void responseMe(String msg) {
                        yeslog.d("the requestjSON TextBaseResponseListener.responseMe:" + msg);
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(YesHttpError error) {
                        yeslog.d("the requestjSON ErrorListener.onErrorResponse:" + error.getMessage());
                    }
                }, yeslog, Request.Protocol.HTTP) {
                    @Override
                    public Map<String, String> getParams() {
                        return null;
                    }

                    @Override
                    public Map getHeaders() {
                        return headers;
                    }

                    JSONObject jsonObject = null;

                    @Override
                    public String JsonBodyBuilder() {
                        if (jsonObject == null) {
                            jsonObject = new JSONObject();
                            try {
                                jsonObject.put("to", deviceToken);
                                JSONObject data = new JSONObject();
                                data.put("my_event", "open_url");
                                data.put("url4open", the_url_add.getText());
                                jsonObject.put("data", data);
                            } catch (JSONException e) {
                                yeslog.e(e.getMessage());
                            }
                        }
                        return jsonObject.toString();
                    }
                };
                YesHttpEngine.getYesHttpEngine().send(request);
            }
        });
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

    private void FindTV() {
        mDialog = ProgressDialog.show(MainActivity.this, null, "Loading TV...", true);
        NetWorkLoader.runHttpTask(new Tasker() {
            @Override
            public void onTask() {
                try {
                    socket = new DatagramSocket(udp_port);
                    DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "Start UDP Server at: " + udp_port);
                    byte data[] = new byte[512];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    socket.receive(packet);
                    currentRemoteIp = packet.getAddress().getHostAddress();
                    currentRemoteAddress = new String(packet.getAddress().getAddress());
                    currentRemoteHostname = packet.getAddress().getHostName();
                    DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "currentRemoteIp:" + currentRemoteIp + ",currentRemoteAddress:" + currentRemoteAddress + ",currentRemoteHostname:" + currentRemoteHostname);
                    String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
                    DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "the remote data is:" + result);
                    parseTV(result);
                } catch (IOException e) {
                    DefaultLogUtil.getInstance().e(getClass().getSimpleName(), "IOException in FindTV:" + e.getMessage());
                }
            }
        });
    }

    private void parseTV(String data) {
        if ("".equals(data) || data == null) {
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(data);
            String deviceToken;
            if (!jsonObject.has("deviceToken")) {
                return;
            }
            deviceToken = jsonObject.getString("deviceToken");
            DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "tv`s deviceToken:" + deviceToken);
            if (deviceToken == null || "".equals(deviceToken)) {
                return;
            }
            DeviceTokenManager.saveGoogleDeviceToken(getApplicationContext(), deviceToken);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mDialog != null && mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "TV paired successfully!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            DefaultLogUtil.getInstance().e(getClass().getSimpleName(), e.getMessage());
        }
    }

}
