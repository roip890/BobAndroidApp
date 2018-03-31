package com.bob.bobmobileapp.tools.UI.views.menu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.views.MyBaseView;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.image.svg.SvgSoftwareLayerSetter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.caverock.androidsvg.SVG;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.IconicsDrawable;

import org.xmlpull.v1.XmlPullParser;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by User on 14/01/2018.
 */

public class MyMenuNodeView extends MyBaseView{

    protected LinearLayout menuNodeViewLayout;

    //menu image
    protected ImageView menuNodeImageView;
    protected String imageUri;

    //menu label
    protected TextView menuNodeLabelView;
    protected int labelColor, labelStartDrawableColor, labelEndDrawableColor;
    protected boolean labelIsBold, labelIsUnderline, labelIsItalic;
    protected Drawable labelStartDrawable, labelEndDrawable;
    protected MyView.DrawableOnClickListener labelStartDrawableOnClickListener, labelEndDrawableOnClickListener;
    protected boolean labelStartDrawableOnFocusOnly;
    protected boolean labelEndDrawableOnFocusOnly;


    public MyMenuNodeView(Context context) {
        this(context, null);
    }

    public MyMenuNodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyMenuNodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.imageUri = null;
    }

    @Override
    protected void createMainView() {
        XmlPullParser menuNodeViewParser = getResources().getXml(R.xml.linear_layout_vertical);
        try {
            menuNodeViewParser.next();
            menuNodeViewParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet menuNodeViewAttrs = Xml.asAttributeSet(menuNodeViewParser);
        this.view = new LinearLayout(this.getContext(), menuNodeViewAttrs);
        this.menuNodeViewLayout = (LinearLayout) this.view;

        XmlPullParser menuNodeImageParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            menuNodeImageParser.next();
            menuNodeImageParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet menuNodeImageAttrs = Xml.asAttributeSet(menuNodeImageParser);
        this.menuNodeImageView = new ImageView(this.getContext(),menuNodeImageAttrs);

        XmlPullParser textViewParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            textViewParser.next();
            textViewParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet menuNodelabelAttrs = Xml.asAttributeSet(textViewParser);
        this.menuNodeLabelView = new TextView(this.getContext(), menuNodelabelAttrs);
    }

    protected void addMainView() {

        FrameLayout.LayoutParams countryCodePickerParams = new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.menuNodeViewLayout.addView(this.menuNodeImageView, countryCodePickerParams);

        this.menuNodeViewLayout.addView(this.menuNodeLabelView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.mainContainer.addView(this.menuNodeViewLayout, params);

    }

    @Override
    protected void initMainView() {

    }

    protected void initMenuLabelView() {

        //text
        this.setLabelText(null);

        //typeface (font)
        this.setLabelTextTypeface(Typeface.DEFAULT);

        //input type
        this.setLabelTextInputType(InputType.TYPE_CLASS_TEXT);

        //text color
        this.setLabelTextColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));

        //start drawable
        this.setLabelStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setLabelStartDrawable(null);
        this.setLabelStartDrawableOnClickListener(null);
        this.setLabelStartDrawableOnFocusOnly(false);
        this.setLabelStartDrawableEnable(false);

        //end drawable
        this.setLabelEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setLabelEndDrawable(null);
        this.setLabelEndDrawableOnClickListener(null);
        this.setLabelEndDrawableOnFocusOnly(false);
        this.setLabelEndDrawableEnable(false);

        //bold
        this.setLabelBoldEnable(false);

        //italic
        this.setLabelItalicEnable(false);

        //underline
        this.setLabelUnderlineEnable(false);


        //init on drawables click listeners
        this.setLabelStartDrawableOnClickListener(null);
        this.setLabelEndDrawableOnClickListener(null);

        //init drawables on focus change listeners
        this.setLabelStartDrawableOnFocusOnly(false);
        this.setLabelEndDrawableOnFocusOnly(false);

    }

    //menu image
    public ImageView getMenuImageView() {
        return this.menuNodeImageView;
    }

    public void setImageUri(String uri) {
        this.imageUri = uri;
        this.menuNodeImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (this.imageUri != null) {
            Glide
                    .with(this.getContext())
                    .load(Uri.parse(uri))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .transition(withCrossFade())
                    .listener(new SvgSoftwareLayerSetter())
                    .into(this.menuNodeImageView);
        }
    }


    public void setImageHeight(int height) {
        this.menuNodeImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.setHeight(height, this.menuNodeImageView);
    }

    public void setImageWidth(int width) {
        this.menuNodeImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.setWidth(width, this.menuNodeImageView);
    }

    public void setImageGravity(int gravity) {
        this.setGravity(gravity, this.menuNodeImageView);
    }


    //menu label
    public void setLabelHeight(int height) {
        this.setHeight(height, this.menuNodeLabelView);
    }

    public void setLabelWidth(int width) {
        this.setWidth(width, this.menuNodeLabelView);
    }

    public void setLabelGravity(int gravity) {
        this.setGravity(gravity, this.menuNodeLabelView);
    }

    public void setLabeLTextAlignment(int textAlignment) {
        this.setTextAlignment(textAlignment, this.menuNodeLabelView);
    }

    public TextView getMenuTextView() {
        return this.menuNodeLabelView;
    }

    public void setLabelText(String text) {
        this.menuNodeLabelView.setText(text);
    }

    public String getLabelText() {
        return this.menuNodeLabelView.getText().toString();
    }

    public void setLabelTextSize(int size) {
        this.setTextSize(size, this.menuNodeLabelView);
    }

    public void setLabelTextInputType(int type) {
        this.setTextInputType(type, this.menuNodeLabelView);
    }

    public void setLabelTextColor(int color) {
        this.labelColor = color;
        this.paintTextColor(color, this.menuNodeLabelView);
    }

    public void setLabelTextTypeface(Typeface typeface) {
        this.setTextTypeface(typeface, this.menuNodeLabelView);
    }

    public void setLabelBoldEnable(boolean isBold) {
        this.labelIsBold = isBold;
        this.makeBoldEnable(this.labelIsBold, this.labelIsItalic, this.menuNodeLabelView);
    }

    public void setLabelItalicEnable(boolean isItalic) {
        this.labelIsItalic = isItalic;
        this.makeItalicEnable(this.labelIsItalic, this.labelIsBold, this.menuNodeLabelView);
    }

    public void setLabelUnderlineEnable(boolean isUnderline) {
        this.labelIsUnderline = isUnderline;
        this.makeUnderlineEnable(isUnderline, this.menuNodeLabelView);
    }

    public void setLabelStartDrawable(int startDrawable) {
        this.setStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable));
    }

    public void setLabelStartDrawable(Drawable startDrawable) {
        this.labelStartDrawable = startDrawable;
        this.paintStartDrawable(this.labelStartDrawableColor,
                this.labelStartDrawable);
        this.updateDrawables(this.labelStartDrawable, this.menuNodeLabelView.getCompoundDrawablesRelative()[2], this.menuNodeLabelView);
    }

    public void setLabelStartDrawableColor(int color) {
        this.labelStartDrawableColor = color;
        this.paintStartDrawable(this.labelStartDrawableColor,
                this.labelStartDrawable);
        if (this.menuNodeLabelView.getCompoundDrawablesRelative()[0] != null) {
            this.updateDrawables(this.labelStartDrawable, this.menuNodeLabelView.getCompoundDrawablesRelative()[2], this.menuNodeLabelView);
        }
    }

    public void setLabelStartDrawableEnable(boolean drawableEnable) {
        this.makeStartDrawableEnable(drawableEnable, this.menuNodeLabelView);
    }

    public void setLabelEndDrawable(int endDrawable) {
        this.setEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable));
    }

    public void setLabelEndDrawable(Drawable endDrawable) {
        this.labelEndDrawable = endDrawable;
        this.paintEndDrawable(this.labelEndDrawableColor,
                this.labelEndDrawable);
        this.updateDrawables(this.menuNodeLabelView.getCompoundDrawablesRelative()[0], this.labelEndDrawable, this.menuNodeLabelView);
    }

    public void setLabelEndDrawableColor(int color) {
        this.labelEndDrawableColor = color;
        this.paintEndDrawable(this.labelEndDrawableColor,
                this.labelEndDrawable);
        if (this.menuNodeLabelView.getCompoundDrawablesRelative()[2] != null) {
            this.updateDrawables(this.menuNodeLabelView.getCompoundDrawablesRelative()[0], this.labelEndDrawable, this.menuNodeLabelView);
        }
    }

    public void setLabelEndDrawableEnable(boolean drawableEnable) {
        this.makeEndDrawableEnable(drawableEnable, this.menuNodeLabelView);
    }

    public void setLabelStartDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.labelStartDrawableOnClickListener = listener;
        this.setDrawablesOnClickListener(this.labelStartDrawableOnClickListener, this.labelEndDrawableOnClickListener, this.menuNodeLabelView);
    }

    public void setLabelEndDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.labelEndDrawableOnClickListener = listener;
        this.setDrawablesOnClickListener(this.labelStartDrawableOnClickListener, this.labelEndDrawableOnClickListener, this.menuNodeLabelView);
    }

    public void setLabelStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly) {
        this.labelStartDrawableOnFocusOnly = startDrawableOnFocusOnly;
        this.setDrawablesOnFocusChangeListener(null, this.labelStartDrawableOnFocusOnly,
                this.labelEndDrawableOnFocusOnly,
                this.menuNodeLabelView);
    }

    public void setLabelEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly) {
        this.labelEndDrawableOnFocusOnly = endDrawableOnFocusOnly;
        this.setDrawablesOnFocusChangeListener(null, this.labelStartDrawableOnFocusOnly,
                this.labelEndDrawableOnFocusOnly,
                this.menuNodeLabelView);
    }

}
