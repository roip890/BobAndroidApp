package com.bob.bobmobileapp.tools.UI.style;

import android.content.Context;
import android.graphics.Typeface;

import com.vstechlab.easyfonts.EasyFonts;

/**
 * Created by User on 02/02/2018.
 */

public class Fonts {

    private static Fonts instance;


    public static Fonts get() {
        if (instance == null) {
            instance = new Fonts();
        }
        return instance;
    }

    public Typeface findTypeface(Context context, String typefaceName) {
        Typeface typeface;
        if ((typeface = Typeface.createFromAsset(context.getAssets(), typefaceName)) != null) {
            return typeface;
        } else {
            try {
                return  (Typeface) EasyFonts.class.getMethod(typefaceName,new Class[] { Context.class }).invoke(null, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
