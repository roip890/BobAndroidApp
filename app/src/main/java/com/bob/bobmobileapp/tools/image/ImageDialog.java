package com.bob.bobmobileapp.tools.image;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.bob.bobmobileapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by User on 17/01/2018.
 */

public class ImageDialog extends Dialog {

    protected String uri;
    protected PhotoView photoView;

    public ImageDialog(@NonNull Context context) {
        super(context);
        this.uri=null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });
        setCancelable(true);

        setContentView(R.layout.image_layout);
        this.photoView = (PhotoView) findViewById(R.id.image_view);

        if (this.uri != null) {
            Glide
                    .with(getContext())
                    .load(Uri.parse(uri))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .into(this.photoView);

        }
    }


    public void setImageUri(String uri) {
        this.uri = uri;
    }

}
