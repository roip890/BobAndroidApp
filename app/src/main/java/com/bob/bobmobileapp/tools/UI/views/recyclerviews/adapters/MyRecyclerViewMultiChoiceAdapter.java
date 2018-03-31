package com.bob.bobmobileapp.tools.UI.views.recyclerviews.adapters;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;

/**
 * Created by User on 17/12/2017.
 */

public class MyRecyclerViewMultiChoiceAdapter extends MyRecyclerViewBaseAdapter<CheckBox> {

    private ArrayList<CheckBox> checkedViews;
    private int maxChecked;

    public MyRecyclerViewMultiChoiceAdapter(ArrayList<CheckBox> views, ArrayList<Integer> checkedIndices) {
        this.views = views;
        this.checkedViews = new ArrayList<CheckBox>();

        for (int i = 0; i < this.views.size(); i++) {
            this.setOnCheckedListener(this.views.get(i));
            if (checkedIndices.contains(i)){
                this.views.get(i).setChecked(true);
                this.checkedViews.add(views.get(i));
            } else {
                this.views.get(i).setChecked(false);
            }
        }
    }

    public MyRecyclerViewMultiChoiceAdapter(ArrayList<CheckBox> views) {
        this(views, new ArrayList<Integer>());
    }

    public MyRecyclerViewMultiChoiceAdapter() {
        this(new ArrayList<CheckBox>(), new ArrayList<Integer>());
    }

    @Override
    public void addView(CheckBox viewToAdd) {
        this.addView(this.views.size(), viewToAdd);
    }

    @Override
    public void addView(int index, CheckBox viewToAdd) {
        if (!this.isViewExist(viewToAdd)) {
            viewToAdd.setChecked(false);
            this.setOnCheckedListener(viewToAdd);
            this.views.add(index, viewToAdd);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public void addAllViews(ArrayList<CheckBox> viewsToAdd) {
        this.addAllViews(this.views.size(), viewsToAdd);
    }

    @Override
    public void addAllViews(int index, ArrayList<CheckBox> viewsToAdd) {
        ArrayList<CheckBox> views = new ArrayList<CheckBox>();
        for (CheckBox view : viewsToAdd) {
            if (!this.isViewExist(view)) {
                view.setChecked(false);
                this.setOnCheckedListener(view);
                views.add(view);
            }
        }
        this.views.addAll(index, viewsToAdd);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeView(CheckBox viewToRemove) {
        super.removeView(viewToRemove);
    }

    @Override
    public void removeView(int indexToRemove) {
        this.removeView(this.views.get(indexToRemove));
    }

    public ArrayList<CheckBox> getCheckedViews() {
        return this.checkedViews;
    }

    public ArrayList<Integer> getCheckedViewsIndices() {
        ArrayList<Integer> checkedIndices = new ArrayList<Integer>();
        for (CheckBox checkBox :
                this.checkedViews) {
            if (this.isViewExist(checkBox)) {
                checkedIndices.add(this.views.indexOf(checkBox));
            }
        }
        return checkedIndices;
    }

    public int getMaxChecked() {
        return this.maxChecked;
    }

    public void setMaxChecked(int maxChecked) {
        this.maxChecked = maxChecked;
    }

    private void setOnCheckedListener(final CheckBox radioButton) {
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton checkBoxView, boolean isChecked) {
                if (isChecked) {
                    if ((maxChecked != -1 ) && (checkedViews.size() < maxChecked) && isViewExist((CheckBox) checkBoxView)) {
                        checkedViews.add((CheckBox) checkBoxView);
                        checkBoxView.setChecked(true);
                    } else {
                        checkBoxView.setChecked(false);
                    }
                } else {
                    if (checkedViews.contains((CheckBox) checkBoxView)) {
                        checkedViews.remove((CheckBox) checkBoxView);
                    }
                    checkBoxView.setChecked(false);
                }
            }
        });
    }

}
