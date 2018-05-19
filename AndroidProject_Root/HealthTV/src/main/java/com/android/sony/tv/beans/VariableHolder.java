package com.android.sony.tv.beans;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import com.android.sony.tv.config.InterfaceGenerator;
import com.hadoopz.MyDroidLib.util.DroidLogProvider;
import java.util.List;

public class VariableHolder {
    public static DroidLogProvider logProvider;

    public static final class Constants {
        public static final String TIMER_BROADCAST_UNIT_NAME = "com.android.sony.tv.monitor";
        public static final long INTERVAL_UNIT = 1000 * 10;

        public static final String APP_SH_NAME = "mytv";
        public static final String APP_AUTOSTART_SP_FLAG = "isautostart";
    }

    public static final int UDP_SERVER_PORT = 18888;
    public static int screenW = 0;
    private int screenH = 0;
    private SharedPreferences sp;
    private LayoutInflater inflater;

    private List<InterfaceGenerator.ICommunicatable> communicatables;

    public int getScreenW() {
        return screenW;
    }


    public void setScreenH(int screenH) {
        this.screenH = screenH;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setCommunicatables(List<InterfaceGenerator.ICommunicatable> communicatables) {
        this.communicatables = communicatables;
    }
}
