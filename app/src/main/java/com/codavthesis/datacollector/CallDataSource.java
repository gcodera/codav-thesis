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
 * Created by ginettecodera on 1/6/15.
 */
public class CallDataSource {
    private SQLiteDatabase db;
    private UsageDataDbHelper dbHelper;
    private String[] allColumns = {
            UsageDataContract.Call._ID,
            UsageDataContract.Call.COLUMN_NAME_USER_ID,
            UsageDataContract.Call.COLUMN_NAME_TIMESTAMP,
            UsageDataContract.Call.COLUMN_NAME_NUMBER,
            UsageDataContract.Call.COLUMN_NAME_CALL_TYPE,
            UsageDataContract.Call.COLUMN_NAME_DURATION,
            UsageDataContract.Call.COLUMN_NAME_KNOWN_NUMBER
    };

    public CallDataSource(Context context) {
        dbHelper = new UsageDataDbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createCallsEntry(long timestamp, String number, int callType, long duration, boolean known){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UsageDataContract.Call.COLUMN_NAME_USER_ID, MainActivity.USER_ID);
        values.put(UsageDataContract.Call.COLUMN_NAME_TIMESTAMP, timestamp);
        values.put(UsageDataContract.Call.COLUMN_NAME_NUMBER, number);
        values.put(UsageDataContract.Call.COLUMN_NAME_CALL_TYPE, callType);
        values.put(UsageDataContract.Call.COLUMN_NAME_DURATION, duration);
        values.put(UsageDataContract.Call.COLUMN_NAME_KNOWN_NUMBER, known);
        // Insert into db and return id
        long newRowId;
        newRowId = db.insert(
                UsageDataContract.Call.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public List<CallEntry> getCallLog() {
        List<CallEntry> list = new ArrayList<CallEntry>();
        Cursor c = db.query(
                UsageDataContract.Call.TABLE_NAME,   // The table to query
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
                //System.out.println(c.getInt(0) + " : " + c.getLong(2) + " : " + time + " : " + c.getString(4) + " : " + c.getFloat(5));
                c.moveToNext();
            }
        }
        catch (Exception e) {System.out.println("An exception was thrown");}
        return list;
    }

    private CallEntry cursorToEntry(Cursor c) {
        CallEntry entry = new CallEntry();
        entry.setCallId(c.getInt(0));
        entry.setUserId(c.getInt(1));
        entry.setTimestamp(c.getLong(2));
        entry.setNumber(c.getString(3));
        entry.setCallType(c.getInt(4));
        entry.setDuration(c.getLong(5));
        if (c.getInt(6)==1) entry.setKnownNumber(true);
        else entry.setKnownNumber(false);
        return entry;
    }
}
