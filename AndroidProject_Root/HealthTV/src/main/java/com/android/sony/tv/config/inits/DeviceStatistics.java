package com.android.sony.tv.config.inits;

import android.content.Context;
import android.os.Build;

import com.android.sony.tv.beans.VariableHolder;
import com.mycomm.itool.listener.T.TInitializer;


public class DeviceStatistics implements TInitializer<Context> {
    @Override
    public void onInit(Context context) {
        VariableHolder.logProvider.d(getClass().getSimpleName(),"===================================================");
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
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.BOARD:"+ Build.BOARD);//获取设备基板名称
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.CPU_ABI:"+ Build.CPU_ABI);//获取设备指令集名称（CPU的类型）
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.CPU_ABI2:"+ Build.CPU_ABI2);//获取第二个指令集名称
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.DEVICE:"+ Build.DEVICE);//获取设备驱动名称
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.RADIO:"+ Build.RADIO);//无线电固件版本号，通常是不可用的 显示unknown
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.TAGS:"+ Build.TAGS);//设备标签
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.TIME:"+ Build.TIME);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.TYPE:"+ Build.TYPE);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.USER:"+ Build.USER);
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.VERSION.CODENAME:"+ Build.VERSION.CODENAME);//设备当前的系统开发代号，一般使用REL代替
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.VERSION.RELEASE:"+ Build.VERSION.RELEASE);//获取系统版本字符串
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.VERSION.INCREMENTAL:"+ Build.VERSION.INCREMENTAL);//系统源代码控制值，一个数字或者git hash值
        VariableHolder.logProvider.d(getClass().getSimpleName(),"Build.VERSION.SDK:"+ Build.VERSION.SDK);//系统的API级别 一般使用下面大的SDK_INT
        VariableHolder.logProvider.d(getClass().getSimpleName(),"===================================================");
    }
}
