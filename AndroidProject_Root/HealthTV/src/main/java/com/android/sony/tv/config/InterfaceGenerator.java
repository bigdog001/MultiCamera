package com.android.sony.tv.config;

import android.content.Context;

import com.android.sony.tv.beans.VariableHolder;
public class InterfaceGenerator {

    public interface AppLifeCycle {
        public void OnStop();
    }

    public interface Initializer {
        public void init(Context context);
    }
    public interface timerAction{
        public boolean isAllowedToExecute();
        public void onTime(Context c,int flag,Object data);
    }

    public interface ICommunicatable{
        public int myIndex();
        public void setCommunicatable(boolean communicatable);
        public void DataIn(Object data);
        public Object DataOut();
    }
}
