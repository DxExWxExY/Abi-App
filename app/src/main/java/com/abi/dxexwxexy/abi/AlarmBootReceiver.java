package com.abi.dxexwxexy.abi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by dxexwxexy on 1/10/18.
 */

public class AlarmBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent notification = new Intent(context, NotificationService.class);
            AlarmManager notificationAlarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent openLogin = PendingIntent.getBroadcast(context, 1, notification, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar time = Calendar.getInstance(TimeZone.getDefault());
            time.set(Calendar.SECOND, 0);
            time.set(Calendar.MINUTE, 0);
            time.set(Calendar.HOUR_OF_DAY, 17);
            notificationAlarm.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), AlarmManager.INTERVAL_DAY, openLogin);
        }
    }
}
