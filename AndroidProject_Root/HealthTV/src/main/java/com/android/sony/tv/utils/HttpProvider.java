package com.android.sony.tv.utils;

import android.content.Context;

import com.android.sony.tv.beans.VariableHolder;
import com.mycomm.MyConveyor.core.Tasker;
import com.mycomm.YesHttp.core.Request;
import com.mycomm.YesHttp.core.Response;
import com.mycomm.YesHttp.core.StringRequest;
import com.mycomm.YesHttp.core.TextBaseResponseListener;
import com.mycomm.YesHttp.core.YesHttpEngine;
import com.mycomm.YesHttp.core.YesHttpError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpProvider {
    private static final Request.YesLog yeslog = new Request.YesLog() {
        public void w(String text) {
            VariableHolder.logProvider.w(getClass().getSimpleName(),text);
        }

        public void e(String text) {
            VariableHolder.logProvider.w(getClass().getSimpleName(),text);
        }

        public void d(String text) {
            VariableHolder.logProvider.w(getClass().getSimpleName(),text);
        }

        public void i(String text) {
            VariableHolder.logProvider.w(getClass().getSimpleName(),text);
        }

        public void v(String text) {
            VariableHolder.logProvider.w(getClass().getSimpleName(),text);
        }
    };

    public static void UpdateDeviceToken(final Context c) {
        VariableHolder.logProvider.d(HttpProvider.class.getSimpleName(), "in HttpProvider.UpdateDeviceToken");
        if (!DeviceTokenManager.needToUpdateDeviceToken(c)) {
            VariableHolder.logProvider.d(HttpProvider.class.getSimpleName(), "unnecessary to UpdateDeviceToken..");
            return;
        }
        final String deviceToken = DeviceTokenManager.getGoogleDeviceToken(c);
        VariableHolder.logProvider.d(HttpProvider.class.getSimpleName(), "the saved deviceToken:"+deviceToken);
        if (deviceToken == null || "".equals(deviceToken)) {
            VariableHolder.logProvider.d(HttpProvider.class.getSimpleName(), "in HttpProvider.UpdateDeviceToken,deviceToken is null!");
            return;
        }
        final String tvCode = TVCodeManager.getTVCode(c);
        if (tvCode == null || "".equals(tvCode)) {
            VariableHolder.logProvider.d(HttpProvider.class.getSimpleName(), "in HttpProvider.UpdateDeviceToken,tvCode is null!");
            return;
        }

        /*
        BusyTaskRunner.executeTask(new Tasker() {
            @Override
            public void onTask() {


                String url = "";
                final Map<String, String> params = new HashMap<>();
                params.put("deviceToken", deviceToken);
                params.put("tvCode", tvCode);
                Request request = new StringRequest(Request.Method.POST, url, new TextBaseResponseListener() {
                    @Override
                    public void responseMe(String msg) {
                        yeslog.LogMe("the requestPOST TextBaseResponseListener.responseMe:" + msg);
                        if (msg == null || "".equals(msg)) {
                            return;
                        }
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(msg);
                        } catch (JSONException e) {
                            VariableHolder.logProvider.e(getClass().getSimpleName(), "in UpdateDeviceToken:" + e.getMessage());
                        }
                        if (jsonObject == null) {
                            return;
                        }
                        if (!jsonObject.has("status")) {
                            return;
                        }
                        String updateStatus = null;
                        try {
                            updateStatus = jsonObject.getString("status");
                        } catch (JSONException e) {
                            VariableHolder.logProvider.e(getClass().getSimpleName(), "error in updateStatus:" + e.getMessage());
                        }
                        if ("success".equals(updateStatus)) {
                            AndroidDeviceTokenManager.updateDeviceTokenStatus(c, false);
                        }


                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(YesHttpError error) {
                        yeslog.LogMe("the requestGet ErrorListener.onErrorResponse:" + error.getMessage());
                    }
                }, yeslog, Request.Protocol.HTTPS_IGNORE_CERT) {

                    @Override
                    public Map<String, String> getParams() {
                        return params;
                    }

                };
                YesHttpEngine.getYesHttpEngine().send(request);
            }
        });
*/

    }
}
