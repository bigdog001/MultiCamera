package com.android.sony.tv.activity;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import com.android.sony.tv.activity.base.BaseActivity;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.R;
import com.hadoopz.MyDroidLib.inject.annotation.ContentView;
import com.hadoopz.MyDroidLib.inject.annotation.Event;
//import com.hadoopz.MyDroidLib.inject.annotation.ViewInject;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        VariableHolder.logProvider.d(getClass().getSimpleName(), "radioBtn_home1:" + (radioBtn_home1));

    }


    @Event(value = {},type = View.OnClickListener.class)
    private void allListener(View view) {
        VariableHolder.logProvider.d(getClass().getSimpleName(), "the id in allListener:" + view.getId());
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

    @Override
    public void OnStop() {

    }
}
