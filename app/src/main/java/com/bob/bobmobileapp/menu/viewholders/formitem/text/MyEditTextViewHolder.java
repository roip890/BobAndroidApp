package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyDateTextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class MyEditTextViewHolder extends MyTextViewViewHolder {

    protected MyEditText editText;

    public MyEditTextViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyEditText) view.findViewById(R.id.my_edit_text));
    }

    @Override
    public void setTextView(MyTextView textView) {
        super.setTextView(textView);
        this.editText = (MyEditText) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.editText.setEndDrawableOnFocusOnly(true);
        this.textView.setTextInputType(finals.inputTypes.get("text"));
        this.editText.setCursorColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.editText.setTitleText("Please enter your text:");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("cursor_color")) != null) {
            try {
                this.editText.setCursorColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
