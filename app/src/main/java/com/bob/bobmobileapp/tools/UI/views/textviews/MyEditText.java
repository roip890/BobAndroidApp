package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.lang.reflect.Field;

/**
 * Created by user on 04/10/2017.
 */

public class MyEditText extends MyTextView {
    protected int cursorColor;

    public MyEditText(Context context) {
        this(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initCursor();
        this.setBottomLineEnable(true);
        MyEditText.this.makeEndDrawableEnable(false, MyEditText.this.textView);
        this.updateViewsMargins(this.textView.getWidth());
    }

    @Override
    protected void createMainView() {
        this.textView = new TextInputEditText(this.getContext()) {

            @Override
            protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                int blwt = MyEditText.this.textView.getWidth();
                int blw = MyEditText.this.bottomLine.getWidth();
                MyEditText.this.updateViewsMargins(w);
                int blwt2 = MyEditText.this.textView.getWidth();
                int blw2 = MyEditText.this.bottomLine.getWidth();
                int blbl = blw * blw2;
            }
        };
    }

    @Override
    protected void initMainView() {
        super.initMainView();
        this.setEndDrawable(new IconicsDrawable(this.getContext()).icon(MaterialDesignIconic.Icon.gmi_close_circle).sizeDp(20));
        this.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                MyEditText.this.makeEndDrawableEnable(false, MyEditText.this.textView);
//                if (!MyEditText.this.errorTextView.getText().toString().equals("")) {
//                    MyEditText.this.errorTextView.setVisibility(VISIBLE);
//                }
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });
        this.setEndDrawableOnClickListener(new MyTextView.DrawableOnClickListener() {
            @Override
            public void onDrawableClick() {
                if (!MyEditText.this.getText().toString().equals("")) {
                    MyEditText.this.setText("");
                    MyEditText.this.makeEndDrawableEnable(false, MyEditText.this.textView);
                }
            }
        });
        this.setOnViewFocusChangedListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ((!MyEditText.this.getText().toString().equals("")) && MyEditText.this.isEndDrawableEnable()) {
                    MyEditText.this.makeEndDrawableEnable(true, MyEditText.this.textView);
                } else {
                    MyEditText.this.makeEndDrawableEnable(false, MyEditText.this.textView);
                }
            }
        });
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((!s.toString().equals("")) && MyEditText.this.isEndDrawableEnable()) {
                    MyEditText.this.makeEndDrawableEnable(true, MyEditText.this.textView);
                } else {
                    MyEditText.this.makeEndDrawableEnable(false, MyEditText.this.textView);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    protected void initCursor() {
        this.setCursorColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
    }

    public void setCursorColor(int color) {
        this.cursorColor = color;
        this.paintCursorColor(color);
    }

    protected void paintCursorColor(int color) {
        try {

            //get cursor fields by reflection
            Field fCursorDrawableRes =
                    TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(this.textView);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(this.textView);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            //change the field to this color
            Drawable[] drawables = new Drawable[2];
            Resources res = this.textView.getContext().getResources();
            drawables[0] = res.getDrawable(mCursorDrawableRes);
            drawables[1] = res.getDrawable(mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (final Throwable ignored) {
        }
    }

    @Override
    protected void onErrorEnable() {
        super.onErrorEnable();
        this.paintCursorColor(this.errorColor);
    }

    @Override
    protected void onErrorDisable() {
        super.onErrorDisable();
        this.paintCursorColor(this.cursorColor);
    }

}
