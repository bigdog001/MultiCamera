package com.android.sony.tv.beans;

public class TimeCounter {
    private int hour;
    private int mins;

    public TimeCounter(int hour, int mins) {
        this.hour = hour;
        this.mins = mins;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getTime(){
        return hour*60+mins;
    }

    @Override
    public String toString() {
        return "TimeCounter{" +
                "hour=" + hour +
                ", mins=" + mins +
                '}';
    }
}
