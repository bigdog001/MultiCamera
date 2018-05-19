package com.android.sony.tv.utils;

import com.mycomm.MyConveyor.core.AbsTask;
import com.mycomm.MyConveyor.core.MyConveyor;
import com.mycomm.MyConveyor.core.Tasker;
public class BusyTaskRunner {

    public static void executeTask(final Tasker tasker) {
        MyConveyor.getInstance().execute(new AbsTask() {
            @Override
            public Tasker getTask() {
                return tasker;
            }
        });
    }
}
