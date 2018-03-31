package com.bob.bobmobileapp.tools.UI.views.recyclerviews.adapters;

import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;

/**
 * Created by User on 17/12/2017.
 */

public class MyRecyclerViewSingleChoiceAdapter extends MyRecyclerViewBaseAdapter<RadioButton> {

    private RadioButton defaultCheckedRadioButton, checkedRadioButton;

    public MyRecyclerViewSingleChoiceAdapter(ArrayList<RadioButton> radioButtons, int checkedIndex) {
        this.views = radioButtons;
        if (this.views.size() > 0) {
            if (this.views.size() > checkedIndex) {
                this.defaultCheckedRadioButton = this.views.get(checkedIndex);
            } else {
                this.defaultCheckedRadioButton = this.views.get(0);
            }
        } else {
            this.defaultCheckedRadioButton = null;
        }
        this.checkedRadioButton = this.defaultCheckedRadioButton;

        for (int i = 0; i < this.views.size(); i++) {
            this.setOnCheckedListener(this.views.get(i));
            if (i == checkedIndex){
                this.views.get(i).setChecked(true);
                this.checkedRadioButton = this.views.get(i);
            } else {
                this.views.get(i).setChecked(false);
            }
        }
    }

    public MyRecyclerViewSingleChoiceAdapter(ArrayList<RadioButton> radioButtonsList, RadioButton defaultCheckedRadioButton) {
        this(radioButtonsList, radioButtonsList.indexOf(defaultCheckedRadioButton));
    }

    public MyRecyclerViewSingleChoiceAdapter(ArrayList<RadioButton> radioButtons) {
        this(radioButtons, 0);
    }

    public MyRecyclerViewSingleChoiceAdapter() {
        this(new ArrayList<RadioButton>(), 0);
    }

    @Override
    public void addView(RadioButton viewToAdd) {
        this.addView(this.views.size(), viewToAdd);
    }

    @Override
    public void addView(int index, RadioButton viewToAdd) {
        if (!this.isViewExist(viewToAdd)) {
            viewToAdd.setChecked(false);
            this.setOnCheckedListener(viewToAdd);
            this.views.add(index, viewToAdd);
        }
        this.notifyDataSetChanged();
        this.updateCheckedView();
    }

    @Override
    public void addAllViews(ArrayList<RadioButton> viewsToAdd) {
        this.addAllViews(this.views.size(), viewsToAdd);
    }

    @Override
    public void addAllViews(int index, ArrayList<RadioButton> viewsToAdd) {
        ArrayList<RadioButton> views = new ArrayList<RadioButton>();
        for (RadioButton view : viewsToAdd) {
            if (!this.isViewExist(view)) {
                view.setChecked(false);
                this.setOnCheckedListener(view);
                views.add(view);
            }
        }
        this.views.addAll(index, viewsToAdd);
        this.notifyDataSetChanged();
        this.updateCheckedView();
    }

    @Override
    public void removeView(RadioButton viewToRemove) {
        super.removeView(viewToRemove);
        this.updateCheckedView();
    }

    @Override
    public void removeView(int indexToRemove) {
        this.removeView(this.views.get(indexToRemove));
    }

    private void updateCheckedView() {

        if (this.views.size() > 0) {
            if (!this.isViewExist(this.defaultCheckedRadioButton)) {
                this.defaultCheckedRadioButton = this.views.get(0);
            }
            for (RadioButton radioButton : this.views) {
                if (radioButton == this.defaultCheckedRadioButton) {
                    radioButton.setChecked(true);
                    this.checkedRadioButton = radioButton;
                } else {
                    radioButton.setChecked(false);
                }
            }
        }

    }

    public RadioButton getCheckedView() {
        return this.checkedRadioButton;
    }

    public int getCheckedViewIndex() {
        return this.views.indexOf(this.checkedRadioButton);
    }

    public RadioButton getDefaultCheckedView() {
        return this.defaultCheckedRadioButton;
    }

    public int getDefaultCheckedViewIndex() {
        return this.views.indexOf(this.defaultCheckedRadioButton);
    }

    public void setDefaultCheckedRadioButton(RadioButton radioButton) {
        if (this.isViewExist(radioButton)) {
            this.defaultCheckedRadioButton = radioButton;
        }
    }

    public void setDefaultCheckedRadioButtonIndex(int defaultCheckedRadioButtonIndex) {
        if (this.views.size() <= defaultCheckedRadioButtonIndex) {
            this.defaultCheckedRadioButton = this.views.get(defaultCheckedRadioButtonIndex);
        }
    }

    private void setOnCheckedListener(RadioButton radioButton) {
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton radioButtonView, boolean isChecked) {
                if (!isChecked) return;
                if (isViewExist((RadioButton) radioButtonView)) {
                    defaultCheckedRadioButton = (RadioButton) radioButtonView;
                    updateCheckedView();
                    //notifyDataSetChanged();
                }
            }
        });
    }

}
