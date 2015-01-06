package com.codavthesis.datacollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class PowerConnectionDataSource {
    private SQLiteDatabase db;
    private UsageDataDbHelper dbHelper;
    private String[] allColumns = {
            UsageDataContract.PowerConnection._ID,
            UsageDataContract.PowerConnection.COLUMN_NAME_USER_ID,
            UsageDataContract.PowerConnection.COLUMN_NAME_TIMESTAMP,
            UsageDataContract.PowerConnection.COLUMN_NAME_POWER_CONNECTED,
            UsageDataContract.PowerConnection.COLUMN_NAME_CONNECTION_TYPE,
            UsageDataContract.PowerConnection.COLUMN_NAME_POWER_LEVEL
    };

    public PowerConnectionDataSource(Context context) {
        dbHelper = new UsageDataDbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createPowerConnectionEntry(long timestamp, boolean isCharging, String chargeType, float powerLevel) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UsageDataContract.PowerConnection.COLUMN_NAME_USER_ID, MainActivity.USER_ID);
        values.put(UsageDataContract.PowerConnection.COLUMN_NAME_TIMESTAMP, timestamp);
        values.put(UsageDataContract.PowerConnection.COLUMN_NAME_POWER_CONNECTED, isCharging);
        values.put(UsageDataContract.PowerConnection.COLUMN_NAME_CONNECTION_TYPE, chargeType);
        values.put(UsageDataContract.PowerConnection.COLUMN_NAME_POWER_LEVEL, powerLevel);
        // Insert into db and return id
        long newRowId;
        newRowId = db.insert(
                UsageDataContract.PowerConnection.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public List<PowerConnectionEntry> getPowerConnectionLog() {
        List<PowerConnectionEntry> list = new ArrayList<PowerConnectionEntry>();
        Cursor c = db.query(
                UsageDataContract.PowerConnection.TABLE_NAME,   // The table to query
                null,                                           // The columns to return
                null,                                           // The columns for the WHERE clause
                null,                                           // The values for the WHERE clause
                null,                                           // don't group the rows
                null,                                           // don't filter by row groups
                null                                            // The sort order
        );

        //System.out.println(c.getCount());
        Time t = new Time();
        try {
            c.moveToFirst();
            int i;
            for (i=0; i<c.getCount(); i++) {
                list.add(cursorToEntry(c));
                t.set(c.getLong(2));
                String time = t.format("%Y:%m:%d %H:%M:%S");
                System.out.println(c.getInt(0) + " : " + c.getLong(2) + " : " + time + " : " + c.getString(4) + " : " + c.getFloat(5));
                c.moveToNext();
            }
        }
        catch (Exception e) {System.out.println("An exception was thrown");}
        return list;
    }

    private PowerConnectionEntry cursorToEntry(Cursor c) {
        PowerConnectionEntry entry = new PowerConnectionEntry();
        entry.setPowerConnectionId(c.getInt(0));
        entry.setUserId(c.getInt(1));
        entry.setTimestamp(c.getLong(2));
        if (c.getInt(3)==1) entry.setPowerConnected(true);
        else entry.setPowerConnected(false);
        entry.setConnectionType(c.getString(4));
        entry.setPowerLevel(c.getFloat(5));
        return entry;
    }
}