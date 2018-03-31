package com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog;

import android.content.Context;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyTextViewViewHolder;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTimeTextView;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class MyTimeViewViewHolder extends MyTextViewViewHolder {

    protected MyTimeTextView timeTextView;

    public MyTimeViewViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyTimeTextView) view.findViewById(R.id.my_time_text_view));
    }

    @Override
    public void setTextView(MyTextView textView) {
        super.setTextView(textView);
        this.timeTextView = (MyTimeTextView) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.timeTextView.setHours(0);
        this.timeTextView.setMinutes(0);
        this.textView.setTitleText("Please select time:");
        this.textView.setDialogTitleText("Please select time:");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("time")) != null) {
            String[] timeParts = curProperty.split(Pattern.quote(":"));
            if (timeParts.length == 2) {
                timeTextView.setHours(Integer.parseInt(timeParts[0]));
                timeTextView.setWidth(Integer.parseInt(timeParts[1]));
            }
        }

    }

}
