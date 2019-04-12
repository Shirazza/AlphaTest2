package com.example.user.alphatest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class BroadcastBatt extends BroadcastReceiver {

    private boolean msgFlag;
    public BroadcastBatt() {
        msgFlag=false;
    }

    @Override
    public void onReceive (Context context, Intent intent) {
        int level=20;
        int battLevel=intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int battStat=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        if (!msgFlag &&
                battLevel<=level &&
                battStat!=BatteryManager.BATTERY_STATUS_CHARGING) {
            msgFlag=true;
            Toast.makeText(context, "Low battery: "+battLevel+"%", Toast.LENGTH_LONG).show();
        }
    }
}