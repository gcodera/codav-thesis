package com.codavthesis.datacollector;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class BatteryLevelEntry {
    private int BatteryLevelId;
    private int UserId;
    private long Timestamp;
    private String BatteryLevel;

    public void setBatteryLevelId(int batteryLevelId) {
        BatteryLevelId = batteryLevelId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setTimestamp(long timestamp) {
        Timestamp = timestamp;
    }

    public void setBatteryLevel(String batteryLevel) {
        BatteryLevel = batteryLevel;
    }

    public int getBatteryLevelId() {
        return BatteryLevelId;
    }

    public int getUserId() {
        return UserId;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public String getBatteryLevel() {
        return BatteryLevel;
    }
}

