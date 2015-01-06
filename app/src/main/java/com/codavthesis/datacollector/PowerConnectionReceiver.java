package com.codavthesis.datacollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.text.format.Time;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Create an intent with info because fuck you Android
        final Intent mIntent = context.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int status = mIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = mIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        String chargeType;
        if (usbCharge) chargeType = "usb";
        else if (acCharge) chargeType = "ac";
        else chargeType = "nwp";

        int level = mIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = mIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float powerLevel = level / (float)scale * 100;

        // Get current time for timestamp
        Time t = new Time();
        t.setToNow();
        long timestamp = t.toMillis(true);

        PowerConnectionDataSource powerConnectionDataSource = new PowerConnectionDataSource(context);
        powerConnectionDataSource.open();
        long chargeRowId = powerConnectionDataSource.createPowerConnectionEntry(timestamp, isCharging, chargeType, powerLevel);

        System.out.println(chargeRowId);
        powerConnectionDataSource.getPowerConnectionLog();
    }
}
