package com.android.sony.tv.activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.sony.tv.activity.base.BaseActivity;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.utils.Utils;
import com.hadoopz.MyDroidLib.inject.annotation.ContentView;
import com.android.sony.tv.R;
import com.hadoopz.MyDroidLib.inject.annotation.Event;
import com.hadoopz.MyDroidLib.inject.annotation.ViewInject;

@ContentView(value = R.layout.tv_health)
public class HealthCheckerActivity extends BaseActivity {

    @ViewInject(value = R.id.tv_unlock_btn)
    private Button tv_unlock_btn;
    @ViewInject(value = R.id.tv_unlock_code)
    private EditText tv_unlock_code;

    private BroadcastReceiver mHomeKeyReceiver = null;

    private static LockUnLockCallback lockUnLockCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lockUnLockCallback = new LockUnLockCallback() {
            @Override
            public void onLockEvent() {
                if(!Utils.isInLockInterval()){
                    VariableHolder.logProvider.d(getClass().getSimpleName(), " Time is good to go ,let`s enable the TV! " );
                    finish();
                }
            }
        };
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    @Event(value = {R.id.tv_unlock_btn}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (R.id.tv_unlock_btn) {
            case R.id.tv_unlock_btn:
                unLockTV();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        VariableHolder.logProvider.d(getClass().getSimpleName(), "the KEYCODE is:" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            VariableHolder.logProvider.d(getClass().getSimpleName(), "KeyEvent.KEYCODE_BACK is happenning");
            Toast.makeText(HealthCheckerActivity.this, "专治各种不服", Toast.LENGTH_LONG).show();
            if(Utils.isInLockInterval()){
                VariableHolder.logProvider.d(getClass().getSimpleName(), "Stay here in KeyEvent.KEYCODE_BACK  ");
                return true;//不执行父类点击事件    return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
            }else {
                VariableHolder.logProvider.d(getClass().getSimpleName(), "leave here in KeyEvent.KEYCODE_BACK ");
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void unLockTV() {
        String code = tv_unlock_code.getText().toString();
        VariableHolder.logProvider.d(getClass().getSimpleName(), "the code:" + code);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHomeKeyReceiver = new BroadcastReceiver() {
            private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
            private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
            private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
            private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
            private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();
                VariableHolder.logProvider.d(getClass().getSimpleName(),  "onReceive: action: " + action);
                if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
                    // android.intent.action.CLOSE_SYSTEM_DIALOGS
                    String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                    VariableHolder.logProvider.d(getClass().getSimpleName(), "reason: " + reason);
                    if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                        // 短按Home键
                        VariableHolder.logProvider.d(getClass().getSimpleName(),  "homekey");
//                        startMe(context);
                        Toast.makeText(context,"叫你再嚣张!",Toast.LENGTH_SHORT).show();
                    }
                    else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {
                        // 长按Home键 或者 activity切换键
                        VariableHolder.logProvider.d(getClass().getSimpleName(), "long press home key or activity switch");
                    }
                    else if (SYSTEM_DIALOG_REASON_LOCK.equals(reason)) {
                        // 锁屏
                        VariableHolder.logProvider.d(getClass().getSimpleName(),  "lock");
                    }
                    else if (SYSTEM_DIALOG_REASON_ASSIST.equals(reason)) {
                        // samsung 长按Home键
                        VariableHolder.logProvider.d(getClass().getSimpleName(),  "assist");
                    }

                }


            }
        };
        registerReceiver(mHomeKeyReceiver,new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    @Override
    protected void onPause() {
        if(mHomeKeyReceiver != null){
            unregisterReceiver(mHomeKeyReceiver);
        }
        super.onPause();
    }

    @Override
    public void OnStop() {

    }

    @Override
    protected void initParams() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lockUnLockCallback = null;
    }

    public static void startMe(Context context){
        Intent intent = new Intent(context, HealthCheckerActivity.class);
        context.startActivity(intent);
    }

    public static void tryToEnableTV(){
        if(lockUnLockCallback != null){
            lockUnLockCallback.onLockEvent();
        }
    }

    interface LockUnLockCallback{
        void onLockEvent();
    }
}
