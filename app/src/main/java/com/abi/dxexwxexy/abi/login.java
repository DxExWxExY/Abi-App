package com.abi.dxexwxexy.abi;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    WebView abi;

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
    }

    @Override
    public void onBackPressed() {
        if (abi.canGoBack()) {
            abi.goBack();
        }
    }
}
