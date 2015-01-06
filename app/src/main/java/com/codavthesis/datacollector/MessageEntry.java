package com.codavthesis.datacollector;

/**
 * Created by ginettecodera on 1/6/15.
 */
public class MessageEntry {
    private int MessageId;
    private int UserId;
    private long Timestamp;
    private String Number;
    private int MessageType;
    private int Length;
    private boolean KnownNumber;

    public int getMessageId() {
        return MessageId;
    }

    public void setMessageId(int messageId) {
        MessageId = messageId;
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

    public int getMessageType() {
        return MessageType;
    }

    public void setMessageType(int messageType) {
        MessageType = messageType;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public boolean isKnownNumber() {
        return KnownNumber;
    }

    public void setKnownNumber(boolean knownNumber) {
        KnownNumber = knownNumber;
    }
}
