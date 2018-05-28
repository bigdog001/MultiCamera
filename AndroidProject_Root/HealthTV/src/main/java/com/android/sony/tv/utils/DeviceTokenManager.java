package com.android.sony.tv.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.sony.tv.beans.VariableHolder;

public class DeviceTokenManager {
    public static void saveGoogleDeviceToken(Context context, String deviceToken) {
        if ("".equals(deviceToken) || deviceToken == null || context == null) {
            VariableHolder.logProvider.d(DeviceTokenManager.class.getClass().getSimpleName(), "context or deviceToken is null in saveGoogleDeviceToken");
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(VariableHolder.appGlobalSharedPreFileName, Context.MODE_PRIVATE).edit();
        editor.putString(VariableHolder.deviceTokenName, deviceToken).apply();
    }

    public static String getGoogleDeviceToken(Context context) {
        if (context == null) {
            VariableHolder.logProvider.d(DeviceTokenManager.class.getClass().getSimpleName(), "context is null in getGoogleDeviceToken");
            return null;
        }
        return context.getSharedPreferences(VariableHolder.appGlobalSharedPreFileName, Context.MODE_PRIVATE).getString(VariableHolder.deviceTokenName, null);
    }

    public static boolean needToUpdateDeviceToken(Context context){
        if (context == null) {
            VariableHolder.logProvider.d(DeviceTokenManager.class.getClass().getSimpleName(), "context is null in needToUpdateDeviceToken");
            return false;
        }
        return context.getSharedPreferences(VariableHolder.appGlobalSharedPreFileName, Context.MODE_PRIVATE).getBoolean(VariableHolder.needToUpdatedeviceTokenName, false);
    }

    public static void updateDeviceTokenStatus(Context context,boolean needToUpdate){
        if (context == null) {
            VariableHolder.logProvider.d(DeviceTokenManager.class.getClass().getSimpleName(), "context is null in updateDeviceTokenStatus");
            return;
        }
        context.getSharedPreferences(VariableHolder.appGlobalSharedPreFileName, Context.MODE_PRIVATE).edit().putBoolean(VariableHolder.needToUpdatedeviceTokenName, needToUpdate).apply();
    }

}
