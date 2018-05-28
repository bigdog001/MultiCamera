package com.android.sony.tv.utils;

import com.android.sony.tv.beans.VariableHolder;

public enum Event {
    Event_OpenUrl("open_url"),
    Event_Echo("udp_echo");
    private String value;

    private Event(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Event fromValue(String v) {
        if ("".equals(v) || v == null) {
            throw new IllegalArgumentException(v);
        }

        for (Event c : values()) {
            VariableHolder.logProvider.d(Event.class.getSimpleName(),"DataTypeItem values:" + c);
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }
}
