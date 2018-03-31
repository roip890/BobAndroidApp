package com.bob.bobmobileapp.tools.image;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bob.bobmobileapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * Activity that gets transitioned to
 */
public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getActionBar() != null) {
            getActionBar().hide();
        }
        this.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(R.layout.image_layout);
        PhotoView imageView = (PhotoView)findViewById(R.id.image_view);
        Intent intent = this.getIntent();
        if (intent.hasExtra("IMAGE_URI")) {
            String uri = intent.getStringExtra("IMAGE_URI");
            Glide
                    .with(this)
                    .load(Uri.parse(uri))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .into(imageView);
        }
    }
}
