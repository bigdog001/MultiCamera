package apps.bigdog.com.multicamera.beans;

/**
 * Created by jw362j on 6/1/2016.
 */
public class VariableHolder {

    public static final class Constants{
        public static final String TIMER_BROADCAST_UNIT_NAME = "apps.bigdog.com.multicamera.android.TIMER_BROADCAST";
        public static final long INTERVAL_UNIT = 1000 * 3;//系统广播脉冲,每3秒一次
    }
    private int screenW = 0;
    private int screenH = 0;

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
