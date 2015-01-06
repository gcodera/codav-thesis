package com.codavthesis.datacollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.Time;

/**
 * Created by ginettecodera on 1/5/15.
 */
public class ConnectivityStatusReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        //System.out.println("connectivity change");
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        String connectionType;
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) connectionType = activeNetwork.getTypeName();
        else connectionType = "NONE";

        // Get current time for timestamp
        Time t = new Time();
        t.setToNow();
        long timestamp = t.toMillis(true);

        ConnectivityStatusDataSource connectivityStatusDataSource = new ConnectivityStatusDataSource(context);
        connectivityStatusDataSource.open();
        long newRowId = connectivityStatusDataSource.createConnectivityStatusEntry(timestamp, connectionType);

        System.out.println(newRowId);
        connectivityStatusDataSource.getConnectivityStatusLog();
    }
}