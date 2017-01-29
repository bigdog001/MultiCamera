package com.tool.bluetoothlauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;

public class MyActivity extends Activity{
    private TextView activity_ResultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        activity_ResultTextView = (TextView) findViewById(R.id.activity_ResultTextView);
        LogUtil.log("start the service in MyActivity.onCreate...");
        startService(new Intent(MyActivity.this,MyService.class));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);//延时5s
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void checkBluetooth(View v){
        activity_ResultTextView.setText( ""+ BluetoothManager.turnOnBluetooth());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.log("i am onDestroy....");
    }
}
