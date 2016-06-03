package apps.bigdog.com.multicamera.fragment;

import org.xutils.view.annotation.ContentView;

import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.util.LogUtil;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home2)
public class HomeTwoFragment extends BaseFragment {
    @Override
    protected void initParams() {

    }

    @Override
    public void OnStop() {

    }

    @Override
    public int myIndex() {
        return 1;
    }

    @Override
    public void DataIn(Object data) {
        if (!isCommunicatable || data == null) {
            return;
        }
        LogUtil.log("HomeTwoFragment is DataIn...");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.log("HomeTwoFragment is onResume...");
    }

    @Override
    public Object DataOut() {
        return null;
    }
}
