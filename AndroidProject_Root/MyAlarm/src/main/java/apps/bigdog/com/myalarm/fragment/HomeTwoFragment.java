package apps.bigdog.com.myalarm.fragment;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import apps.bigdog.com.myalarm.R;
import apps.bigdog.com.myalarm.view.MySurfaceView;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home2)
public class HomeTwoFragment extends BaseFragment {
    @ViewInject(R.id.mySurfaceView)
    private MySurfaceView mySurfaceView;

    @Override
    protected void initParams() {
        mySurfaceView.setBackgroundResource(R.drawable.screen_no_signal);
    }

    /*
    @Event(value = {R.id.mySurfaceView}, type = View.OnClickListener.class)
    private void ItemOnclick(View v) {
        switch (v.getId()) {
            case R.id.mySurfaceView:
//                DoAutoLaunchSwitcher();
                break;


            default:
                break;
        }
    }
*/
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
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"HomeTwoFragment is DataIn...");
    }

    @Override
    public void onResume() {
        super.onResume();
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"HomeTwoFragment is onResume...");
    }

    @Override
    public Object DataOut() {
        return null;
    }
}
