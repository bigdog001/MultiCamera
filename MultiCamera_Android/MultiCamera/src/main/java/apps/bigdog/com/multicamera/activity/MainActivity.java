package apps.bigdog.com.multicamera.activity;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.activity.base.BaseActivity;
import apps.bigdog.com.multicamera.util.LogUtil;
import org.xutils.x;

import javax.net.ssl.SSLSocketFactory;

/**
 * Created by jw362j on 6/1/2016.
 */
public  class MainActivity extends BaseActivity {
    @ViewInject(R.id.testbtn)
    private Button testbtn;
    @ViewInject(R.id.myimage)
    private ImageView myimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(value={R.id.testbtn},type=View.OnClickListener.class)
    private void ItemOnclick(View v){
        int idv = v.getId();
        switch (idv){
            case R.id.testbtn:
                x.image().bind(myimage, "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");

//                sendHttp();
                break;

            default:
                break;
        }
    }

    private void sendHttp(){
        RequestParams params = new RequestParams("https://www.baidu.com/s");
//        params.setSslSocketFactory(SSLCertificateSocketFactory.getDefault(2,new SSLSessionCache(this))); // 设置ssl
        params.addQueryStringParameter("wd", "xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
                Toast.makeText(x.app(), "onFinished", Toast.LENGTH_LONG).show();
            }
        });
    }
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void initParams() {

    }
}
