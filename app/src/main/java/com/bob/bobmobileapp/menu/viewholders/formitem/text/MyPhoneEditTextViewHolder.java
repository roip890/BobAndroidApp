package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyPhoneEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.util.HashMap;

/**
 * Created by user on 01/09/2017.
 */

public class MyPhoneEditTextViewHolder extends MyEditTextViewHolder {

    protected MyPhoneEditText phoneEditText;

    public MyPhoneEditTextViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyPhoneEditText) view.findViewById(R.id.my_phone_edit_text));
    }

    @Override
    public void setTextView(MyTextView textView) {
        super.setTextView(textView);
        this.phoneEditText = (MyPhoneEditText) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.textView.setTitleText("Please enter phone number:");
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
