package apps.bigdog.com.myalarm.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.hadoopz.MyDroidLib.util.MyLogUtil;
public class RecordService extends Service{

    @Override
    public void onCreate(){
        super.onCreate();
        MyLogUtil.LogMe( "RecordService-->onCreate");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        MyLogUtil.LogMe( "RecordService-->onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        MyLogUtil.LogMe( "RecordService-->onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

}
