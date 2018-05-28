package com.android.sony.tv.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.sony.tv.activity.base.BaseActivity;
import com.android.sony.tv.activity.ui.SettingsActivity;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.R;
import com.hadoopz.MyDroidLib.inject.annotation.ContentView;
import com.hadoopz.MyDroidLib.inject.annotation.Event;
import com.hadoopz.MyDroidLib.inject.annotation.ViewInject;
//import com.hadoopz.MyDroidLib.inject.annotation.ViewInject;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends BaseActivity{
    @ViewInject(value = R.id.testseting)
    private Button testseting ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        VariableHolder.logProvider.d(getClass().getSimpleName(), "radioBtn_home1:" + (radioBtn_home1));

    }

    @Event(value = {R.id.testseting},type = View.OnClickListener.class)
    private void allListener(View view) {
        VariableHolder.logProvider.d(getClass().getSimpleName(), "the id in allListener:" + view.getId());
        switch (view.getId()){
            case R.id.testseting:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
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
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void OnStop() {

    }
}
