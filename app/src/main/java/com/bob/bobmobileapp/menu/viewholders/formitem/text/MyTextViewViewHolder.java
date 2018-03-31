package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.Theme;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.BaseViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.MyViewHolder;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.style.Fonts;
import com.bob.bobmobileapp.tools.UI.style.Icons;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.validators.Validator;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.HashMap;


/**
 * Created by user on 01/09/2017.
 */

public class MyTextViewViewHolder extends MyViewHolder {

    protected MyTextView textView;

    public MyTextViewViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    public MyTextViewViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public MyTextView getTextView() {
        return textView;
    }

    public void setTextView(MyTextView textView) {
        super.setView(textView);
        this.textView = textView;
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyTextView) view.findViewById(R.id.my_text_view));
    }

    @Override
    protected void initialize() {
        super.initialize();

        this.textView.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);

        this.textView.setText("");

        this.textView.setTextColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.textView.setTitleColor(ContextCompat.getColor(context, R.color.textColorPrimary));

        this.textView.setTextSize(UIUtilsManager.get().convertSpToPixels(this.context, 8));
        this.textView.setTextInputType(finals.inputTypes.get("none"));

        this.textView.setBoldEnable(false);
        this.textView.setUnderlineEnable(false);
        this.textView.setItalicEnable(false);

    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("layout_background")) != null) {
            this.textView.setBackgroundImage(ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName())));
        }

        if ((curProperty = properties.get("width")) != null) {
            try {
                this.textView.setWidth(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("height")) != null) {
            try {
                this.textView.setHeight(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if ((curProperty = properties.get("text")) != null) {
            this.textView.setText(curProperty);
        }

        if ((curProperty = properties.get("text_color")) != null) {
            try {
                this.textView.setTextColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("title_color")) != null) {
            try {
                this.textView.setTitleColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if ((curProperty = properties.get("font_size")) != null) {
            try {
                this.textView.setTextSize(UIUtilsManager.get().convertPixelsToSp(this.context, Integer.parseInt(curProperty)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("font_type")) != null) {
            this.textView.setTextTypeface(Fonts.get().findTypeface(this.context, curProperty));
        }
        if ((curProperty = properties.get("input_type")) != null) {
            this.textView.setTextInputType(finals.inputTypes.get(curProperty));
        }

        if ((curProperty = properties.get("bold_text")) != null) {
            if (curProperty.equals("true")) {
                this.textView.setBoldEnable(true);
            } else if (curProperty.equals("false")) {
                this.textView.setBoldEnable(false);
            }
        }
        if ((curProperty = properties.get("underline_text")) != null) {
            if (curProperty.equals("true")) {
                this.textView.setUnderlineEnable(true);
           } else if (curProperty.equals("false")) {
                this.textView.setUnderlineEnable(false);
            }
        }
        if ((curProperty = properties.get("italic_text")) != null) {
            if (curProperty.equals("true")) {
                this.textView.setItalicEnable(true);
            } else if (curProperty.equals("false")) {
                this.textView.setItalicEnable(false);
            }
        }

    }

}
