package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyLocationInputView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyLocationOutputView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.util.HashMap;

/**
 * Created by user on 01/09/2017.
 */

public class MyLocationInputViewHolder extends MyTextViewViewHolder {

    protected MyLocationInputView locationInputView;

    public MyLocationInputViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyLocationInputView) view.findViewById(R.id.my_location_input));
    }

    @Override
    public void setTextView(MyTextView textView) {
        super.setTextView(textView);
        this.locationInputView = (MyLocationInputView) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.textView.setTitleText("Please select place:");
        this.textView.setDialogTitleText("Please select place:");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
    }
}
