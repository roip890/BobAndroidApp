package com.bob.bobmobileapp.tools.UI.views.mediaviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.image.ImageActivity;
import com.bob.bobmobileapp.tools.UI.views.MyBaseView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by User on 14/01/2018.
 */

public class MyImageView extends MyBaseView{

    protected ImageView imageView;
    protected String imageUri;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.imageUri = null;
    }

    @Override
    protected void createMainView() {
        this.view = new ImageView(this.getContext());
        this.imageView = (ImageView) this.view;
    }

    @Override
    protected void initMainView() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClick();
            }
        });
    }

    public void setImageUri(String uri) {
        this.imageUri = uri;
        if (this.imageUri !=  null) {
            Glide
                    .with(this.getContext())
                    .load(Uri.parse(uri))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .apply(RequestOptions.fitCenterTransform())
                    .into(this.imageView);
        }
    }

    public void setImageWidth(int width) {
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.imageView.getLayoutParams().width = UIUtilsManager.get().convertPixelsToDp(this.getContext(), width);

    }

    public void setImageHeight(int height) {
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.imageView.getLayoutParams().height = UIUtilsManager.get().convertPixelsToDp(this.getContext(), height);
    }

    protected void onImageClick() {
        if (getContext() instanceof Activity) {
//                    ImageDialog dialog=new ImageDialog(((Activity)getContext()));
//                    dialog.setImageUri(imageUri);
//                    dialog.show();

            if (Build.VERSION.SDK_INT < 21) {
                Toast.makeText(getContext(), "21+ only, keep out", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getContext(), ImageActivity.class);
                if (imageUri != null) {
                    intent.putExtra("IMAGE_URI", imageUri);
                }
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(((Activity)getContext()), view, "image");
                ((Activity)getContext()).startActivity(intent, options.toBundle());
            }
        }
    }
}
