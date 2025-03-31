package com.example.urabanapp.utilites;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

public class CustomPageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
        float alphaFactor = Math.max(MIN_ALPHA, 1 - Math.abs(position));

        page.setScaleX(scaleFactor);
        page.setScaleY(scaleFactor);
        page.setAlpha(alphaFactor);
    }
}
