package com.aluxian.loremui.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Dp {

    private static final DisplayMetrics DISPLAY_METRICS = Resources.getSystem().getDisplayMetrics();

    public static final int PX_04 = toPx(4);
    public static final int PX_08 = toPx(8);
    public static final int PX_16 = toPx(16);
    public static final int PX_48 = toPx(48);
    public static final int PX_56 = toPx(56);
    public static final int PX_72 = toPx(72);

    public static int toPx(float units) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, units, DISPLAY_METRICS));
    }

}
