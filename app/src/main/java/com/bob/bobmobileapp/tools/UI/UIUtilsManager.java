package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 04/02/2018..
 */

public class UIUtilsManager {

    private static UIUtilsManager instance;


    public static UIUtilsManager get() {
        if (instance == null) {
            instance = new UIUtilsManager();
        }
        return instance;
    }

    //int to dp tool
    public int convertPixelsToDp(Context context, int px) {
        return px * ((int)(context.getResources().getDisplayMetrics().density));
    }

    public int convertPixelsToSp(Context context, int px) {
        return convertDpToSp(context, convertPixelsToDp(context, px));
    }

    public int convertDpToPixels(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public int convertSpToPixels(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public int convertDpToSp(Context context, float dp) {
        return (int) (convertDpToPixels(context, dp) / (float) convertSpToPixels(context, dp));
    }

    public int convertSpToDp(Context context, float sp) {
        return convertPixelsToDp(context, convertSpToPixels(context, sp));
    }

}
