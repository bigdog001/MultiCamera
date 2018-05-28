package com.android.sony.tv.beans;

public class TimeRange {
    private TimeCounter start;
    private TimeCounter end;

    public TimeRange(TimeCounter start, TimeCounter end) {
        this.start = start;
        this.end = end;
    }

    public TimeCounter getStart() {
        return start;
    }

    public void setStart(TimeCounter start) {
        this.start = start;
    }

    public TimeCounter getEnd() {
        return end;
    }

    public void setEnd(TimeCounter end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
