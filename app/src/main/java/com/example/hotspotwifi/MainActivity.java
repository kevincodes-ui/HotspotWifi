package com.example.hotspotwifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    WifiManager wifiManager;
    WifiConfiguration wifiConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize(){
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "MyDummySSID";
        changeStateWifiApp(true);
    }

    private void changeStateWifiApp(boolean activated) {
        Method method;
        try {
            method = wifiManager.getClass().getDeclaredMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            method.invoke(wifiManager, wifiConfiguration, activated);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
