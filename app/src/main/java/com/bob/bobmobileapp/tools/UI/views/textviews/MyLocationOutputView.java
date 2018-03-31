package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.googleapi.maps.MapsFragmentDialog;
import com.bob.bobmobileapp.http.beans.WishPair;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.style.Icons;
import com.mikepenz.iconics.IconicsDrawable;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by User on 24/12/2017.
 */

public class MyLocationOutputView extends MyTextView {

    protected Drawable mapIconDrawable;
    protected int mapIconDrawableColor;
    protected ImageView mapIconImageView;
    protected LinearLayout locationOutputLayout;
    protected double latitude;
    protected double longitude;

    public MyLocationOutputView(Context context) {
        this(context, null);
    }

    public MyLocationOutputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLocationOutputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //default location Tel Aviv
        this.setLatitude(32.109333);
        this.setLongitude(34.855499);
    }

    @Override
    protected void createMainView() {
        XmlPullParser linearLayoutParser = getResources().getXml(R.xml.linear_layout_horizontal);
        try {
            linearLayoutParser.next();
            linearLayoutParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet linearLayoutAttrs = Xml.asAttributeSet(linearLayoutParser);
        this.locationOutputLayout = new LinearLayout(this.getContext(), linearLayoutAttrs);


        XmlPullParser textViewParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            textViewParser.next();
            textViewParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet textViewAttrs = Xml.asAttributeSet(textViewParser);
        this.textView = new TextView(this.getContext(), textViewAttrs);


        XmlPullParser locationOutputParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            locationOutputParser.next();
            locationOutputParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet locationOutputAttrs = Xml.asAttributeSet(locationOutputParser);
        this.mapIconImageView = new ImageView(this.getContext(),locationOutputAttrs);
        this.mapIconImageView.setImageDrawable(this.mapIconDrawable);

    }

    @Override
    protected void initMainView() {
        super.initMainView();

        this.setMapDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setMapDrawable(((IconicsDrawable)Icons.get().findDrawable(this.getContext(), "gmd_my_location")).sizeDp(24));
        this.setMapDrawableEnable(true);

        this.mapIconImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                MapsFragmentDialog mapsFragmentDialog = new MapsFragmentDialog();
                FragmentManager fm = ((FragmentActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                mapsFragmentDialog.show(ft, "Maps");


                //ft.add(getId(), autocompleteFragment , "fragment" + fragCount++);
                //ft.commit();


                //Intent intent = new Intent(getContext(), MapsActivity.class);
                //getContext().startActivity(intent);

            }
        });

//        this.setBackgroundColor(Color.RED);
//        this.mapIconImageView.setColorFilter(Color.GREEN);
//        this.textView.setBackgroundColor(Color.YELLOW);

    }

    @Override
    protected void addMainView() {

        FrameLayout.LayoutParams textViewParams = new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.locationOutputLayout.addView(this.textView, textViewParams);

        FrameLayout.LayoutParams mapIconParams = new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mapIconParams.setMarginStart(UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5));
        this.locationOutputLayout.addView(this.mapIconImageView, mapIconParams);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.mainContainer.addView(this.locationOutputLayout, params);

    }

    @Override
    public WishPair getValue() {
        WishPair wishPair = null;
        if ((this.getTitleText() != null) && (this.getText() != null)) {
            wishPair = new WishPair();
            wishPair.setKey(this.getTitleText());
            wishPair.setValue(Double.toString(this.latitude) + "," + Double.toString(this.longitude));
        }
        return wishPair;
    }

    @Override
    public void setValue(String value) {
        try {
            String[] valueParts = value.split(",");
            if (valueParts.length == 2) {
                double latitude = Double.parseDouble(valueParts[0]);
                double longitude = Double.parseDouble(valueParts[1]);
                this.latitude = latitude;
                this.longitude = longitude;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setMapDrawable(int mapDrawable) {
        this.setMapDrawable(ContextCompat.getDrawable(getContext(), mapDrawable));
    }

    public void setMapDrawable(Drawable mapIconDrawable) {
        this.mapIconDrawable = mapIconDrawable;
        this.paintMapDrawable(this.mapIconDrawableColor,
                this.mapIconDrawable);
    }

    public void setMapDrawableColor(int color) {
        this.mapIconDrawableColor = color;
        this.paintMapDrawable(this.mapIconDrawableColor,
                this.mapIconDrawable);
    }

    protected void paintMapDrawable(int color, Drawable mapIconDrawable) {
        if (mapIconDrawable != null) {
            mapIconDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        this.mapIconImageView.setImageDrawable(this.mapIconDrawable);
        updateViewsMargins(this.textView.getWidth());
    }


    public void setMapDrawableEnable(boolean drawableEnable) {
        this.makeMapDrawableEnable(drawableEnable, this.textView);
    }

    protected void makeMapDrawableEnable(boolean drawableEnable, TextView textView) {
        if (drawableEnable) {
            this.mapIconImageView.setImageDrawable(this.mapIconImageView.getDrawable());
        } else {
            this.mapIconImageView.setImageDrawable(null);
        }
    }

    @Override
    protected void updateViewsMargins(int width) {
        super.updateViewsMargins(width);

        //map icon drawable
        LinearLayout.LayoutParams mapIconLayoutParams = (LinearLayout.LayoutParams)this.mapIconImageView.getLayoutParams();
        if (mapIconLayoutParams != null) {
            mapIconLayoutParams.setMarginEnd(this.getBottomLineEndMargin());
        }
    }

    protected int getBottomLineEndMargin() {
        if (this.mapIconImageView.getDrawable() != null) {
            return this.mapIconImageView.getDrawable().getIntrinsicWidth() + UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
        } else {
            return UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
