package apps.bigdog.com.multicamera.fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.view.MySurfaceView;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;
import com.mycomm.YesHttp.core.FileUploadRequest;
import com.mycomm.YesHttp.core.Request;
import com.mycomm.YesHttp.core.Response;
import com.mycomm.YesHttp.core.TextBaseResponseListener;
import com.mycomm.YesHttp.core.YesHttpEngine;
import com.mycomm.YesHttp.core.YesHttpError;
import java.io.File;
import java.util.Map;

/**
 * Created by jw362j on 6/2/2016.
 */
@ContentView(R.layout.fragment_home2)
public class HomeTwoFragment extends BaseFragment {
    @ViewInject(R.id.mySurfaceView)
    private MySurfaceView mySurfaceView;

    @ViewInject(R.id.preview_top_btn_record)
    private Button preview_top_btn_record;

    @Override
    protected void initParams() {
        mySurfaceView.setBackgroundResource(R.drawable.screen_no_signal);
    }


    @Event(value = {R.id.preview_top_btn_record}, type = View.OnClickListener.class)
    private void ItemOnclick(View v) {
        switch (v.getId()) {
            case R.id.preview_top_btn_record:
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        SendNetWorkRequest();
                    }
                }).start();

                break;


            default:
                break;
        }
    }

    private void SendNetWorkRequest(){
        Request.YesLog yeslog = new Request.YesLog() {
            @Override
            public void w(String s) {
                DefaultLogUtil.getInstance().v(getClass().getSimpleName(),"the log :" + s);
            }

            @Override
            public void e(String s) {
                DefaultLogUtil.getInstance().e(getClass().getSimpleName(),"the log :" + s);
            }

            @Override
            public void d(String s) {
                DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"the log :" + s);
            }

            @Override
            public void i(String s) {
                DefaultLogUtil.getInstance().i(getClass().getSimpleName(),"the log :" + s);
            }

            @Override
            public void v(String s) {
                DefaultLogUtil.getInstance().v(getClass().getSimpleName(),"the log :" + s);
            }
        };
        String url = "http://192.168.0.102:8082/testFileUpload.xhtml";
        Request request = new FileUploadRequest(url, new TextBaseResponseListener() {
            @Override
            public void responseMe(String msg) {
                DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"FileUploadRequest.responseMe:" + msg);
            }
        }, null, new Response.ErrorListener() {
            public void onErrorResponse(YesHttpError error) {
                DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"Response.ErrorListener.onErrorResponse:" + error.getMessage());
            }

        }, yeslog, Request.Protocol.HTTP, new Response.DownLoadUpLoadListener() {
            public void onProgressing(float rate) {
                DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"DownLoadUpLoadListener rate:" + rate);
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                return null; //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public File getUploadFile() {
                return new File("/sdcard/A/b.zip");
            }



        };
        YesHttpEngine.getYesHttpEngine().send(request);
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
