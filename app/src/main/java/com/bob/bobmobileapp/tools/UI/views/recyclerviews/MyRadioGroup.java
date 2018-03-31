package com.bob.bobmobileapp.tools.UI.views.recyclerviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bob.bobmobileapp.tools.UI.views.recyclerviews.adapters.MyRecyclerViewSingleChoiceAdapter;

/**
 * Created by User on 19/12/2017.
 */

public class MyRadioGroup extends MyBaseTextGroup<RadioButton> {

    public MyRadioGroup(Context context) {
        this(context, null);
    }

    public MyRadioGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.adapter = new MyRecyclerViewSingleChoiceAdapter();
        this.recyclerView.setAdapter(this.adapter);

    }

    @Override
    protected RadioButton createViewType() {
        return new RadioButton(this.getContext());
    }

    public RadioButton getCheckedView() {
        return ((MyRecyclerViewSingleChoiceAdapter)this.adapter).getCheckedView();
    }

    public int getCheckedViewIndex() {
        return ((MyRecyclerViewSingleChoiceAdapter)this.adapter).getCheckedViewIndex();
    }


}
