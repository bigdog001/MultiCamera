package apps.bigdog.com.myalarm.fragment;

import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import org.xutils.view.annotation.ContentView;
import apps.bigdog.com.myalarm.R;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home3)
public class HomeThreeFragment extends BaseFragment {
    @Override
    protected void initParams() {

    }

    @Override
    public void OnStop() {

    }

    @Override
    public int myIndex() {
        return 2;
    }

    @Override
    public void DataIn(Object data) {
        if (!isCommunicatable || data == null) {
            return;
        }
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"HomeThreeFragment is DataIn...");
    }

    @Override
    public void onResume() {
        super.onResume();
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"HomeThreeFragment is onResume...");
    }

    @Override
    public Object DataOut() {
        return null;
    }
}
