package com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog;

import android.content.Context;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyTextViewViewHolder;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyDateTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class MyDateViewViewHolder extends MyTextViewViewHolder {

    protected MyDateTextView dateTextView;

    public MyDateViewViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyDateTextView) view.findViewById(R.id.my_date_text_view));
    }

    @Override
    public void setTextView(MyTextView textView) {
        super.setTextView(textView);
        this.dateTextView = (MyDateTextView) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        Calendar calendar = Calendar.getInstance();
        this.dateTextView.setYears(calendar.get(Calendar.YEAR));
        this.dateTextView.setMonths(calendar.get(Calendar.MONTH));
        this.dateTextView.setDays(calendar.get(Calendar.DAY_OF_MONTH));
        this.textView.setTitleText("Please select date:");
        this.textView.setDialogTitleText("Please select date:");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("date")) != null) {
            String[] timeParts = curProperty.split(Pattern.quote("/"));
            if (timeParts.length == 3) {
                this.dateTextView.setYears(Integer.parseInt(timeParts[0]));
                this.dateTextView.setMonths(Integer.parseInt(timeParts[1]));
                this.dateTextView.setDays(Integer.parseInt(timeParts[2]));
            }
        }
    }
}
