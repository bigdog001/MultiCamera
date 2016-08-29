package apps.bigdog.com.myalarm.beans;

import java.io.Serializable;

/**
 * Created by jw362j on 8/29/2016.
 */
public class EmergencyContactor implements Serializable {
    private String cellPhoneNumber;
    private String nickName;

    public EmergencyContactor() {

    }

    public EmergencyContactor(String cellPhoneNumber, String nickName) {
        this.cellPhoneNumber = cellPhoneNumber;
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
}
