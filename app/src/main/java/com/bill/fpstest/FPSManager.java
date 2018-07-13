package com.bill.fpstest;

import android.util.Log;
import android.view.Choreographer;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bill on 2018/7/13.
 */

public class FPSManager implements Choreographer.FrameCallback {

    private Choreographer choreographer;

    public FPSManager() {
        choreographer = Choreographer.getInstance();
        addListener();
    }

    public void addListener() {
        choreographer.postFrameCallback(this);
    }

    public void removeListener() {
        choreographer.removeFrameCallback(this);
    }

    private long frameStartTime = 0;
    private int framesRendered = 0;
    private final DecimalFormat decimal = new DecimalFormat("#.0' fps'");

    @Override
    public void doFrame(long frameTimeNanos) {

        long frameTimeMillis = TimeUnit.NANOSECONDS.toMillis(frameTimeNanos);
        if (frameStartTime > 0) {
            final long timeDiff = frameTimeMillis - frameStartTime;
            framesRendered++;
            if (timeDiff > 1000) {
                final double fps = framesRendered * 1000 / (double) timeDiff;
                Log.e("Bill", framesRendered + "|fps:" + decimal.format(fps));

                frameStartTime = frameTimeMillis;
                framesRendered = 0;
            }
        } else {
            frameStartTime = frameTimeMillis;
        }

        addListener();
    }

}
