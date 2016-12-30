package apps.bigdog.com.multicamera.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tool.mytool.lib.util.LogUtil;


public class RecordService extends Service{

    @Override
    public void onCreate(){
        super.onCreate();
        LogUtil.log( "RecordService-->onCreate");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        LogUtil.log( "RecordService-->onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        LogUtil.log( "RecordService-->onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

}
