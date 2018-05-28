package apps.bigdog.com.myalarm.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.hadoopz.MyDroidLib.util.DefaultLogUtil;

public class RecordService extends Service{

    @Override
    public void onCreate(){
        super.onCreate();
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"RecordService-->onCreate");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(),"RecordService-->onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        DefaultLogUtil.getInstance().d(getClass().getSimpleName(), "RecordService-->onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

}
