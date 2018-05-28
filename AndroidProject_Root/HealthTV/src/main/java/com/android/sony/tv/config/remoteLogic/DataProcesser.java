package com.android.sony.tv.config.remoteLogic;

import android.content.Context;
import com.android.sony.tv.utils.Event;

import java.util.Map;
public interface DataProcesser {
    Event getEvent();
    void process(Context context,Map<String,String> data);
}
