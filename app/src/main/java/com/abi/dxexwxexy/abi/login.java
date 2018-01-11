package com.abi.dxexwxexy.abi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.TimeZone;

public class login extends AppCompatActivity {

    public WebView abi;
    public Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myContext = getApplicationContext();
        Toast.makeText(this, "Version 1.0", Toast.LENGTH_SHORT).show();
        loadAbimm();
        notifierAlarm();
        enableDailyNotification(myContext);
    }

    void loadAbimm() {
        abi = (WebView) findViewById(R.id.abiViewer);
        WebSettings abiSettings = abi.getSettings();
        abiSettings.setJavaScriptEnabled(true);
        abi.loadUrl("https://ess.abimm.com/");
    }

    void enableDailyNotification(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    void disableDailyNotification(Context context) {
        ComponentName receiver = new ComponentName(this, AlarmBootReceiver.class);
        PackageManager pm = this.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void notifierAlarm() {
        Intent notification = new Intent(this, NotificationService.class);
        AlarmManager notificationAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent openLogin = PendingIntent.getBroadcast(this, 1, notification, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar time = Calendar.getInstance(TimeZone.getDefault());
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.HOUR_OF_DAY, 17);
        notificationAlarm.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), AlarmManager.INTERVAL_DAY, openLogin);
    }

    public void checkFirstRun() {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
        }
    }

    @Override
    public void onBackPressed() {
        if (abi.canGoBack()) {
            abi.goBack();
        }
        else {
            finish();
        }
    }
}
