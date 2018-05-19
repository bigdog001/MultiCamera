package com.android.sony.tv.config.udpLogic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.utils.Event;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenBrowser implements DataProcesser {
    private Context mContext;

    @Override
    public void process(Context context, JSONObject obj_tmp) {
        mContext = context;
        if(obj_tmp == null){
            VariableHolder.logProvider.d(getClass().getSimpleName(), "obj_tmp is null in  OpenBrowser");
            return;
        }
        String url4open = null;
        if (obj_tmp.has("url4open")) {
            try {
                url4open = obj_tmp.getString("url4open");
            } catch (JSONException e) {
                VariableHolder.logProvider.e(getClass().getSimpleName(), "OpenBrowser error:"+e.getMessage());
            }
        }
        if(url4open == null || "".equals(url4open)){
            VariableHolder.logProvider.d(getClass().getSimpleName(), "url4open is null in  OpenBrowser");
            return;
        }
        VariableHolder.logProvider.d(getClass().getSimpleName(), "open the url:"+url4open+" in browser....");
        Intent openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(url4open));
        mContext.startActivity(openWeb);
    }

    @Override
    public Event getEvent() {
        return Event.Event_OpenUrl;
    }
}
