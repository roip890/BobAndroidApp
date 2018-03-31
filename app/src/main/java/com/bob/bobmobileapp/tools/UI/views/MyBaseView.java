package com.bob.bobmobileapp.tools.UI.views;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.lang.reflect.Field;

/**
 * Created by User on 15/12/2017.
 */

public abstract class MyBaseView extends MyView {


    //title
    protected int titleIndex;
    protected TextView titleTextView;
    protected int titleColor, titleStartDrawableColor, titleEndDrawableColor;
    protected boolean titleIsBold, titleIsUnderline, titleIsItalic;
    protected Drawable titleStartDrawable, titleEndDrawable;
    protected MyView.DrawableOnClickListener titleStartDrawableOnClickListener, titleEndDrawableOnClickListener;
    protected boolean titleStartDrawableOnFocusOnly;
    protected boolean titleEndDrawableOnFocusOnly;


    //constructors
    public MyBaseView(Context context) {
        this(context, null);
    }

    public MyBaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //initialization
    @Override
    protected void initViewsOrder() {


        int i = this.getChildCount();
        this.titleIndex = 0;
        this.mainViewIndex = 0;
        this.bottomLineIndex = 1;
        this.errorIndex = 2;

    }

    protected void createTitleView() {

        this.titleTextView = new TextView(this.getContext());

    }

