package com.codavthesis.datacollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class BatteryLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String batteryLevel = "okay";
        if (intent.getAction() == Intent.ACTION_BATTERY_LOW) batteryLevel = "low";

        // Get current time for timestamp
        Time t = new Time();
        t.setToNow();
        long timestamp = t.toMillis(true);

        //WRITE TO DATABASE
        BatteryLevelDataSource batteryLevelDataSource = new BatteryLevelDataSource(context);
        batteryLevelDataSource.open();
        long newRowId = batteryLevelDataSource.createBatteryLevelEntry(timestamp, batteryLevel);

        System.out.println(newRowId);
        batteryLevelDataSource.getBatteryLevelLog();
    }
}