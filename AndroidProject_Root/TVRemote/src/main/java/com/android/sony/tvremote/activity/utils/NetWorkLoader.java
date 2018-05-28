package com.android.sony.tvremote.activity.utils;

import com.mycomm.MyConveyor.core.AbsTask;
import com.mycomm.MyConveyor.core.MyConveyor;
import com.mycomm.MyConveyor.core.Tasker;

public class NetWorkLoader {
    public static void runHttpTask(final Tasker tasker){
        MyConveyor.getInstance().execute(new AbsTask() {
            @Override
            public Tasker getTask() {
                return tasker;
            }
        });
    }
}
