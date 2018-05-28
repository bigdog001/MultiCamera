package com.android.sony.tv.config.scanner;

import android.content.Context;
import com.android.sony.tv.config.InterfaceGenerator;
import com.android.sony.tv.utils.HttpProvider;
public class UpdateDeviceToken implements InterfaceGenerator.timerAction {
    @Override
    public boolean isAllowedToExecute() {
        return true;
    }

    @Override
    public void onTime(final Context c, int flag, Object data) {
        HttpProvider.UpdateDeviceToken(c);
    }
}
