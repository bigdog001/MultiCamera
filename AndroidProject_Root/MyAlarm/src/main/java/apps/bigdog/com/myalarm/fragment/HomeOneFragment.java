package apps.bigdog.com.myalarm.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import apps.bigdog.com.myalarm.R;
import apps.bigdog.com.myalarm.activity.MainActivity;
import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.beans.EmergencyContactor;
import apps.bigdog.com.myalarm.beans.VariableHolder;
import apps.bigdog.com.myalarm.util.LogUtil;
import apps.bigdog.com.myalarm.util.SystemUtils;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home1)
public class HomeOneFragment extends BaseFragment {
    @ViewInject(R.id.toggle_btn_autolaunch_switcher)
    private ImageView toggle_btn_autolaunch_switcher;

    @ViewInject(R.id.lv_emergency_contactslist)
    private ListView lv_emergency_contactslist;

    private boolean isAutoStart;

    @Override
    protected void initParams() {
        isAutoStart = SystemUtils.isAutoLaunchApp();// LocalApplication.getInstance().getVariableHolder().getSp().getBoolean(VariableHolder.Constants.APP_AUTOSTART_SP_FLAG,false);
        if (isAutoStart) {
            toggle_btn_autolaunch_switcher.setImageResource(R.drawable.toggle_btn_autolaunch_on);
        }
       initialListView();
    }

    @Event(value = {R.id.toggle_btn_autolaunch_switcher,R.id.home_add_contactor_icon}, type = View.OnClickListener.class)
    private void ItemOnclick(View v) {
        switch (v.getId()) {
            case R.id.toggle_btn_autolaunch_switcher:
                DoAutoLaunchSwitcher();
                break;
            case R.id.home_add_contactor_icon:
                addEmergencyContactor(new EmergencyContactor("7182199057",""));
                break;


            default:
                break;
        }
    }

    private void addEmergencyContactor(EmergencyContactor contactor) {
        SystemUtils.addEmergencyContactor(contactor);
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

    private void initialListView(){
        if (lv_emergency_contactslist == null) {
            return;
        }
        //lv_emergency_contactslist.setAdapter();
        if (LocalApplication.getInstance().getVariableHolder().getContactors() == null || LocalApplication.getInstance().getVariableHolder().getContactors().size() <= 0) {
            TextView tv_infor = new TextView(mActivity);
            tv_infor.setText("无紧急联系人");
            tv_infor.setTextColor(Color.BLACK);
            lv_emergency_contactslist.addHeaderView(tv_infor);
        }


    }
}
