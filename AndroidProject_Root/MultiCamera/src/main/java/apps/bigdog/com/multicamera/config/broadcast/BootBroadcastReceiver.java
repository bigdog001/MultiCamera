package apps.bigdog.com.multicamera.config.broadcast;

import android.content.Context;
import android.content.Intent;

import com.tool.mytool.lib.broadcast.BaseBroadCastRcv;

import apps.bigdog.com.multicamera.beans.VariableHolder;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;

/**
 * Created by jw362j on 6/3/2016.
 */
public class BootBroadcastReceiver extends BaseBroadCastRcv implements InterfaceGenerator.AppLifeCycle {
    @Override
    public void onSafeReceive(Context context, Intent intent) {
       boolean isAutoStart = context.getSharedPreferences(VariableHolder.Constants.APP_SH_NAME,Context.MODE_PRIVATE).getBoolean(VariableHolder.Constants.APP_AUTOSTART_SP_FLAG,false);
        if (isAutoStart) {
            //start the service automaticlly
            /*

            Intent service = new Intent(context,XXXclass);
            context.startService(service);

            */
        }
    }

    @Override
    public void OnStop() {

    }
}