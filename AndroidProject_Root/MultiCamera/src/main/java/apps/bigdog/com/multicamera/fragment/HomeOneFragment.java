package apps.bigdog.com.multicamera.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tool.mytool.lib.util.LogUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.activity.MainActivity;
import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.beans.VariableHolder;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home1)
public class HomeOneFragment extends BaseFragment {
    @ViewInject(R.id.toggle_btn_autolaunch_switcher)
    private ImageView toggle_btn_autolaunch_switcher;
    private boolean isAutoStart;

    @Override
    protected void initParams() {
        isAutoStart = LocalApplication.getInstance().getVariableHolder().getSp().getBoolean(VariableHolder.Constants.APP_AUTOSTART_SP_FLAG,false);
        if (isAutoStart) {
            toggle_btn_autolaunch_switcher.setImageResource(R.drawable.toggle_btn_autolaunch_on);
        }
    }

    @Event(value = {R.id.toggle_btn_autolaunch_switcher}, type = View.OnClickListener.class)
    private void ItemOnclick(View v) {
        switch (v.getId()) {
            case R.id.toggle_btn_autolaunch_switcher:
                DoAutoLaunchSwitcher();
                break;


            default:
                break;
        }
    }

    private void DoAutoLaunchSwitcher(){
        isAutoStart = !isAutoStart;
        LogUtil.log("isAutoStart:"+isAutoStart);
        if (isAutoStart) {
            toggle_btn_autolaunch_switcher.setImageResource(R.drawable.toggle_btn_autolaunch_on);
        }else {
            toggle_btn_autolaunch_switcher.setImageResource(R.drawable.toggle_btn_autolaunch_off);
        }
        LocalApplication.getInstance().getVariableHolder().getSp().edit().putBoolean(VariableHolder.Constants.APP_AUTOSTART_SP_FLAG,isAutoStart).commit();
    }

    @Override
    public int myIndex() {
        return 0;
    }


    @Override
    public void DataIn(Object data) {
        if (!isCommunicatable || data == null) {
            return;
        }
        LogUtil.log("HomeOneFragment is DataIn...");
    }

    @Override
    public Object DataOut() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.chooseIndex == 0) {
            LogUtil.log("HomeOneFragment is onResume...");
            isCommunicatable = true;
        }
    }

    @Override
    public void OnStop() {

    }


}
