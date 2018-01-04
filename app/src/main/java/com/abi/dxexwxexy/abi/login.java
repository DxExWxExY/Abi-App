package com.abi.dxexwxexy.abi;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.Calendar;

public class login extends AppCompatActivity {

    public WebView abi;
    public PendingIntent openLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*WebView & ToastCode*/
        Toast.makeText(this, "Version 1.0", Toast.LENGTH_SHORT).show();
        abi = (WebView) findViewById(R.id.abiViewer);
        WebSettings abiSettings = abi.getSettings();
        abiSettings.setJavaScriptEnabled(true);
        abi.loadUrl("https://ess.abimm.com/");
        /************************************/
        Intent login = new Intent(this, NotificationService.class);
        AlarmManager notification = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent openLogin = PendingIntent.getBroadcast(this, 1, login, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        notification.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, openLogin);
        /************************************/
    }

    @Override
    public void onBackPressed() {
        if (abi.canGoBack()) {
            abi.goBack();
        }
    }
}
