package com.android.sony.tv.config.udpLogic;

import android.content.Context;

import com.android.sony.tv.utils.Event;

import org.json.JSONObject;

/**
 * Created by jw362j on 5/18/2018.
 */

public interface DataProcesser {
    Event getEvent();
    void process(Context context,JSONObject jsonObjectd);
}
