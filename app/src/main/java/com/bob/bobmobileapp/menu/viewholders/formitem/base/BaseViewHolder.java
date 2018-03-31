package com.bob.bobmobileapp.menu.viewholders.formitem.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.Theme;
import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.style.Fonts;
import com.bob.bobmobileapp.tools.UI.style.Icons;
import com.bob.bobmobileapp.tools.UI.views.MyBaseView;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.validators.Validator;
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
import com.vstechlab.easyfonts.EasyFonts;

import java.lang.reflect.Field;
import java.util.HashMap;

import io.realm.RealmResults;

/**
 * Created by user on 17/09/2017.
 */

public abstract class BaseViewHolder extends MyViewHolder {

    protected MyBaseView baseView;

    public BaseViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    public BaseViewHolder(Context context, View view) {
        this(context, view, null);
    }

    protected void setView(MyBaseView view) {
        super.setView(view);
        this.baseView = view;
    }

    protected abstract void initView(View view);

    protected void initialize() {
        super.initialize();
        this.baseView.setTitleText("Default title:");
        this.baseView.setTitleTextSize(UIUtilsManager.get().convertSpToPixels(this.context, 8));
        this.baseView.setTitleTextInputType(finals.inputTypes.get("none"));
        this.baseView.setTitleTextColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.baseView.setTitleTextTypeface(null);
        this.baseView.setTitleBoldEnable(false);
        this.baseView.setTitleItalicEnable(false);
        this.baseView.setTitleUnderlineEnable(false);
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("layout_background_color")) != null) {
            try {
                this.baseView.setBackgroundColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("layout_background")) != null) {
            this.baseView.setBackgroundImage(ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName())));
        }

        if ((curProperty = properties.get("width")) != null) {
            try {
                this.baseView.setWidth(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("height")) != null) {
            try {
                this.baseView.setHeight(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("gravity")) != null) {
            this.baseView.setGravity(finals.gravity.get(curProperty));
        }

        if ((curProperty = properties.get("hint_text")) != null) {
            this.baseView.setTitleText(curProperty);
        }

        if ((curProperty = properties.get("bottom_line_color")) != null) {
            try {
                this.baseView.setBottomLineColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("error_color")) != null) {
            try {
                this.baseView.setErrorTextColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("start_drawable_color")) != null) {
            try {
                this.baseView.setStartDrawableColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("end_drawable_color")) != null) {
            try {
                this.baseView.setEndDrawableColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if ((curProperty = properties.get("start_drawable")) != null) {
            this.baseView.setStartDrawable(Icons.get().findDrawable(this.context, curProperty));
        }
        if ((curProperty = properties.get("end_drawable")) != null) {
            this.baseView.setEndDrawable(Icons.get().findDrawable(this.context, curProperty));
        }
    }

}
