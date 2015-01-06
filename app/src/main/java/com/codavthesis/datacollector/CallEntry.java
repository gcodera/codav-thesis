package com.codavthesis.datacollector;

/**
 * Created by ginettecodera on 1/6/15.
 */
public class CallEntry {
    private int CallId;
    private int UserId;
    private long Timestamp;
    private String Number;
    private int CallType;
    private long Duration;
    private boolean KnownNumber;

    public int getCallId() {
        return CallId;
    }

    public void setCallId(int callId) {
        CallId = callId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(long timestamp) {
        Timestamp = timestamp;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public int getCallType() {
        return CallType;
    }

    public void setCallType(int callType) {
        CallType = callType;
    }

    public long getDuration() {
        return Duration;
    }

    public void setDuration(long duration) {
        Duration = duration;
    }

    public boolean isKnownNumber() {
        return KnownNumber;
    }

    public void setKnownNumber(boolean knownNumber) {
        KnownNumber = knownNumber;
    }
}
