package com.bob.bobmobileapp.menu.viewholders.formitem.media;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.afollestad.materialdialogs.Theme;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.BaseViewHolder;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.UI.views.mediaviews.MyImageView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.util.HashMap;


/**
 * Created by user on 01/09/2017.
 */

public class MyImageViewViewHolder extends BaseViewHolder {

    protected MyImageView imageView;

    public MyImageViewViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    public MyImageViewViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public MyImageView getMediaView() {
        return this.imageView;
    }

    public void setMediaView(MyImageView imageView) {
        super.setView(imageView);
        this.imageView = imageView;
    }

    @Override
    protected void initView(View view) {
        this.setMediaView((MyImageView) view.findViewById(R.id.my_image_view));
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.imageView.setImageUri(null);

    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("image_uri")) != null) {
            this.imageView.setImageUri(curProperty);
        }
        if ((curProperty = properties.get("image_width")) != null) {
            try {
                this.imageView.setImageHeight(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("image_height")) != null) {
            try {
                this.imageView.setImageHeight(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    }

}
