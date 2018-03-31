package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.http.beans.Wish;
import com.bob.bobmobileapp.http.beans.WishPair;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.lang.reflect.Field;

/**
 * Created by user on 04/10/2017.
 */

public class MyTextView extends MyView {

    protected Validator validator;
    protected TextView textView;
    protected int textColor, titleColor;
    protected boolean isBold, isItalic, isUnderline, isEndDrawableEnable, isStartDrawableEnable;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void createMainView() {
        this.textView = new android.support.v7.widget.AppCompatEditText(this.getContext());
        this.textView.setInputType(finals.inputTypes.get("none"));
//        ((EditText)this.textView).setEnabled(false);
    }

    @Override
    protected void initMainView() {


        //init
        this.view = this.textView;

        //text
        this.setText(null, this.textView);

        //typeface (font)
        this.setTextTypeface(Typeface.DEFAULT);

        //input type
//        this.setTextInputType(InputType.TYPE_CLASS_TEXT);

        //text color
        this.setTextColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));

        //start drawable
        this.setStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setStartDrawable(null);
        this.setStartDrawableOnClickListener(null);
        this.setStartDrawableOnFocusOnly(false);
        this.setStartDrawableEnable(true);

        //end drawable
        this.setEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setEndDrawable(null);
        this.setEndDrawableOnClickListener(null);
        this.setEndDrawableOnFocusOnly(false);
        this.setEndDrawableEnable(true);

        //bold
        this.setBoldEnable(false);

        //italic
        this.setItalicEnable(false);

        //underline
        this.setUnderlineEnable(false);

        //init on drawables click listeners
        this.setEndDrawableOnClickListener(null);
        this.setStartDrawableOnClickListener(null);

        //init drawables on focus change listeners
        this.setEndDrawableOnFocusOnly(false);
        this.setStartDrawableOnFocusOnly(false);
        this.setOnViewFocusChangedListener(null);

        this.textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable text) {
                validateTextField(text.toString());
            }
        });

        this.initDefaultMainViewBackGround();
        this.setBottomLineEnable(false);

        this.textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                MyTextView.this.updateViewsMargins(MyTextView.this.textView.getWidth());
//                updateViewsMargins();
            }
        });

        this.textView.setCompoundDrawablePadding(UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5));

    }

    @Override
    protected void addMainView() {

        //init layout params
        LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );



        //add main view
        this.addView(this.view, viewLayoutParams);

    }

    @Override
    protected void initMainContainer() {

        try {
//            try to get the frame layout inside the TextInputLayout
            Field fInputFrame =TextInputLayout.class.getDeclaredField("mInputFrame");
            fInputFrame.setAccessible(true);
            this.mainContainer = (FrameLayout) fInputFrame.get(this);
//            this.mainContainer = this;
        } catch (Exception e) {
            //if can't get the container take 'this' object as the container
            this.mainContainer = this;
        }

    }

    @Override
    protected void addBottomLine() {

        //init layout params
        FrameLayout.LayoutParams lineLayoutParams = new FrameLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                this.bottomLineSize
        );

        //set bottom margin
        lineLayoutParams.setMargins(0, 0, 0, UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5));

        //gravity
        lineLayoutParams.gravity = Gravity.BOTTOM;


        //add bottom line
        this.mainContainer.addView(this.bottomLine, lineLayoutParams);

    }

    @Override
    protected void updateViewsMargins(int width) {

        //bottom line margin update
        FrameLayout.LayoutParams bottomLineLayoutParams = (FrameLayout.LayoutParams)this.bottomLine.getLayoutParams();
        if (bottomLineLayoutParams != null) {
            bottomLineLayoutParams.setMarginStart((this.getStartDrawableStartMargin()));
        }

        //error view margin update
        LinearLayout.LayoutParams errorViewLayoutParams = (LinearLayout.LayoutParams)this.errorTextView.getLayoutParams();
        if (errorViewLayoutParams != null) {
            errorViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
        }

        if (width > 0) {
            this.updateBottomLineWidth(width);
//            this.setWidth(width, this);
        }

//        //main view margin update
//        FrameLayout.LayoutParams mainViewLayoutParams = (FrameLayout.LayoutParams)this.view.getLayoutParams();
//        if (mainViewLayoutParams != null) {
//            mainViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
//        }
    }

    @Override
    protected void updateBottomLineWidth(int width) {

        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            MyTextView.this.setBottomLineWidth(width - this.startDrawable.getIntrinsicWidth());
        } else {
            MyTextView.this.setBottomLineWidth(width);
        }

