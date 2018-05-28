package apps.bigdog.com.myalarm.util;
import com.hadoopz.MyDroidLib.util.DefaultLogUtil;

import java.util.Set;
import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.beans.EmergencyContactor;
import apps.bigdog.com.myalarm.beans.VariableHolder;

/**
 * Created by jw362j on 8/29/2016.
 */
public class SystemUtils {
    public static EmergencyContactor parseEmergencyContactor(String line){
        EmergencyContactor contactor = null;

        return contactor;
    }

    public static boolean isInEmergencyModel(){
        boolean result = false;
        result = LocalApplication.getInstance().getVariableHolder().getSp().getBoolean(VariableHolder.Constants.APP_ISINEMERGENCYMODEl_NAME,false);
        return result;
    }


    public static boolean isValidContactor(String cellNumber){
        if (cellNumber == null || "".equals(cellNumber)) {
            return false;
        }
        boolean result = false;
        result = LocalApplication.getInstance().getVariableHolder().getContactors().containsKey(cellNumber);
        return result;
    }

    public static void reloadContactor(){
//        LocalApplication.getInstance().getVariableHolder().getContactors().containsKey(cellNumber);
        Set<String> contactors_cellphoneNumbers = LocalApplication.getInstance().getVariableHolder().getSp().getStringSet(VariableHolder.Constants.APP_EMERGENCYCONTACTORS_NAME, null);
        if (contactors_cellphoneNumbers == null || contactors_cellphoneNumbers.size() <= 0) {
            return;
        }
        LocalApplication.getInstance().getVariableHolder().getContactors().clear();
        for (String cell : contactors_cellphoneNumbers) {
            if (cell == null || "".equals(cell) || cell.length() <= 0) {
                continue;
            }
            EmergencyContactor contactor = SystemUtils.parseEmergencyContactor(cell);
            if (contactor != null) {
                LocalApplication.getInstance().getVariableHolder().getContactors().put(contactor.getCellPhoneNumber(), contactor);
            }
        }

    }

    public static boolean isAutoLaunchApp(){
        return LocalApplication.getInstance().getVariableHolder().getSp().getBoolean(VariableHolder.Constants.APP_AUTOSTART_SP_FLAG,false);
    }

    public static void addEmergencyContactor(EmergencyContactor contactor){
        DefaultLogUtil.getInstance().d(SystemUtils.class.getSimpleName(),"add the emergency contactor:"+contactor.getCellPhoneNumber());
        //LocalApplication.getInstance().getVariableHolder().getSp().getStringSet(VariableHolder.Constants.APP_EMERGENCYCONTACTORS_NAME, null);
        LocalApplication.getInstance().getVariableHolder().getContactors().put(contactor.getCellPhoneNumber(),contactor);
        LocalApplication.getInstance().getVariableHolder().getSp().edit().putStringSet(VariableHolder.Constants.APP_EMERGENCYCONTACTORS_NAME,LocalApplication.getInstance().getVariableHolder().getContactors().keySet());
    }

}
