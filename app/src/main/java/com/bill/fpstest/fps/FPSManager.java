package com.bill.fpstest.fps;

import android.view.Choreographer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bill on 2018/7/13.
 */

public class FPSManager implements Choreographer.FrameCallback {

    private Choreographer choreographer;
    private long frameStartTime = 0;
    private int framesRenderCount = 0;
    private FPSChangeListener fpsChangeListener;

    FPSManager() {
        choreographer = Choreographer.getInstance();
    }

    void addListener() {
        choreographer.postFrameCallback(this);
    }

    void removeListener() {
        choreographer.removeFrameCallback(this);
    }

    void setFpsChangeListener(FPSChangeListener fpsChangeListener) {
        this.fpsChangeListener = fpsChangeListener;
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        long frameTimeMillis = TimeUnit.NANOSECONDS.toMillis(frameTimeNanos);
        if (frameStartTime > 0) {
            final long timeDiff = frameTimeMillis - frameStartTime;
            framesRenderCount++;
            if (timeDiff > 1000) {
                final double fps = framesRenderCount * 1000 / (double) timeDiff;
                if (fpsChangeListener != null) {
                    fpsChangeListener.update(fps);
                }

                frameStartTime = frameTimeMillis;
                framesRenderCount = 0;
            }
        } else {
            frameStartTime = frameTimeMillis;
        }

        addListener();
    }

}
