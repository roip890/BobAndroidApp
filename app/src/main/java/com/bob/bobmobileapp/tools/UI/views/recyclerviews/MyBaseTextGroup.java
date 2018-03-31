package com.bob.bobmobileapp.tools.UI.views.recyclerviews;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.validators.GroupValidator;

import java.util.ArrayList;

/**
 * Created by User on 24/12/2017.
 */

public abstract class MyBaseTextGroup<ViewType extends TextView> extends MyRecyclerView<ViewType> {

    //text views
    protected ArrayList<ViewType> textViews;
    protected ArrayList<Integer> textViewsTextColors, textViewsStartDrawableColors, textViewsEndDrawableColors;
    protected ArrayList<Drawable> textViewsStartDrawables, textViewsEndDrawables;
    protected ArrayList<Boolean> textViewsIsBold, textViewsIsItalic, textViewsIsUnderline;
    protected ArrayList<DrawableOnClickListener> textViewsStartDrawableOnClickListener, textViewsEndDrawableOnClickListener;
    protected ArrayList<Boolean> textViewsStartDrawableOnFocusOnly, textViewsEndDrawableOnFocusOnly;
    protected ArrayList<Boolean> textViewsFocusIsFirst;

    //validator
    private GroupValidator<ViewType> validator;


    public MyBaseTextGroup(Context context) {
        this(context, null);
    }

