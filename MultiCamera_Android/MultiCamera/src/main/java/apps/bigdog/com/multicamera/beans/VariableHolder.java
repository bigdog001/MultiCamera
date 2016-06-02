package apps.bigdog.com.multicamera.beans;

import android.content.SharedPreferences;

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
    }
    private int screenW = 0;
    private int screenH = 0;
    private SharedPreferences sp;

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
}
