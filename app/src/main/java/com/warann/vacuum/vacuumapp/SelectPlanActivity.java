package com.warann.vacuum.vacuumapp;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;



/**
 * Created by PINGZ on 08/03/2018.
 */

public class SelectPlanActivity {

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    public void sendPlan(View view) {
        // Do something in response to button click
    }
}
