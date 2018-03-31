package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyLocationOutputView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyDateTextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class MyLocationOutputViewHolder extends MyTextViewViewHolder {

    protected MyLocationOutputView locationOutputView;

    public MyLocationOutputViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyLocationOutputView) view.findViewById(R.id.my_location_output));
    }

    @Override
    public void setTextView(MyTextView textView) {
        super.setTextView(textView);
        this.locationOutputView = (MyLocationOutputView) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.textView.setTitleText("Please show map:");
        this.textView.setDialogTitleText("Please show map:");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
    }
}
