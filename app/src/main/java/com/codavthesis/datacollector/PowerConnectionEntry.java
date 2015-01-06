package com.codavthesis.datacollector;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class PowerConnectionEntry {
    private int PowerConnectionId;
    private int UserId;
    private long Timestamp;
    private boolean PowerConnected;
    private String ConnectionType;
    private float PowerLevel;

    public void setPowerConnectionId(int powerConnectionId) { PowerConnectionId = powerConnectionId; }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setTimestamp(long timestamp) {
        Timestamp = timestamp;
    }

    public void setPowerConnected(boolean powerConnected) {
        PowerConnected = powerConnected;
    }

    public void setConnectionType(String connectionType) { ConnectionType = connectionType; }

    public void setPowerLevel(float powerLevel) {
        PowerLevel = powerLevel;
    }

    public int getPowerConnectionId() {
        return PowerConnectionId;
    }

    public int getUserId() {
        return UserId;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public boolean isPowerConnected() {
        return PowerConnected;
    }

    public String getConnectionType() { return ConnectionType; }

    public float getPowerLevel() {
        return PowerLevel;
    }
}

