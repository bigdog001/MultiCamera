package apps.bigdog.com.multicamera.fragment;

import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import apps.bigdog.com.multicamera.R;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home1)
public class HomeOneFragment extends BaseFragment {
    @ViewInject(R.id.test1)
    private TextView test1;


    @Override
    protected void initParams() {

    }

    @Override
    public void OnStop() {

    }
}
