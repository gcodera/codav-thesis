package com.codavthesis.datacollector;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class ConnectivityStatusEntry {
    private int ConnectivityStatusId;
    private int UserId;
    private long Timestamp;
    private String ConnectionType;

    public void setConnectivityStatusId(int connectivityStatusId) {
        ConnectivityStatusId = connectivityStatusId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setTimestamp(long timestamp) {
        Timestamp = timestamp;
    }

    public void setConnectionType(String connectionType) {
        ConnectionType = connectionType;
    }

    public int getConnectivityStatusId() {
        return ConnectivityStatusId;
    }

    public int getUserId() {
        return UserId;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public String getConnectionType() {
        return ConnectionType;
    }
}