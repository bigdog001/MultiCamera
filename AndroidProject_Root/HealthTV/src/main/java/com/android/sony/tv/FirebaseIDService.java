package com.android.sony.tv;

import com.android.sony.tv.beans.VariableHolder;
import com.android.sony.tv.utils.DeviceTokenManager;
import com.android.sony.tv.utils.HttpProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        VariableHolder.logProvider.d(getClass().getSimpleName(), "in FirebaseIDService onTokenRefresh, Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        DeviceTokenManager.updateDeviceTokenStatus(getApplicationContext(),true);
        DeviceTokenManager.saveGoogleDeviceToken(getApplication(),token);
        HttpProvider.UpdateDeviceToken(getApplication());
    }
}
