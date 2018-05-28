package com.android.sony.tvremote.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hadoopz.MyDroidLib.util.DefaultLogUtil;

public class DeviceTokenManager {

    public static void saveGoogleDeviceToken(Context context, String deviceToken) {
        if ("".equals(deviceToken) || deviceToken == null || context == null) {
            DefaultLogUtil.getInstance().d(DeviceTokenManager.class.getClass().getSimpleName(), "context or deviceToken is null in saveGoogleDeviceToken");
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences("HealthTVRemote", Context.MODE_PRIVATE).edit();
        editor.putString("deviceToken", deviceToken).apply();
    }

    public static String getGoogleDeviceToken(Context context) {
        if (context == null) {
            DefaultLogUtil.getInstance().d(DeviceTokenManager.class.getClass().getSimpleName(), "context is null in getGoogleDeviceToken");
            return null;
        }
        return context.getSharedPreferences("HealthTVRemote", Context.MODE_PRIVATE).getString("deviceToken", null);
    }

}
