package com.bill.fpstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bill.fpstest.fps.FPS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FPS.getInstance().init(getApplicationContext());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FPS.getInstance().close();
    }

    public void handleRegister(View view) {
        FPS.getInstance().show(this);
    }

    public void handleUnRegister(View view) {
        FPS.getInstance().hide();
    }
}
