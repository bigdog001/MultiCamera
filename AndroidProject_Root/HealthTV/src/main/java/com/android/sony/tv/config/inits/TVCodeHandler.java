package com.android.sony.tv.config.inits;

import android.content.Context;

import com.android.sony.tv.utils.TVCodeManager;
import com.mycomm.itool.listener.T.TInitializer;

public class TVCodeHandler implements TInitializer<Context> {
    @Override
    public void onInit(Context context) {
        TVCodeManager.makeTVCode(context);
    }
}
