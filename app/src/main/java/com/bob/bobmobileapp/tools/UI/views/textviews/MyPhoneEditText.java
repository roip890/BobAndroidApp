package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.util.Xml;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.RegexUtils;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.http.beans.WishPair;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.hbb20.CountryCodePicker;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by user on 04/10/2017.
 */

public class MyPhoneEditText extends MyEditText {

    protected CountryCodePicker countryCodePicker;
//    protected LinearLayout phoneViewLayout;

    public MyPhoneEditText(Context context) {
        this(context, null);
    }

    public MyPhoneEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createMainView() {

        XmlPullParser countryCodePickerParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            countryCodePickerParser.next();
            countryCodePickerParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet countryCodePickerAttrs = Xml.asAttributeSet(countryCodePickerParser);
        this.countryCodePicker = new CountryCodePicker(this.getContext(), countryCodePickerAttrs);

        this.textView = new TextInputEditText(this.getContext());
    }

    @Override
    protected void addMainView() {
        //init layout params
        LinearLayout.LayoutParams phoneEditTextLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //add main view
        this.addView(this.view, phoneEditTextLayoutParams);
        this.textView.setPaddingRelative(UIUtilsManager.get().convertPixelsToDp(this.getContext(), 135),0,
                UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5),0);

        FrameLayout.LayoutParams countryCodePickerParams = new FrameLayout.LayoutParams(
                UIUtilsManager.get().convertPixelsToDp(this.getContext(), 135),
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.mainContainer.addView(this.countryCodePicker, countryCodePickerParams);
    }

    @Override
    protected void initMainView() {
        super.initMainView();
    }

    @Override
    protected int getStartDrawableStartMargin() {
        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            return this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
        } else {
            return UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
        }
    }

    @Override
    public WishPair getValue(){
        WishPair wishPair = null;
        if ((this.getTitleText() != null) && (this.getText() != null)) {
            wishPair = new WishPair();
            wishPair.setKey(this.getTitleText());
            wishPair.setValue(this.countryCodePicker.getSelectedCountryCodeWithPlus() + "-" + this.getText().toString());
        }
        return wishPair;
    }

    @Override
    public void setValue(String value){
        if (RegexUtils.isMatch(
                "(\\+\\d+|0)\\-(\\d{9})$", value)) {
            try {
                String[] valueParts = value.split("-");
                if (valueParts.length == 2) {
                    int countryCode = Integer.parseInt(valueParts[0].substring(1));
                    String phoneNum = valueParts[1];
                    this.countryCodePicker.setCountryForPhoneCode(countryCode );
                    this.textView.setText(phoneNum);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        this.countryCodePicker.setContentColor(color);
    }

    @Override
    protected void onErrorEnable() {
        super.onErrorEnable();
        this.countryCodePicker.setContentColor(this.errorColor);
    }

    @Override
    protected void onErrorDisable() {
        super.onErrorDisable();
        this.countryCodePicker.setContentColor(this.textColor);
    }

    public CountryCodePicker getCountryCodePicker() {
        return this.countryCodePicker;
    }

    public void setCountryCodeByName(String countryCode) {
        this.countryCodePicker.setCountryForNameCode(countryCode);
    }

    public void setCountryCodeByCode(int countryCode) {
        this.countryCodePicker.setCountryForPhoneCode(countryCode);
    }

    public String getCountryCode() {
        return this.countryCodePicker.getSelectedCountryCode();
    }

    public void setCountryCodePickerDialogTextColor(int color) {
        this.countryCodePicker.setDialogTextColor(color);
    }

    public void setCountryCodePickerDialogBackgroundColor(int color) {
        this.countryCodePicker.setDialogBackgroundColor(color);
    }


}