//        //main view margin update
//        FrameLayout.LayoutParams mainViewLayoutParams = (FrameLayout.LayoutParams)this.view.getLayoutParams();
//        if (mainViewLayoutParams != null) {
//            mainViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
//        }
    }

    protected void initDefaultMainViewBackGround() {
        this.textView.setBackground(null);
    }

    protected int getStartDrawableStartMargin() {
        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            return this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
        } else {
//            return UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
            return 0;
        }
    }

    @Override
    public WishPair getValue(){
        WishPair wishPair = null;
        if ((this.getTitleText() != null) && (this.getText() != null)) {
            wishPair = new WishPair();
            wishPair.setKey(this.getTitleText());
            wishPair.setValue(this.getText().toString());
        }
        return wishPair;
    }

    @Override
    public void setValue(String value){
        this.textView.setText(value);
    }


    //title view
    @Override
    public void setTitleText(String text) {
            this.setHint(text);
    }

    @Override
    public String getTitleText() {
        if (this.getHint() == null) {
            return null;
        } else {
            return this.getHint().toString();
        }
    }

    public void setTitleColor(int color) {
        this.titleColor = color;
        this.paintTitleColor(color);
    }

    protected void paintTitleColor(int color) {
        try {
            Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            fDefaultTextColor.setAccessible(true);
            fDefaultTextColor.set(this, new ColorStateList(new int[][]{{0}}, new int[]{ color }));

            Field fFocusedTextColor = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
            fFocusedTextColor.setAccessible(true);
            fFocusedTextColor.set(this, new ColorStateList(new int[][]{{0}}, new int[]{ color }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //main view (text view)
    public CharSequence getText() {
        return this.getText(this.textView);
    }

    public void setText(String text) {
        this.setText(text, this.textView);
    }

    public void setTextSize(int size) {
        this.setTextSize(size, this.textView);
    }

    public void setTextInputType(int type) {
        this.setTextInputType(type, this.textView);
    }

    public void setTextColor(int color) {
        this.textColor = color;
        this.paintTextColor(color, this.textView);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.view.setOnClickListener(onClickListener);
    }

    public void setStartDrawable(int startDrawable) {
        this.setStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable));
    }

    public void setStartDrawable(Drawable startDrawable) {
        this.startDrawable = startDrawable;
        this.paintStartDrawable(this.startDrawableColor,
                this.startDrawable);
        this.updateDrawables(this.startDrawable, this.textView.getCompoundDrawablesRelative()[2], this.textView);
    }

    public void setStartDrawableColor(int color) {
        this.startDrawableColor = color;
        this.paintStartDrawable(this.startDrawableColor,
                this.startDrawable);
        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            this.updateDrawables(this.startDrawable, this.textView.getCompoundDrawablesRelative()[2], this.textView);
        }
    }

    public void setStartDrawableEnable(boolean drawableEnable) {
        this.isStartDrawableEnable = drawableEnable;
        this.makeStartDrawableEnable(drawableEnable, this.textView);
    }

    public boolean isStartDrawableEnable() {
        return this.isStartDrawableEnable;
    }

    public void setEndDrawable(int endDrawable) {
        this.setEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable));
    }

    public void setEndDrawable(Drawable endDrawable) {
        this.endDrawable = endDrawable;
        this.paintEndDrawable(this.endDrawableColor,
                this.errorEndDrawable);
        this.updateDrawables(this.textView.getCompoundDrawablesRelative()[0], this.endDrawable, this.textView);
    }

    public void setEndDrawableColor(int color) {
        this.endDrawableColor = color;
        this.paintEndDrawable(this.endDrawableColor,
                this.endDrawable);
        if (this.textView.getCompoundDrawablesRelative()[2] != null) {
            this.updateDrawables(this.textView.getCompoundDrawablesRelative()[0], this.endDrawable, this.textView);
        }
    }

    public void setEndDrawableEnable(boolean drawableEnable) {
        this.isEndDrawableEnable = drawableEnable;
        this.makeEndDrawableEnable(drawableEnable, this.textView);
    }

    public boolean isEndDrawableEnable() {
        return this.isEndDrawableEnable;
    }

    public void setStartDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.startDrawableOnClickListener = listener;
        this.setDrawablesOnClickListener(this.startDrawableOnClickListener, this.endDrawableOnClickListener, this.textView);
    }

    public void setEndDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.endDrawableOnClickListener = listener;
        this.setDrawablesOnClickListener(this.startDrawableOnClickListener, this.endDrawableOnClickListener, this.textView);
    }

    public void setStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly) {
        this.startDrawableOnFocusOnly = startDrawableOnFocusOnly;
        this.setDrawablesOnFocusChangeListener(this.onFocusChangedListener,
                this.startDrawableOnFocusOnly,
                this.endDrawableOnFocusOnly,
                this.textView);
    }

    public void setEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly) {
        this.endDrawableOnFocusOnly = endDrawableOnFocusOnly;
        this.setDrawablesOnFocusChangeListener(this.onFocusChangedListener,
                this.startDrawableOnFocusOnly,
                this.endDrawableOnFocusOnly,
                this.textView);
    }

    public void setTextTypeface(Typeface typeface) {
        this.setTextTypeface(typeface, this.textView);
    }

    public void setBoldEnable(boolean isBold) {
        this.isBold = isBold;
        this.makeBoldEnable(this.isBold, this.isItalic, this.textView);
    }

    public void setItalicEnable(boolean isItalic) {
        this.isItalic = isItalic;
        this.makeItalicEnable(this.isItalic, this.isBold, this.textView);
    }

    public void setUnderlineEnable(boolean isUnderline) {
        this.isUnderline = isUnderline;
        this.makeUnderlineEnable(isUnderline, this.textView);
    }


    //validation
    public void validateTextField(String text) {
        if (this.validator != null) {
            String errorMessage = this.validator.validate(text);
            this.setError(errorMessage);
        }
    }


    //validator
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    //error text view
    @Override
    protected void createErrorView() {
        try {
            this.setErrorEnabled(true);
            Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
            fErrorView.setAccessible(true);
            this.errorTextView = (TextView)fErrorView.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            this.errorTextView = new TextView(this.getContext());
            super.addErrorView();
        }
    }

    @Override
    protected void addErrorView() {}

    @Override
    protected void onErrorEnable() {
        this.setErrorEnabled(true);
        this.createErrorView();
        super.onErrorEnable();
        this.paintTitleColor(this.errorColor);
        this.paintTextColor(this.errorColor, this.textView);
        this.paintStartDrawable(this.errorColor, this.startDrawable);
        this.paintEndDrawable(this.errorColor, this.endDrawable);
        this.updateDrawables(this.startDrawable, this.endDrawable, this.textView);
        this.paintTextColor(this.errorColor, this.errorTextView);
        this.errorTextView.setVisibility(VISIBLE);
    }

    @Override
    protected void onErrorDisable() {
        this.setErrorEnabled(false);
        this.createErrorView();
        super.onErrorDisable();
        this.paintTitleColor(this.titleColor);
        this.paintTextColor(this.textColor, this.textView);
        this.paintStartDrawable(this.startDrawableColor, this.startDrawable);
        this.paintEndDrawable(this.endDrawableColor, this.endDrawable);
        this.updateDrawables(this.startDrawable, this.endDrawable, this.textView);
        this.paintTextColor(this.errorColor, this.errorTextView);
        this.errorTextView.setVisibility(GONE);
    }


    //text view getter
    public TextView getTextView() {
        return this.textView;
    }


    //drawable on focus boolean
    @Override
    protected void onFocusOnlyHandler(final boolean startDrawableOnFocusOnly,
                                      final boolean endDrawableOnFocusOnly,
                                      final TextView textView, boolean hasFocus) {
        validateTextField(((TextView) view).getText().toString());
        super.onFocusOnlyHandler(startDrawableOnFocusOnly,
                endDrawableOnFocusOnly,
                textView, hasFocus);
    }



    public void addTextChangedListener(TextWatcher textWatcher) {
        this.textView.addTextChangedListener(textWatcher);
    }

    //((ViewGroup.MarginLayoutParams)this.errorTextView.getLayoutParams()).setMarginStart(this.getStartDrawableStartMargin());

}
