package apps.bigdog.com.multicamera.fragment;

import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.activity.MainActivity;
import apps.bigdog.com.multicamera.app.LocalApplication;
import apps.bigdog.com.multicamera.util.LogUtil;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home1)
public class HomeOneFragment extends BaseFragment {
    @ViewInject(R.id.id_home_record_status)
    private TextView test1;



    @Override
    protected void initParams() {
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
