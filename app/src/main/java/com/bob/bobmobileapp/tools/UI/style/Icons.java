package com.bob.bobmobileapp.tools.UI.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.devicon_typeface_library.DevIcon;
import com.mikepenz.entypo_typeface_library.Entypo;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.foundation_icons_typeface_library.FoundationIcons;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.meteocons_typeface_library.Meteoconcs;
import com.mikepenz.octicons_typeface_library.Octicons;
import com.mikepenz.pixeden_7_stroke_typeface_library.Pixeden7Stroke;
import com.mikepenz.typeicons_typeface_library.Typeicons;
import com.mikepenz.weather_icons_typeface_library.WeatherIcons;

/**
 * Created by User on 31/12/2017.
 */

public class Icons {

    private static Icons instance;


    public static Icons get() {
        if (instance == null) {
            instance = new Icons();
        }
        return instance;
    }

    //extern this functions
    public Drawable findDrawable(Context context, String drawableName) {
        Drawable drawable;
        try{
            drawable = ContextCompat.getDrawable(context, context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName()));
            return drawable;
        } catch (Exception e1) {
            try {
                return new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(GoogleMaterial.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(FontAwesome.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Octicons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Meteoconcs.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(CommunityMaterial.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(WeatherIcons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Typeicons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Entypo.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(DevIcon.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(FoundationIcons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Ionicons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Pixeden7Stroke.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
