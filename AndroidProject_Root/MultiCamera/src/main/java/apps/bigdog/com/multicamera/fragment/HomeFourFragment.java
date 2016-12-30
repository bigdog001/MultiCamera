package apps.bigdog.com.multicamera.fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;

import com.tool.mytool.lib.util.LogUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.view.DialogMaker;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home4)
public class HomeFourFragment extends BaseFragment {
    @ViewInject(R.id.app_exit)
    private Button app_exit;
    @Override
    protected void initParams() {

    }

    @Override
    public void OnStop() {

    }
    @Event(value = {R.id.app_exit}, type = View.OnClickListener.class)
    private void ItemOnclick(View v) {
        switch (v.getId()){
            case R.id.app_exit:
                appExit();
                break;

            default:
                break;
        }

    }

    private void appExit() {
        String title = getActivity().getResources().getText(R.string.home_tab_settings_item_exit_title_text).toString();
        String content = getActivity().getResources().getText(R.string.home_tab_settings_item_exit_content_text).toString();
        super.showAlertDialog(title,content,new String[]{"OK","Cancel"},new DialogMaker.DialogCallBack(){
            @Override
            public void onButtonClicked(Dialog dialog, int position, Object tag) {
                if (position == 0) {
                    LocalApplication.getInstance().stopAll();
                }
            }

            @Override
            public void onCancelDialog(Dialog dialog, Object tag) {

            }
        },true,true,null);

    }

    @Override
    public int myIndex() {
        return 3;
    }



    @Override
    public void DataIn(Object data) {
        if (!isCommunicatable || data == null) {
            return;
        }
        LogUtil.log("HomeFourFragment is DataIn...");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.log("HomeFourFragment is onResume...");
    }

    @Override
    public Object DataOut() {
        return null;
    }
}
