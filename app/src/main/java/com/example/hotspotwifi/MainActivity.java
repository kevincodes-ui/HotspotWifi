package com.example.hotspotwifi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        Spinner spinner = findViewById(R.id.security_protocol);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.security_protocol_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
