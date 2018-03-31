package com.bob.bobmobileapp.menu.viewholders.formitem.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.afollestad.materialdialogs.Theme;
import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.BaseViewHolder;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.style.Fonts;
import com.bob.bobmobileapp.tools.UI.style.Icons;
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

import java.util.HashMap;

import io.realm.RealmResults;


/**
 * Created by user on 01/09/2017.
 */

public abstract class MyViewHolder extends RecyclerView.ViewHolder {

    protected Context context;
    protected MyView view;

    public MyViewHolder(Context context, View view, Validator validator) {
        super(view);
        this.context = context;
        initView(view);
        initialize();
    }

    public MyViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public void configureFormItem(FormItem formItem) {
        HashMap<String, String> properties = new HashMap<String, String>();
        RealmResults<FormItemProperty> RealmProperties = RealmController.with(BOBApplication.get()).getPropertiesOfFormItem(formItem.getId());
        for (FormItemProperty property : RealmProperties) {
            properties.put(property.getKey(), property.getValue());
        }
        updateProperties(properties);
    }

    protected void setView(MyView view) {
        this.view = view;
    }

    protected abstract void initView(View view);

    protected void initialize() {
        this.view.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
        this.view.setGravity(finals.gravity.get("start"));
        this.view.setTitleText("Default title");

        this.view.setBottomLineColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.view.setErrorTextColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.view.setStartDrawableColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.view.setEndDrawableColor(ContextCompat.getColor(context, R.color.textColorPrimary));


        this.view.setBottomLineEnable(false);

        //dialog
//        this.view.setDialogBackGroundColor(ContextCompat.getColor(context, R.color.windowBackground));
//        this.view.setDialogTitleColor(ContextCompat.getColor(context, R.color.textColorPrimary));
//        this.view.setDialogContentColor(ContextCompat.getColor(context, R.color.textColorPrimary));
//        this.view.setDialogPositiveColor(ContextCompat.getColor(context, R.color.colorPrimary));
//        this.view.setDialogNegativeColor(ContextCompat.getColor(context, R.color.colorError));
//        this.view.setDialogNeutralColor(ContextCompat.getColor(context, R.color.textColorPrimary));
//        this.view.setDialogDividerColor(ContextCompat.getColor(context, R.color.colorPrimary));
//        this.view.setDialogLinkColor(ContextCompat.getColor(context, R.color.colorPrimary));
//
//        this.view.setDialogTitleGravity(finals.dialogGravity.get("center"));
//        this.view.setDialogContentGravity(finals.dialogGravity.get("center"));
//        this.view.setDialogButtonsGravity(finals.dialogGravity.get("end"));
//        this.view.setDialogButtonsStackedGravity(finals.dialogGravity.get("center"));
//
//
//        this.view.setDialogIsPositiveFocus(false);
//        this.view.setDialogIsNegativeFocus(true);
//
//        this.view.setDialogTitleText("Please enter your text");
//        this.view.setDialogPositiveText("Ok");
//        this.view.setDialogNegativeText("Cancle");
//        this.view.setDialogNeutralText(null);
//
//        this.view.setDialogIconMaxSize((int) context.getResources().getDimension(R.dimen.dialog_default_max_icon_size));
//        this.view.setDialogIsButtonsStacked(finals.dialogStackingBehavior.get("never"));
//        this.view.setDialogTheme(Theme.LIGHT);
    }

    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("layout_background_color")) != null) {
            try {
                this.view.setBackgroundColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("layout_background")) != null) {
            this.view.setBackgroundImage(ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName())));
        }

        if ((curProperty = properties.get("width")) != null) {
            try {
                this.view.setWidth(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("height")) != null) {
            try {
                this.view.setHeight(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("gravity")) != null) {
            this.view.setGravity(finals.gravity.get(curProperty));
        }

        if ((curProperty = properties.get("title_text")) != null) {
            this.view.setTitleText(curProperty);
        }

        if ((curProperty = properties.get("bottom_line_color")) != null) {
            try {
                this.view.setBottomLineColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("error_color")) != null) {
            try {
                this.view.setErrorTextColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("start_drawable_color")) != null) {
            try {
                this.view.setStartDrawableColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("end_drawable_color")) != null) {
            try {
                this.view.setEndDrawableColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if ((curProperty = properties.get("start_drawable")) != null) {
            this.view.setStartDrawable(Icons.get().findDrawable(this.context, curProperty));
        }
        if ((curProperty = properties.get("end_drawable")) != null) {
            this.view.setEndDrawable(Icons.get().findDrawable(this.context, curProperty));
        }

        //dialog
        if ((curProperty = properties.get("is_dialog_enable")) != null) {
            if (curProperty.equals("true")) {
                this.view.setDialogEnable(true);
            } else if (curProperty.equals("false")) {
                this.view.setDialogEnable(false);
            }
        }
        if ((curProperty = properties.get("dialog_background_color")) != null) {
            try {
                this.view.setDialogBackGroundColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_title_color")) != null) {
            try {
                this.view.setDialogTitleColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_content_color")) != null) {
            try {
                this.view.setDialogContentColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_positive_color")) != null) {
            try {
                this.view.setDialogPositiveColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_negative_color")) != null) {
            try {
                this.view.setDialogNegativeColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_neutral_color")) != null) {
            try {
                this.view.setDialogNeutralColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_divider_color")) != null) {
            try {
                this.view.setDialogDividerColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_link_color")) != null) {
            try {
                this.view.setDialogLinkColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_title_gravity")) != null) {
            this.view.setDialogTitleGravity(finals.dialogGravity.get(curProperty));
        }
        if ((curProperty = properties.get("dialog_content_gravity")) != null) {
            this.view.setDialogContentGravity(finals.dialogGravity.get(curProperty));
        }
        if ((curProperty = properties.get("dialog_buttons_gravity")) != null) {
            this.view.setDialogButtonsGravity(finals.dialogGravity.get(curProperty));
        }
        if ((curProperty = properties.get("dialog_buttons_stacked_gravity")) != null) {
            this.view.setDialogButtonsStackedGravity(finals.dialogGravity.get(curProperty));
        }
        if ((curProperty = properties.get("dialog_icon_max_size")) != null) {
            try {
                this.view.setDialogIconMaxSize(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_is_positive_focus")) != null) {
            if (curProperty.equals("true")) {
                this.view.setDialogIsPositiveFocus(true);
            } else if (curProperty.equals("false")) {
                this.view.setDialogIsPositiveFocus(false);
            }
        }
        if ((curProperty = properties.get("dialog_is_negative_focus")) != null) {
            if (curProperty.equals("true")) {
                this.view.setDialogIsNegativeFocus(true);
            } else if (curProperty.equals("false")) {
                this.view.setDialogIsNegativeFocus(false);
            }
        }
        if ((curProperty = properties.get("dialog_is_buttons_stacked")) != null) {
            this.view.setDialogIsButtonsStacked(finals.dialogStackingBehavior.get(curProperty));
        }
        if ((curProperty = properties.get("dialog_title_text")) != null) {
            this.view.setDialogTitleText(curProperty);
        }
        if ((curProperty = properties.get("dialog_content_text")) != null) {
            this.view.setDialogContentText(curProperty);
        }
        if ((curProperty = properties.get("dialog_positive_text")) != null) {
            this.view.setDialogPositiveText(curProperty);
        }
        if ((curProperty = properties.get("dialog_negative_text")) != null) {
            this.view.setDialogNegativeText(curProperty);
        }
        if ((curProperty = properties.get("dialog_neutral_text")) != null) {
            this.view.setDialogNeutralText(curProperty);
        }
        if ((curProperty = properties.get("dialog_theme")) != null) {
            if (curProperty.equals("light")) {
                this.view.setDialogTheme(Theme.LIGHT);
            } else if (curProperty.equals("dark")) {
                this.view.setDialogTheme(Theme.DARK);
            }
        }
        if ((curProperty = properties.get("dialog_title_and_buttons_font")) != null) {
            this.view.setDialogTitleAndButtonsFont(Fonts.get().findTypeface(this.context, curProperty));
        }
        if ((curProperty = properties.get("dialog_text_font")) != null) {
            this.view.setDialogTextFont(Fonts.get().findTypeface(this.context, curProperty));
        }
        if ((curProperty = properties.get("dialog_icon")) != null) {
            this.view.setDialogIcon(Icons.get().findDrawable(this.context, curProperty));
        }

    }

}
