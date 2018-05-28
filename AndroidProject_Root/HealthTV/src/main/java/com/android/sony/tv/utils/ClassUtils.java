package com.android.sony.tv.utils;
import android.content.Context;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import dalvik.system.DexFile;

public class ClassUtils {
    public static List<String> getClassName(Context context,String packageName){
        List<String >classNameList=new ArrayList<>();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());
            Enumeration<String> enumeration = df.entries();
            while (enumeration.hasMoreElements()) {
                String className = enumeration.nextElement();
                if (className.contains(packageName)) {
                    if(!className.contains("$")){classNameList.add(className);}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  classNameList;
    }
}
