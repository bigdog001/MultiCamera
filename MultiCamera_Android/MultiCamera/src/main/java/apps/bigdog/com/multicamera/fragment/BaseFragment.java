package apps.bigdog.com.multicamera.fragment;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.xutils.x;

import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.view.DialogMaker;

/**
 * Created by jw362j on 6/2/2016.
 */
public abstract class BaseFragment  extends Fragment implements InterfaceGenerator.AppLifeCycle,InterfaceGenerator.ICommunicatable{
    public FragmentActivity mActivity;
    protected Dialog dialog;
    private View mView;
    private boolean injected = false;
    protected boolean isCommunicatable ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalApplication.addAppLifeCycle(this);
        LocalApplication.getInstance().getVariableHolder().getCommunicatables().add(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        mActivity = getActivity();
        if (mView == null && getActivity() != null)
        {
//            mView = inflater.inflate(getLayoutId(), container, false);
            mView = x.view().inject(this, inflater, container);
            if (savedInstanceState != null)
                onRestoreInstanceState(savedInstanceState);
//            x.view().inject(this, mView);
            injected = true;
            initParams();
        } else if (mView != null)
        {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null)
            {
                parent.removeView(mView);
            }
        }
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    @Override
    public void setCommunicatable(boolean communicatable) {
        this.isCommunicatable = communicatable;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalApplication.getInstance().getVariableHolder().getCommunicatables().remove(this);
    }

    /**
     * 恢复状态
     *
     * @author blue
     */
    protected void onRestoreInstanceState(Bundle savedInstanceState){
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
    public Dialog showAlertDialog(String title, String msg, String[] btns, final DialogMaker.DialogCallBack callBack, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag)
    {
        if (null == dialog || !dialog.isShowing())
        {
            dialog = DialogMaker.showCommonAlertDialog(mActivity, title, msg, btns, callBack, isCanCancelabel, isDismissAfterClickBtn, tag);
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
            dialog = DialogMaker.showCommenWaitDialog(mActivity, msg, null, isCanCancelabel, tag);
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

//    public abstract int getLayoutId();
}