    public MyBaseTextGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBaseTextGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initTextViews();
    }


    //initialization
    protected abstract ViewType createViewType();

    protected void initTextViews() {
        this.textViews = this.adapter.getViews();
        this.textViewsTextColors = new ArrayList<Integer>();
        this.textViewsStartDrawableColors = new ArrayList<Integer>();
        this.textViewsEndDrawableColors = new ArrayList<Integer>();
        this.textViewsStartDrawables = new ArrayList<Drawable>();
        this.textViewsEndDrawables = new ArrayList<Drawable>();
        this.textViewsIsBold = new ArrayList<Boolean>();
        this.textViewsIsItalic = new ArrayList<Boolean>();
        this.textViewsIsUnderline = new ArrayList<Boolean>();
        this.textViewsStartDrawableOnClickListener = new ArrayList<DrawableOnClickListener>();
        this.textViewsEndDrawableOnClickListener = new ArrayList<DrawableOnClickListener>();
        this.textViewsStartDrawableOnFocusOnly = new ArrayList<Boolean>();
        this.textViewsEndDrawableOnFocusOnly = new ArrayList<Boolean>();
        this.textViewsFocusIsFirst = new ArrayList<Boolean>();
    }

    protected void initTextView(final int textViewIndex) {


        //create
        if (this.textViews.size() <= textViewIndex) {
            this.addOrSetIfExist(this.textViews, textViewIndex,this.createViewType());
        }

        final ViewType textView = this.textViews.get(textViewIndex);


        //init
        this.addOrSetIfExist(this.textViewsTextColors, textViewIndex, textView.getCurrentTextColor());
        this.addOrSetIfExist(this.textViewsStartDrawableColors, textViewIndex, textView.getCurrentTextColor());
        this.addOrSetIfExist(this.textViewsEndDrawableColors, textViewIndex, textView.getCurrentTextColor());
        this.addOrSetIfExist(this.textViewsStartDrawables, textViewIndex, textView.getCompoundDrawablesRelative()[0]);
        this.addOrSetIfExist(this.textViewsEndDrawables, textViewIndex, textView.getCompoundDrawablesRelative()[2]);
        this.addOrSetIfExist(this.textViewsIsBold, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsIsItalic, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsIsUnderline, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsStartDrawableOnClickListener, textViewIndex, null);
        this.addOrSetIfExist(this.textViewsEndDrawableOnClickListener, textViewIndex, null);
        this.addOrSetIfExist(this.textViewsStartDrawableOnFocusOnly, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsEndDrawableOnFocusOnly, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsFocusIsFirst, textViewIndex, false);


        //configure
        this.setTextViewTextColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), textViewIndex);
        this.setTextViewStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), textViewIndex);
        this.setTextViewStartDrawable(null, textViewIndex);
        this.setTextViewStartDrawableOnClickListener(null, textViewIndex);
        this.setTextViewStartDrawableOnFocusOnly(false, textViewIndex);
        this.setTextViewEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), textViewIndex);
        this.setTextViewEndDrawable(null, textViewIndex);
        this.setTextViewEndDrawableOnClickListener(null, textViewIndex);
        this.setTextViewEndDrawableOnFocusOnly(false, textViewIndex);
        this.setTextViewBoldEnable(false, textViewIndex);
        this.setTextViewItalicEnable(false, textViewIndex);
        this.setTextViewUnderlineEnable(false, textViewIndex);

        textView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (isOnEndDrawable(textView, motionEvent.getX()) && textView.getCompoundDrawablesRelative()[2] != null) {
                        if (textViewsEndDrawableOnClickListener.get(textViewIndex) != null) {
                            textViewsEndDrawableOnClickListener.get(textViewIndex).onDrawableClick();
                        }
                    } else if (isOnStartDrawable(textView, motionEvent.getX())&& textView.getCompoundDrawablesRelative()[0] != null) {
                        if (textViewsStartDrawableOnClickListener.get(textViewIndex) != null) {
                            textViewsStartDrawableOnClickListener.get(textViewIndex).onDrawableClick();
                        }
                    }
                }
                return false;
            }
        });

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                validate();
            }

            @Override
            public void afterTextChanged(Editable text) {

            }
        });
        textView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (textViewsFocusIsFirst.get(textViewIndex)) {
                    addOrSetIfExist(textViewsFocusIsFirst, textViewIndex, false);
                } else {
                    validate();
                    if (!hasFocus) {
                        Drawable startDrawable = textView.getCompoundDrawablesRelative()[0];
                        Drawable endDrawable = textView.getCompoundDrawablesRelative()[2];
                        if (textViewsStartDrawableOnFocusOnly.get(textViewIndex)) {
                            startDrawable = null;
                        }
                        if (textViewsEndDrawableOnFocusOnly.get(textViewIndex)) {
                            endDrawable = null;
                        }
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, endDrawable, null);
                    }
                }
            }
        });

        textView.setCompoundDrawablePadding(UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5));
        textView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.transparent));
    }

    public void add(View viewToAdd) {
        try {
            ViewType view = (ViewType)viewToAdd;
            this.textViews.add(view);
            this.initTextView(this.textViews.indexOf(view));
            this.adapter.addView(view);
        } catch (Exception e) {
            this.recyclerView.addView(viewToAdd);
        }

    }

    public void add(int index, View viewToAdd) {

        try {
            ViewType view = (ViewType)viewToAdd;
            this.textViews.add(index, view);
            this.initTextView(this.textViews.indexOf(view));
            this.adapter.addView(index, view);
        } catch (Exception e) {
            this.recyclerView.addView(viewToAdd, index);
        }

    }

    public void addAll(ArrayList<ViewType> views) {
        this.textViews.addAll(views);
        for (ViewType view : views) {
            this.initTextView(this.textViews.indexOf(view));
        }
        this.adapter.addAllViews(views);
    }

    public void addAll(int index, ArrayList<ViewType> views) {
        this.textViews.addAll(index, views);
        for (ViewType view : views) {
            this.initTextView(this.textViews.indexOf(view));
        }
        this.adapter.addAllViews(index, views);
    }

    //text views
    public void setTextViewText(String text, int textViewIndex) {
        this.adapter.getView(textViewIndex).setText(text);
    }

    public CharSequence getTextViewText(int textViewIndex) {
        return this.getText(this.adapter.getView(textViewIndex));
    }

    public void setTextViewTextSize(int size, int textViewIndex) {
        this.setTextSize(size, this.adapter.getView(textViewIndex));
    }

    public void setTextViewTextInputType(int type, int textViewIndex) {
        this.setTextInputType(type, this.adapter.getView(textViewIndex));
    }

    public void setTextViewTextColor(int color, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsTextColors, textViewIndex, color);
        this.paintTextColor(color, this.adapter.getView(textViewIndex));
    }

    public void setTextViewStartDrawable(int startDrawable, int textViewIndex) {
        this.setTextViewStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable), textViewIndex);
    }

    public void setTextViewStartDrawable(Drawable startDrawable, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawables, textViewIndex, startDrawable);
        this.paintStartDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                this.textViewsStartDrawables.get(textViewIndex));
        this.updateDrawables(this.textViewsStartDrawables.get(textViewIndex), this.adapter.getView(textViewIndex).getCompoundDrawablesRelative()[2], this.adapter.getView(textViewIndex));
    }

    public void setTextViewStartDrawableColor(int color, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawableColors, textViewIndex, color);
        this.paintStartDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                this.textViewsStartDrawables.get(textViewIndex));
        if (this.adapter.getView(textViewIndex).getCompoundDrawablesRelative()[0] != null) {
            this.updateDrawables(this.textViewsStartDrawables.get(textViewIndex), this.adapter.getView(textViewIndex).getCompoundDrawablesRelative()[2], this.adapter.getView(textViewIndex));
        }
    }

    public void setTextViewStartDrawableEnable(boolean drawableEnable, int textViewIndex) {
        this.makeStartDrawableEnable(drawableEnable, this.adapter.getView(textViewIndex));
    }

    public void setTextViewEndDrawable(int endDrawable, int textViewIndex) {
        this.setTextViewEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable), textViewIndex);
    }

    public void setTextViewEndDrawable(Drawable endDrawable, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawables, textViewIndex, endDrawable);
        this.paintEndDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                this.textViewsEndDrawables.get(textViewIndex));
        this.updateDrawables(this.adapter.getView(textViewIndex).getCompoundDrawablesRelative()[0], this.textViewsEndDrawables.get(textViewIndex), this.adapter.getView(textViewIndex));
    }

    public void setTextViewEndDrawableColor(int color, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawableColors, textViewIndex, color);
        this.paintEndDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                this.textViewsEndDrawables.get(textViewIndex));
        if (this.adapter.getView(textViewIndex).getCompoundDrawablesRelative()[2] != null) {
            this.updateDrawables(this.adapter.getView(textViewIndex).getCompoundDrawablesRelative()[0], this.textViewsEndDrawables.get(textViewIndex), this.adapter.getView(textViewIndex));
        }
    }

    public void setTextViewEndDrawableEnable(boolean drawableEnable, int textViewIndex) {
        this.makeEndDrawableEnable(drawableEnable, this.adapter.getView(textViewIndex));
    }

    public void setTextViewStartDrawableOnClickListener(MyView.DrawableOnClickListener listener, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawableOnClickListener, textViewIndex, listener);
    }

    public void setTextViewEndDrawableOnClickListener(MyView.DrawableOnClickListener listener, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawableOnClickListener, textViewIndex, listener);
    }

    public void setTextViewStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawableOnFocusOnly, textViewIndex, startDrawableOnFocusOnly);
    }

    public void setTextViewEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawableOnFocusOnly, textViewIndex, startDrawableOnFocusOnly);
    }

    public void setTextViewTextTypeface(Typeface typeface, int textViewIndex) {
        this.setTextTypeface(typeface, this.adapter.getView(textViewIndex));
    }

    public void setTextViewBoldEnable(boolean isBold, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsIsBold, textViewIndex, isBold);
        this.makeBoldEnable(this.textViewsIsBold.get(textViewIndex), this.textViewsIsItalic.get(textViewIndex), this.adapter.getView(textViewIndex));
    }

    public void setTextViewItalicEnable(boolean isItalic, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsIsItalic, textViewIndex, isItalic);
        this.makeItalicEnable(this.textViewsIsItalic.get(textViewIndex), this.textViewsIsBold.get(textViewIndex), this.adapter.getView(textViewIndex));
    }

    public void setTextViewUnderlineEnable(boolean isUnderline, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsIsUnderline, textViewIndex, isUnderline);
        this.makeUnderlineEnable(isUnderline, this.adapter.getView(textViewIndex));
    }


    //validator
    public void setValidator(GroupValidator validator) {
        this.validator = validator;
    }

    //validation
    public void validate() {
        if (validator != null) {
            String errorMessage = validator.validate(textViews);
            if (errorMessage != null) {
                this.setError(errorMessage);
            } else {
                this.setError(null);
            }
        }
    }


    //add if index not exist to array
    protected void addOrSetIfExist(ArrayList array, int index, Object object) {
        if (array.size() <= index) {
            array.add(object);
        } else {
            array.set(index, object);
        }
    }

}
