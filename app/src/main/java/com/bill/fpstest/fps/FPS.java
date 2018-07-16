package com.bill.fpstest.fps;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Bill on 2018/7/16.
 */

public class FPS implements FPSChangeListener {

    private final DecimalFormat decimal = new DecimalFormat("#.00' fps'");
    private FPSManager fpsManager;
    private ViewGroup decorView;
    private TextView textView;
    private FrameLayout.LayoutParams layoutParams;

    private static class SingletonHolder {
        private final static FPS instance = new FPS();
    }

    public static FPS getInstance() {
        return SingletonHolder.instance;
    }

    private FPS() {
        fpsManager = new FPSManager();
        fpsManager.setFpsChangeListener(this);
    }

    public void init(Context context) {
        fpsManager.addListener();

        textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(16);
        textView.setAlpha(0.2f);

        layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
    }

    public void close() {
        removeView();
        fpsManager.removeListener();
        textView = null;
        layoutParams = null;
    }

    public void show(Activity activity) {
        addView(activity);
    }

    public void hide() {
        removeView();
    }

    private void removeView() {
        if (decorView != null && textView != null) {
            decorView.removeView(textView);
        }
    }

    private void addView(Activity activity) {
        decorView = (ViewGroup) activity.getWindow().getDecorView();
        if (textView != null) {
            decorView.addView(textView, layoutParams);
        }
    }


    @Override
    public void update(double fps) {
        if (textView != null)
            textView.setText(decimal.format(fps));
    }
}
