package com.android.sony.tv.activity.base;
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
import com.android.sony.tv.app.LocalApplication;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.InterfaceGenerator;
import com.android.sony.tv.view.DialogMaker;
import com.hadoopz.MyDroidLib.util.ActivityStack;
import com.hadoopz.MyDroidLib.inject.y;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public abstract class BaseActivity extends FragmentActivity implements DialogMaker.DialogCallBack,InterfaceGenerator.AppLifeCycle {
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
        LocalApplication.addAppLifeCycle(this);
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
                        VariableHolder.logProvider.d(TAG,"==> got sdcard read write permission.....");
                    }
                }
            default:
                break;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration config) {
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
     * 弹出对话框
     *
     * @author blue
     */
    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag)
    {
        if (null == dialog || !dialog.isShowing())
        {
            dialog = DialogMaker.showCommonAlertDialog(this, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag);
        }
        return dialog;
    }

    /**
     * 等待对话框
     *
     * @author blue
     */
    public Dialog showWaitDialog(String msg, boolean isCanCancelabel, Object tag)
    {
        if (null == dialog || !dialog.isShowing())
        {
            dialog = DialogMaker.showCommenWaitDialog(this, msg, this, isCanCancelabel, tag);
        }
        return dialog;
    }

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
    public void onButtonClicked(Dialog dialog, int position, Object tag)
    {
    }

    @Override
    public void onCancelDialog(Dialog dialog, Object tag)
    {
    }

    @Override
    protected synchronized void onDestroy()
    {
        dismissDialog();
        ActivityStack.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
