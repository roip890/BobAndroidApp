package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyButton;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.util.HashMap;

/**
 * Created by user on 01/09/2017.
 */

public class MyButtonViewHolder extends MyTextViewViewHolder {

    protected MyButton button;

    public MyButtonViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyButton) view.findViewById(R.id.my_button));
    }

    @Override
    public void setTextView(MyTextView textView) {
        super.setTextView(textView);
        this.button = (MyButton) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.button.setButtonColor(ContextCompat.getColor(context, R.color.colorPrimary));
        this.textView.setTitleText("Please push");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("button_color")) != null) {
            try {
                this.button.setButtonColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
