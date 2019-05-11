package com.sinqupa.chofer.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sinqupa.chofer.service.LocationUpdatesService;


public class LocationUpdatesBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, LocationUpdatesService.class));
    }
}
