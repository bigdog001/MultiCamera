package apps.bigdog.com.multicamera.fragment;

import org.xutils.view.annotation.ContentView;

import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.util.LogUtil;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home4)
public class HomeFourFragment extends BaseFragment {
    @Override
    protected void initParams() {

    }

    @Override
    public void OnStop() {

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
