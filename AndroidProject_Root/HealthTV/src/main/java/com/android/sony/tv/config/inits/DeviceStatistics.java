package com.android.sony.tv.config.inits;

import android.content.Context;
import android.os.Build;

import com.android.sony.tv.beans.VariableHolder;
import com.mycomm.itool.listener.T.TInitializer;


public class DeviceStatistics implements TInitializer<Context> {
    @Override
    public void onInit(Context context) {
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.MANUFACTURER:"+ Build.MANUFACTURER);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.HARDWARE:"+ Build.HARDWARE);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.BOOTLOADER:"+ Build.BOOTLOADER);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.BRAND:"+ Build.BRAND);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.DEVICE:"+ Build.DEVICE);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.FINGERPRINT:"+ Build.FINGERPRINT);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.HOST:"+ Build.HOST);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.ID:"+ Build.ID);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.MODEL:"+ Build.MODEL);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.PRODUCT:"+ Build.PRODUCT);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.SERIAL:"+ Build.SERIAL);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.DISPLAY:"+ Build.DISPLAY);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.VERSION.SDK_INT:"+ Build.VERSION.SDK_INT);
    }
}
