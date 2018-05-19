package com.android.sony.tv.config;

import com.android.sony.tv.app.LocalApplication;

/**
 * Created by jw362j on 6/1/2016.
 */
public abstract class AppObject implements InterfaceGenerator.AppLifeCycle{
    public AppObject(){
        LocalApplication.addAppLifeCycle(this);
    }
}
