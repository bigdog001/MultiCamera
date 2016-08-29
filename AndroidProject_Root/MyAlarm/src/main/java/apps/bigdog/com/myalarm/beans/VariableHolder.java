package apps.bigdog.com.myalarm.beans;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import java.util.List;
import java.util.Map;

import apps.bigdog.com.myalarm.config.InterfaceGenerator;

/**
 * Created by jw362j on 6/1/2016.
 */
public class VariableHolder {

    public static final class Constants{
        public static final String TIMER_BROADCAST_UNIT_NAME = "apps.bigdog.com.myalarm.android.TIMER_BROADCAST";
        public static final long INTERVAL_UNIT = 1000 * 1;//系统广播脉冲,每1秒一次
        public static final String APP_SH_NAME = "myalarmSp";
        public static final String APP_EMERGENCYCONTACTORS_NAME = "EMERGENCYCONTACTORS_NAME";
        public static final String APP_ISINEMERGENCYMODEl_NAME = "isinemergency";



        public static final String MP4_FILE_STORAGED_IN_SP = "MP4_FILE_STORAGE_DIR";
        public static final String MP4_FILE_STORAGE_DIR_DEFAULT = "MP4_RECORD";

        public static final String APP_AUTOSTART_SP_FLAG = "isautostart";
    }

    private int screenW = 0;
    private int screenH = 0;
    private SharedPreferences sp;
    private LayoutInflater inflater ;
    private float batteryPercent;

    private List<InterfaceGenerator.ICommunicatable> communicatables;
    private Map<String,EmergencyContactor> contactors;

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

    public Map<String,EmergencyContactor> getContactors() {
        return contactors;
    }

    public void setContactors(Map<String,EmergencyContactor> contactors) {
        this.contactors = contactors;
    }


    public float getBatteryPercent() {
        return batteryPercent;
    }

    public void setBatteryPercent(float batteryPercent) {
        this.batteryPercent = batteryPercent;
    }
}
