package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.View;
import android.widget.Button;

import com.bob.bobmobileapp.R;

import java.util.HashMap;

/**
 * Created by User on 22/11/2017.
 */

public class MyButton extends MyTextView {

    private String buttonType;
    protected int buttonColor, buttonBorderColor;
    protected int buttonBorderSize;
    protected int buttonBorderRadius;
    protected boolean isBorderEnable;
    protected HashMap<Integer, Integer> stateColors, stateBorderColors, stateTextColors;


    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.buttonColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
        this.setBottomLineEnable(false);
    }

    @Override
    protected void createMainView() {
        this.textView = new Button(this.getContext());
    }

    @Override
    protected void initMainView() {
        this.stateColors = new HashMap<Integer, Integer>();
        this.stateBorderColors = new HashMap<Integer, Integer>();
        this.stateTextColors = new HashMap<Integer, Integer>();
        this.buttonColor = ContextCompat.getColor(this.getContext(), R.color.colorPrimary);
        this.buttonBorderColor = ContextCompat.getColor(this.getContext(), R.color.colorPrimary);
        this.buttonBorderSize = 5;
        this.buttonBorderRadius = 0;
        this.buttonType = "none";
        super.initMainView();
        this.setButtonBorderEnable(false);
    }

    @Override
    protected void initDefaultMainViewBackGround() {
    }

    public void setButtonColor(int color) {
        this.buttonColor = color;
        this.paintButtonColor();
    }


    public void setButtonBorderEnable(boolean isBorderEnable) {
        this.isBorderEnable = isBorderEnable;
        paintButtonColor();
    }

    public void addButtonColorState(int state, int color) {
        if (this.stateColors == null) {
            this.stateColors = new HashMap<Integer, Integer>();
        }
        this.stateColors.put(state, color);
        this.paintButtonColor();
    }

    public void removeButtonColorState(int state) {
        if ((this.stateColors != null) && (this.stateColors.containsKey(state))){
            this.stateColors.remove(state);
        }
        this.paintButtonColor();
    }

    public void setButtonBorderColor(int color) {
        this.buttonBorderColor = color;
        this.paintButtonColor();
    }

    public void addButtonBorderColorState(int state, int color) {
        if (this.stateBorderColors == null) {
            this.stateBorderColors = new HashMap<Integer, Integer>();
        }
        this.stateBorderColors.put(state, color);
        this.paintButtonColor();
    }

    public void removeButtonBorderColorState(int state) {
        if ((this.stateBorderColors != null) && (this.stateBorderColors.containsKey(state))){
            this.stateBorderColors.remove(state);
        }
        this.paintButtonColor();
    }

    public void setButtonBorderSize(int buttonBorderSize) {
        this.buttonBorderSize = buttonBorderSize;
        this.paintButtonColor();
    }

    public void setButtonBorderRadius(int buttonBorderRadius) {
        this.buttonBorderRadius = buttonBorderRadius;
        this.paintButtonColor();
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        this.paintButtonColor();
    }

    public void setButtonTextColor(int color) {
        this.textColor = color;
        this.paintButtonColor();
    }

    public void addButtonTextColorState(int state, int color) {
        if (this.stateTextColors == null) {
            this.stateTextColors = new HashMap<Integer, Integer>();
        }
        this.stateTextColors.put(state, color);
        this.paintButtonColor();
    }

    public void removeButtonTextColorState(int state) {
        if ((this.stateTextColors != null) && (this.stateTextColors.containsKey(state))){
            this.stateTextColors.remove(state);
        }
        this.paintButtonColor();
    }

    public void paintButtonColor() {
        if (this.isBorderEnable) {
            ViewCompat.setBackgroundTintList(this.textView, null);
            StateListDrawable stateListDrawable = new StateListDrawable();
            for (Integer state : this.stateBorderColors.keySet()) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                if (this.stateColors.containsKey(state)) {
                    gradientDrawable.setColor(this.stateColors.get(state));
                } else {
                    gradientDrawable.setColor(this.buttonColor);
                }
                gradientDrawable.setCornerRadius(this.buttonBorderRadius);
                gradientDrawable.setStroke(this.buttonBorderSize, this.stateBorderColors.get(state));
                stateListDrawable.addState(this.stateToArray(state), gradientDrawable);
            }
            //default drawable
            GradientDrawable defaultGradientDrawable = new GradientDrawable();
            defaultGradientDrawable.setColor(this.buttonColor);
            defaultGradientDrawable.setCornerRadius(this.buttonBorderRadius);
            defaultGradientDrawable.setStroke(this.buttonBorderSize, this.buttonBorderColor);
            stateListDrawable.addState(StateSet.WILD_CARD, defaultGradientDrawable);
            ViewCompat.setBackground(this.textView, stateListDrawable);
        } else {
            int[][] states = new int[this.stateColors.keySet().size() + 1][];
            int[] colors = new int[this.stateColors.keySet().size() + 1];
            int index = 0;
            for (Integer state : this.stateColors.keySet()) {
                states[index] = this.stateToArray(state);
                colors[index] = this.stateColors.get(state);
                index++;
            }
            states[this.stateColors.keySet().size()] = new int[] {};
            colors[this.stateColors.keySet().size()] = this.buttonColor;
            ViewCompat.setBackgroundTintList(this.textView, new ColorStateList(states, colors));
        }
        int[][] states = new int[this.stateTextColors.keySet().size() + 1][];
        int[] colors = new int[this.stateTextColors.keySet().size() + 1];
        int index = 0;
        for (Integer state : this.stateTextColors.keySet()) {
            states[index] = this.stateToArray(state);
            colors[index] = this.stateTextColors.get(state);
            index++;
        }
        states[this.stateTextColors.keySet().size()] = new int[] {};
        colors[this.stateTextColors.keySet().size()] = this.textColor;
        this.textView.setTextColor(new ColorStateList(states, colors));
    }

    protected int[] stateToArray(int state) {
        int[] array = new int[1];
        array[0] = state;
        return array;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
}
