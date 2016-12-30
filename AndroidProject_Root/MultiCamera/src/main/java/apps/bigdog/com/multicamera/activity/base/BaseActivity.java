package apps.bigdog.com.multicamera.activity.base;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.tool.mytool.lib.util.ActivityStack;

import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.view.DialogMaker;
import org.xutils.x;
/**
 * Created by jw362j on 6/1/2016.
 */
public abstract class BaseActivity extends FragmentActivity implements DialogMaker.DialogCallBack,InterfaceGenerator.AppLifeCycle {

    protected Dialog dialog;

    private boolean isCreate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityStack.getInstance().addActivity(this);
        LocalApplication.addAppLifeCycle(this);
        setContentView(getLayoutId());
        x.view().inject(this);
        isCreate = true;
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
//        super.onConfigurationChanged(config);
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

    protected abstract int getLayoutId();
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
