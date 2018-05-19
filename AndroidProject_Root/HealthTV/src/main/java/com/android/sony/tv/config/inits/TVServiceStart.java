package com.android.sony.tv.config.inits;

import android.content.Context;
import android.content.Intent;

import com.android.sony.tv.config.TVService;
import com.mycomm.itool.listener.T.TInitializer;

/**
 * Created by jw362j on 5/18/2018.
 */

public class TVServiceStart implements TInitializer<Context> {
    @Override
    public void onInit(Context context) {
        Intent intent = new Intent(context, TVService.class);
        context.startService(intent);
    }
}
