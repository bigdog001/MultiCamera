package apps.bigdog.com.myalarm.config.broadcast;

import android.content.Context;
import android.content.Intent;

import apps.bigdog.com.myalarm.beans.VariableHolder;
import apps.bigdog.com.myalarm.config.BaseBroadCastRcv;

/**
 * Created by jw362j on 6/3/2016.
 */
public class BootBroadcastReceiver extends BaseBroadCastRcv{
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