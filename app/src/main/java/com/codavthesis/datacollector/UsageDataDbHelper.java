package com.codavthesis.datacollector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class UsageDataDbHelper extends SQLiteOpenHelper {

    UsageDataDbHelper(Context context) {
        super(context, MainActivity.DATABASE_NAME, null, MainActivity.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsageDataContract.BatteryLevel.CREATE_TABLE);
        db.execSQL(UsageDataContract.PowerConnection.CREATE_TABLE);
        db.execSQL(UsageDataContract.ConnectivityStatus.CREATE_TABLE);
        db.execSQL(UsageDataContract.Call.CREATE_TABLE);
        db.execSQL(UsageDataContract.Message.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UsageDataContract.BatteryLevel.DELETE_TABLE);
        db.execSQL(UsageDataContract.PowerConnection.DELETE_TABLE);
        db.execSQL(UsageDataContract.ConnectivityStatus.DELETE_TABLE);
        db.execSQL(UsageDataContract.Call.DELETE_TABLE);
        db.execSQL(UsageDataContract.Message.DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

