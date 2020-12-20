package com.besoffen.isauf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        try {
            if (isNetworkAvailable()) {
                WebView myWebView = (WebView) findViewById(R.id.myWebView);
                myWebView.getSettings().setLoadsImagesAutomatically(true);
                myWebView.getSettings().setJavaScriptEnabled(true);
                myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                myWebView.loadUrl("http://isauf.net");
                myWebView.getSettings().setUseWideViewPort(true);
                myWebView.getSettings().setLoadWithOverviewMode(true);
            } else {
                loadErrorPage("Keine Internetverbindung");
            }
        } catch (Exception e) {
            loadErrorPage("Ups, da ist etwas schief gelaufen...");
        }
    }

    public void loadErrorPage(String text) {
        Context context = getApplicationContext();
        CharSequence toastText = text;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}