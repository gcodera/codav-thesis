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
public class ConnectivityStatusDataSource {
    private SQLiteDatabase db;
    private UsageDataDbHelper dbHelper;
    private String[] allColumns = {
            UsageDataContract.ConnectivityStatus._ID,
            UsageDataContract.ConnectivityStatus.COLUMN_NAME_USER_ID,
            UsageDataContract.ConnectivityStatus.COLUMN_NAME_TIMESTAMP,
            UsageDataContract.ConnectivityStatus.COLUMN_NAME_CONNECTION_TYPE
    };

    public ConnectivityStatusDataSource(Context context) {
        dbHelper = new UsageDataDbHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createConnectivityStatusEntry(long timestamp, String connectionType) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UsageDataContract.ConnectivityStatus.COLUMN_NAME_USER_ID, MainActivity.USER_ID);
        values.put(UsageDataContract.ConnectivityStatus.COLUMN_NAME_TIMESTAMP, timestamp);
        values.put(UsageDataContract.ConnectivityStatus.COLUMN_NAME_CONNECTION_TYPE, connectionType);

        // Insert into db and return id
        long newRowId;
        newRowId = db.insert(
                UsageDataContract.ConnectivityStatus.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    //can be changed in the future to get user id
    //change to List<BatteryLevelDataEntry> in a bit
    public List<ConnectivityStatusEntry> getConnectivityStatusLog(){
        List<ConnectivityStatusEntry> list = new ArrayList<ConnectivityStatusEntry>();
        Cursor c = db.query(
                UsageDataContract.ConnectivityStatus.TABLE_NAME,    // The table to query
                null,                                               // The columns to return
                null,                                               // The columns for the WHERE clause
                null,                                               // The values for the WHERE clause
                null,                                               // don't group the rows
                null,                                               // don't filter by row groups
                null                                                // The sort order
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
                System.out.println(c.getInt(0) + " : " + c.getLong(2) + " : " + time + " : " + c.getString(3));
                c.moveToNext();
            }
        }
        catch (Exception e) {System.out.println("An exception was thrown");}
        return list;
    }

    private ConnectivityStatusEntry cursorToEntry(Cursor c) {
        ConnectivityStatusEntry entry = new ConnectivityStatusEntry();
        entry.setConnectivityStatusId(c.getInt(0));
        entry.setUserId(c.getInt(1));
        entry.setTimestamp(c.getLong(2));
        entry.setConnectionType(c.getString(3));
        return entry;
    }
}