    protected void initTitleView() {

        //text
        this.setTitleText(null);

        //typeface (font)
        this.setTitleTextTypeface(Typeface.DEFAULT);

        //input type
        this.setTitleTextInputType(InputType.TYPE_CLASS_TEXT);

        //text color
        this.setTitleTextColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));

        //start drawable
        this.setStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setStartDrawable(null);
        this.setStartDrawableOnClickListener(null);
        this.setStartDrawableOnFocusOnly(false);
        this.setStartDrawableEnable(false);

        //end drawable
        this.setEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setEndDrawable(null);
        this.setEndDrawableOnClickListener(null);
        this.setEndDrawableOnFocusOnly(false);
        this.setEndDrawableEnable(false);

        //bold
        this.setTitleBoldEnable(false);

        //italic
        this.setTitleItalicEnable(false);

        //underline
        this.setTitleUnderlineEnable(false);

        //init on drawables click listeners
        this.setEndDrawableOnClickListener(null);
        this.setStartDrawableOnClickListener(null);

        //init drawables on focus change listeners
        this.setEndDrawableOnFocusOnly(false);
        this.setStartDrawableOnFocusOnly(false);
        this.setOnViewFocusChangedListener(null);

    }

    protected void addTitleView() {

        //add title view with default layout params
        this.addView(this.titleTextView, this.titleIndex, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));

    }

    @Override
    protected void initMainContainer() {

        try {
            //try to get the frame layout inside the TextInputLayout
            Field fInputFrame =TextInputLayout.class.getDeclaredField("mInputFrame");
            fInputFrame.setAccessible(true);
            this.mainContainer = (FrameLayout) fInputFrame.get(this);
        } catch (Exception e) {
            //if can't get the container take 'this' object as the container
            this.mainContainer = this;
        }

    }

    @Override
    protected abstract void createMainView();

    @Override
    protected abstract void initMainView();

    @Override
    protected void addMainView() {

        //init layout params
        FrameLayout.LayoutParams viewLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        //add main view
        this.mainContainer.addView(this.view, this.mainViewIndex, viewLayoutParams);

    }

    @Override
    protected void addBottomLine() {

        //init layout params
        FrameLayout.LayoutParams lineLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                this.bottomLineSize
        );

        //set bottom margin
        lineLayoutParams.setMargins(0, 0, 0, UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5));

        //gravity
        lineLayoutParams.gravity = Gravity.BOTTOM;

        //add bottom line
        this.mainContainer.addView(this.bottomLine, this.bottomLineIndex, lineLayoutParams);

    }

    @Override
    protected void createViews() {

        //create all views
        createTitleView();
        createMainView();
        createBottomLine();
        createErrorView();

    }

    @Override
    protected void initViews() {

        //init all views
        initDialog();
        initView();
        initMainContainer();
        initTitleView();
        initMainView();
        initBottomLine();
        initErrorView();

    }

    @Override
    protected void addViews() {

        //add all views
        addTitleView();
        addMainView();
        addBottomLine();
        addErrorView();

    }

        @Override
        protected void updateViewsMargins(int width) {

            //bottom line margin update
            FrameLayout.LayoutParams bottomLineLayoutParams = (FrameLayout.LayoutParams)this.bottomLine.getLayoutParams();
            if (bottomLineLayoutParams != null) {
                bottomLineLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
            }

            //error view margin update
            LinearLayout.LayoutParams errorViewLayoutParams = (LinearLayout.LayoutParams)this.errorTextView.getLayoutParams();
            if (errorViewLayoutParams != null) {
                errorViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
            }

            //main view margin update
            FrameLayout.LayoutParams mainViewLayoutParams = (FrameLayout.LayoutParams)this.view.getLayoutParams();
            if (mainViewLayoutParams != null) {
                mainViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
            }

    }

    protected void updateBottomLineWidth(int width) {

        if (this.titleTextView.getCompoundDrawablesRelative()[0] != null) {
            this.setWidth(width - this.startDrawable.getIntrinsicWidth() - this.endDrawable.getIntrinsicWidth(), this.bottomLine);
        } else {
            this.setWidth(width, this.bottomLine);
        }

//        //main view margin update
//        FrameLayout.LayoutParams mainViewLayoutParams = (FrameLayout.LayoutParams)this.view.getLayoutParams();
//        if (mainViewLayoutParams != null) {
//            mainViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
//        }
    }


    //title
    @Override
    public void setTitleText(String text) {
        this.titleTextView.setText(text);
    }

    @Override
    public String getTitleText() {
        return this.titleTextView.getText().toString();
    }

    public void setTitleTextSize(int size) {
        this.setTextSize(size, this.titleTextView);
    }

    public void setTitleTextInputType(int type) {
        this.setTextInputType(type, this.titleTextView);
    }

    public void setTitleTextColor(int color) {
        this.titleColor = color;
        this.paintTextColor(color, this.titleTextView);
    }

    public void setTitleTextTypeface(Typeface typeface) {
        this.setTextTypeface(typeface, this.titleTextView);
    }

    public void setTitleBoldEnable(boolean isBold) {
        this.titleIsBold = isBold;
        this.makeBoldEnable(this.titleIsBold, this.titleIsItalic, this.titleTextView);
    }

    public void setTitleItalicEnable(boolean isItalic) {
        this.titleIsItalic = isItalic;
        this.makeItalicEnable(this.titleIsItalic, this.titleIsBold, this.titleTextView);
    }

    public void setTitleUnderlineEnable(boolean isUnderline) {
        this.titleIsUnderline = isUnderline;
        this.makeUnderlineEnable(isUnderline, this.titleTextView);
    }

    @Override
    public void setStartDrawable(int startDrawable) {
        this.setStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable));
    }

    @Override
    public void setStartDrawable(Drawable startDrawable) {
        this.titleStartDrawable = startDrawable;
        this.paintStartDrawable(this.titleStartDrawableColor,
                this.titleStartDrawable);
        this.updateDrawables(this.titleStartDrawable, this.titleTextView.getCompoundDrawablesRelative()[2], this.titleTextView);
    }

    @Override
    public void setStartDrawableColor(int color) {
        this.titleStartDrawableColor = color;
        this.paintStartDrawable(this.titleStartDrawableColor,
                this.titleStartDrawable);
        if (this.titleTextView.getCompoundDrawablesRelative()[0] != null) {
            this.updateDrawables(this.titleStartDrawable, this.titleTextView.getCompoundDrawablesRelative()[2], this.titleTextView);
        }
    }

    @Override
    public void setStartDrawableEnable(boolean drawableEnable) {
        this.makeStartDrawableEnable(drawableEnable, this.titleTextView);
    }

    @Override
    public void setEndDrawable(int endDrawable) {
        this.setEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable));
    }

    @Override
    public void setEndDrawable(Drawable endDrawable) {
        this.titleEndDrawable = endDrawable;
        this.paintEndDrawable(this.titleEndDrawableColor,
                this.titleEndDrawable);
        this.updateDrawables(this.titleTextView.getCompoundDrawablesRelative()[0], this.titleEndDrawable, this.titleTextView);
    }

    @Override
    public void setEndDrawableColor(int color) {
        this.titleEndDrawableColor = color;
        this.paintEndDrawable(this.titleEndDrawableColor,
                this.titleEndDrawable);
        if (this.titleTextView.getCompoundDrawablesRelative()[2] != null) {
            this.updateDrawables(this.titleTextView.getCompoundDrawablesRelative()[0], this.titleEndDrawable, this.titleTextView);
        }
    }

    @Override
    public void setEndDrawableEnable(boolean drawableEnable) {
        this.makeEndDrawableEnable(drawableEnable, this.titleTextView);
    }

    @Override
    public void setStartDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.titleStartDrawableOnClickListener = listener;
        this.setDrawablesOnClickListener(this.titleStartDrawableOnClickListener, this.titleEndDrawableOnClickListener, this.titleTextView);
    }

    @Override
    public void setEndDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.titleEndDrawableOnClickListener = listener;
        this.setDrawablesOnClickListener(this.titleStartDrawableOnClickListener, this.titleEndDrawableOnClickListener, this.titleTextView);
    }

    @Override
    public void setStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly) {
        this.titleStartDrawableOnFocusOnly = startDrawableOnFocusOnly;
        this.setDrawablesOnFocusChangeListener(this.onFocusChangedListener,
                this.titleStartDrawableOnFocusOnly,
                this.titleEndDrawableOnFocusOnly,
                this.titleTextView);
    }

    @Override
    public void setEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly) {
        this.titleEndDrawableOnFocusOnly = endDrawableOnFocusOnly;
        this.setDrawablesOnFocusChangeListener(this.onFocusChangedListener,
                this.titleStartDrawableOnFocusOnly,
                this.titleEndDrawableOnFocusOnly,
                this.titleTextView);

    }

}
