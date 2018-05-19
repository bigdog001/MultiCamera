package com.android.sony.tv.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.sony.tv.activity.base.BaseActivity;
import com.android.sony.tv.beans.VariableHolder;
import com.hadoopz.MyDroidLib.inject.annotation.ContentView;
import com.android.sony.tv.R;
import com.hadoopz.MyDroidLib.inject.annotation.Event;
import com.hadoopz.MyDroidLib.inject.annotation.ViewInject;

@ContentView(value = R.layout.tv_health)
public class HealthChecker extends BaseActivity {

    @ViewInject(value = R.id.tv_unlock_btn)
    private Button tv_unlock_btn;
    @ViewInject(value = R.id.tv_unlock_code)
    private EditText tv_unlock_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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

    private void unLockTV() {
        String code = tv_unlock_code.getText().toString();
        VariableHolder.logProvider.d(getClass().getSimpleName(), "the enter code:"+code);
    }

    @Override
    public void OnStop() {

    }

    @Override
    protected void initParams() {

    }
}
