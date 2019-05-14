package com.example.anim.inter;

import android.view.animation.Interpolator;

public class HesitateInterpolator implements Interpolator {
    public HesitateInterpolator(float input) {
        getInterpolation(input);
    }

    @Override
    public float getInterpolation(float input) {
        float x = 2.0f * input - 1.0f;
        return 0.5f * (x * x * x + 1.0f);
    }
}
