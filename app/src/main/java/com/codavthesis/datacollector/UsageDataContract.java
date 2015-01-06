package com.codavthesis.datacollector;

import android.provider.BaseColumns;

/**
 * Created by ginettecodera on 1/5/15.
 */
public final class UsageDataContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public UsageDataContract() {}

    /* Inner class that defines the table contents */
    public static abstract class BatteryLevel implements BaseColumns {
        public static final String TABLE_NAME = "battery_level";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_BATTERY_LEVEL = "batterylevel";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_USER_ID + " INT, " +
                        COLUMN_NAME_TIMESTAMP +  " INT, " +
                        COLUMN_NAME_BATTERY_LEVEL + " CHAR(3));";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class PowerConnection implements BaseColumns {
        public static final String TABLE_NAME = "power_connection";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_POWER_CONNECTED = "powerconnected";
        public static final String COLUMN_NAME_CONNECTION_TYPE = "connectiontype";
        public static final String COLUMN_NAME_POWER_LEVEL = "powerlevel";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_USER_ID + " INT, " +
                        COLUMN_NAME_TIMESTAMP +  " INT, " +
                        COLUMN_NAME_POWER_CONNECTED + " BOOLEAN, " +
                        COLUMN_NAME_CONNECTION_TYPE + " CHAR(3), " +
                        COLUMN_NAME_POWER_LEVEL + " FLOAT);";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class ConnectivityStatus implements BaseColumns {
        public static final String TABLE_NAME = "connectivity_status";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_CONNECTION_TYPE = "connectiontype";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_USER_ID + " INT, " +
                        COLUMN_NAME_TIMESTAMP +  " INT, " +
                        COLUMN_NAME_CONNECTION_TYPE + " TEXT);";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Call implements BaseColumns {
        public static final String TABLE_NAME = "calls";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_CALL_TYPE = "calltype";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_KNOWN_NUMBER = "known";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_USER_ID + " INT, " +
                        COLUMN_NAME_TIMESTAMP +  " INT, " +
                        COLUMN_NAME_NUMBER + " TEXT, " +
                        COLUMN_NAME_CALL_TYPE + "INT, " +
                        COLUMN_NAME_DURATION + "INT, " +
                        COLUMN_NAME_KNOWN_NUMBER + "BOOLEAN);";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Message implements BaseColumns {
        public static final String TABLE_NAME = "messages";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_MESSAGE_TYPE = "messagetype";
        public static final String COLUMN_NAME_MESSAGE_LENGTH = "length";
        public static final String COLUMN_NAME_KNOWN_NUMBER = "known";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_USER_ID + " INT, " +
                        COLUMN_NAME_TIMESTAMP +  " INT, " +
                        COLUMN_NAME_NUMBER + " TEXT, " +
                        COLUMN_NAME_MESSAGE_TYPE + "INT, " +
                        COLUMN_NAME_MESSAGE_LENGTH + "INT, " +
                        COLUMN_NAME_KNOWN_NUMBER + "BOOLEAN);";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
