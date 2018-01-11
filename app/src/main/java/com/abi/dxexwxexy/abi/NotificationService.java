package com.abi.dxexwxexy.abi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.GregorianCalendar;

/**
 * Created by DxExWxExY on 1/3/2018.
 */

public class NotificationService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent openApp = new Intent(context, login.class);
        openApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent launchIntent = PendingIntent.getActivity(context, 1, openApp, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder dailyNotification = new NotificationCompat.Builder(context)
                .setContentIntent(launchIntent)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Check Your Account")
                .setContentText("Login to confirm or change availability.")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[] { 500, 1000})
                .setLights(Color.DKGRAY, 500, 1000)
                .setAutoCancel(true)
                .setColor(Color.BLACK);
        notification.notify(1, dailyNotification.build());
    }
}
