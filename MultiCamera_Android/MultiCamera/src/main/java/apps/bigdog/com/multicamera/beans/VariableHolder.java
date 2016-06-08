package apps.bigdog.com.multicamera.beans;

import android.content.SharedPreferences;
import android.view.LayoutInflater;

import java.util.List;

import apps.bigdog.com.multicamera.config.InterfaceGenerator;

/**
 * Created by jw362j on 6/1/2016.
 */
public class VariableHolder {

    public static final class Constants{
        public static final String TIMER_BROADCAST_UNIT_NAME = "apps.bigdog.com.multicamera.android.TIMER_BROADCAST";
        public static final long INTERVAL_UNIT = 1000 * 3;//系统广播脉冲,每3秒一次
        public static final String APP_SH_NAME = "MultiCameraSp";
        public static final String MP4_FILE_STORAGED_IN_SP = "MP4_FILE_STORAGE_DIR";
        public static final String MP4_FILE_STORAGE_DIR_DEFAULT = "MP4_RECORD";
        public static final String APP_AUTOSTART_SP_FLAG = "isautostart";
    }
    private int screenW = 0;
    private int screenH = 0;
    private SharedPreferences sp;
    private LayoutInflater inflater ;

    private List<InterfaceGenerator.ICommunicatable> communicatables;

    public SharedPreferences getSp() {
        return sp;
    }

    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    public int getScreenW() {
        return screenW;
    }

    public void setScreenW(int screenW) {
        this.screenW = screenW;
    }

    public int getScreenH() {
        return screenH;
    }

    public void setScreenH(int screenH) {
        this.screenH = screenH;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public List<InterfaceGenerator.ICommunicatable> getCommunicatables() {
        return communicatables;
    }

    public void setCommunicatables(List<InterfaceGenerator.ICommunicatable> communicatables) {
        this.communicatables = communicatables;
    }
}
