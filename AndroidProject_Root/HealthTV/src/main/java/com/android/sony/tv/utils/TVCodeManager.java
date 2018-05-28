package com.android.sony.tv.utils;
import android.content.Context;
import android.os.Build;
import com.android.sony.tv.beans.VariableHolder;
import com.mycomm.itool.SystemUtil;

public class TVCodeManager {
    public static void makeTVCode(Context context){
        if(context == null){
            return;
        }
        String tvCode = SystemUtil.getMD5((Build.FINGERPRINT+System.currentTimeMillis()).getBytes());
        VariableHolder.logProvider.d("TVCodeManager","the new made tvCode is:"+tvCode);
        context.getSharedPreferences(VariableHolder.appGlobalSharedPreFileName, Context.MODE_PRIVATE).edit().putString(VariableHolder.tvCodeName, tvCode).apply();
    }
    public static String getTVCode(Context context){
        if(context == null){
            return null;
        }
        String tvCode = context.getSharedPreferences(VariableHolder.appGlobalSharedPreFileName, Context.MODE_PRIVATE).getString(VariableHolder.tvCodeName, null);
        VariableHolder.logProvider.d("TVCodeManager","the saved tvCode is:"+tvCode);
        return tvCode;
    }

}
