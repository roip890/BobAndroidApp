package com.bob.bobmobileapp.tools.UI.views.recyclerviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bob.bobmobileapp.tools.UI.views.recyclerviews.adapters.MyRecyclerViewMultiChoiceAdapter;

import java.util.ArrayList;

/**
 * Created by User on 24/12/2017.
 */

public class MyCheckBoxGroup extends MyBaseTextGroup<CheckBox> {

    public MyCheckBoxGroup(Context context) {
        this(context, null);
    }

    public MyCheckBoxGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCheckBoxGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.adapter = new MyRecyclerViewMultiChoiceAdapter();
        this.recyclerView.setAdapter(this.adapter);

    }

    @Override
    protected CheckBox createViewType() {
        return new CheckBox(this.getContext());
    }

    public int getMaxCheckedViews() {
        return ((MyRecyclerViewMultiChoiceAdapter) this.adapter).getMaxChecked();
    }

    public void setMaxCheckedViews(int maxChecked) {
        ((MyRecyclerViewMultiChoiceAdapter) this.adapter).setMaxChecked(maxChecked);
    }

    public ArrayList<CheckBox> getCheckedViews() {
        return ((MyRecyclerViewMultiChoiceAdapter) this.adapter).getCheckedViews();
    }

    public ArrayList<Integer> getCheckedViewIndices() {
        return ((MyRecyclerViewMultiChoiceAdapter) this.adapter).getCheckedViewsIndices();
    }

}
