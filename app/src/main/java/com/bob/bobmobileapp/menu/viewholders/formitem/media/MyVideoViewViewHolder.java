package com.bob.bobmobileapp.menu.viewholders.formitem.media;

import android.content.Context;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.mediaviews.MyImageView;
import com.bob.bobmobileapp.tools.UI.views.mediaviews.MyVideoView;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.util.HashMap;


/**
 * Created by user on 01/09/2017.
 */

public class MyVideoViewViewHolder extends MyImageViewViewHolder {

    protected MyVideoView videoView;

    public MyVideoViewViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    public MyVideoViewViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public MyImageView getTextView() {
        return this.videoView;
    }

    public void setMediaView(MyVideoView videoView) {
        super.setMediaView(videoView);
        this.videoView = videoView;
    }

    @Override
    protected void initView(View view) {
        this.setMediaView((MyVideoView) view.findViewById(R.id.my_video_view));
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.videoView.setVideoUri(null);
    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("video_uri")) != null) {
            this.videoView.setVideoUri(curProperty);
        }
        if ((curProperty = properties.get("youtube_id")) != null) {
            this.videoView.setYoutubeVideoByID(curProperty);
        }
    }

}
