package com.android.sony.tvremote.activity.base;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import com.hadoopz.MyDroidLib.util.ActivityStack;
import com.hadoopz.MyDroidLib.inject.y;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public abstract class BaseActivity extends FragmentActivity  {
    private static final String TAG = "BaseActivity";
    protected Dialog dialog;
    private final int RW_PERMISSION_REQUEST_CODE = 20;
    private final String[] RW_PERMISSIONS = new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
    private boolean isCreate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        y.view().inject(this);
        ActivityStack.getInstance().addActivity(this);
        isCreate = true;
        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        int readPermissionCheck = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        int writePermissionCheck = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        if (readPermissionCheck != PackageManager.PERMISSION_GRANTED && writePermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(BaseActivity.this, RW_PERMISSIONS, RW_PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case RW_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        DefaultLogUtil.getInstance().d(TAG,"==> got sdcard read write permission.....");
                    }
                }
            default:
                break;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (isCreate)
        {
            isCreate = false;
            initParams();
        }
    }
    /**
     * 参数设置
     *
     * @author blue
     */
    protected abstract void initParams();


    /**
     * 关闭对话框
     *
     * @author blue
     */
    public void dismissDialog()
    {
        if (null != dialog && dialog.isShowing())
        {
            dialog.dismiss();
        }
    }

    @Override
    protected synchronized void onDestroy()
    {
        dismissDialog();
        ActivityStack.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
