package com.bill.fpstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FPSManager fpsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fpsManager = new FPSManager();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fpsManager.removeListener();
    }

    public void handleRegister(View view) {
        fpsManager.addListener();
    }

    public void handleUnRegister(View view) {
        fpsManager.removeListener();
    }
}
