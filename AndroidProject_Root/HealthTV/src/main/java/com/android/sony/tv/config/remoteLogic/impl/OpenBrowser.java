package com.android.sony.tv.config.remoteLogic.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.config.remoteLogic.DataProcesser;
import com.android.sony.tv.utils.Event;
import java.util.Map;

public class OpenBrowser implements DataProcesser {
    private Context mContext;

    @Override
    public void process(Context context, Map<String,String> data) {
        mContext = context;
        if(data == null){
            VariableHolder.logProvider.d(getClass().getSimpleName(), "obj_tmp is null in  OpenBrowser");
            return;
        }
        String url4open =  data.get("url4open");
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

    @Override
    public String toString() {
        return "OpenBrowser class";
    }
}
