package com.bob.bobmobileapp.tools.UI.views.mediaviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;

import com.bob.bobmobileapp.tools.video.MyYoutubeExtractor;
import com.bob.bobmobileapp.tools.video.VideoPlayerActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YtFile;
import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by User on 20/01/2018.
 */

public class MyVideoView extends MyImageView {

    protected String videoUri;
    protected Bitmap thumbnail;

    public MyVideoView(Context context) {
        this(context, null);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.thumbnail = null;
    }

    public void setVideoUri(String uri) {
        this.videoUri = uri;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
                    mmr.setDataSource(videoUri);
                    thumbnail = mmr.getFrameAtTime(1000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
                    setVideoPreviewAnimation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setYoutubeVideoByID(final String id) {

        Glide.with(this.getContext()).load("https://img.youtube.com/vi/" + id + "/maxresdefault.jpg")
                .into(this.imageView);


        String youtubeLink = "http://youtube.com/watch?v=" + id;
        MyYoutubeExtractor youtubeExtractor = new MyYoutubeExtractor(this.getContext());
        youtubeExtractor.setYoutubeExtractorHandler(new MyYoutubeExtractor.YoutubeExtractorHandler() {
            @Override
            public void handle(SparseArray<YtFile> ytFiles, VideoMeta videoMeta) {
                if (ytFiles != null) {
                    int itag = 22;
                    videoUri = ytFiles.get(itag).getUrl();
                    setVideoPreviewAnimation();
                }
            }
        });
        youtubeExtractor.extract(youtubeLink, false, false);
    }

    protected void setVideoPreviewAnimation() {
        if ((getContext() instanceof Activity) && (this.videoUri != null)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    setVideoThumbnail();
                    try {
                        FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
                        mmr.setDataSource(videoUri);
                        String time = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_DURATION);
                        long timeInmillisec = Long.parseLong( time );
                        long timeInSec = timeInmillisec / 1000;
                        final ArrayList<Bitmap> frames = new ArrayList<Bitmap>();

                        for (int i = 0; i < timeInSec; i++ ) {
                            frames.add(mmr.getFrameAtTime(i * 1000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST));
                        }

                        final AnimationDrawable animatedGIF = new AnimationDrawable();

                        for (int i = 0; i < timeInSec; i++ ) {
                            animatedGIF.addFrame(new BitmapDrawable(getResources(), frames.get(i)), 500);
                        }
                        ((Activity)getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageDrawable(animatedGIF);
                                animatedGIF.start();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    protected void onImageClick() {
        if ((getContext() instanceof Activity) && (this.videoUri != null)) {
            Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
            intent.putExtra("EXTRA_URL", this.videoUri);
            getContext().startActivity(intent);
        }
    }

    protected void setVideoThumbnail() {
        if ((this.getContext() instanceof  Activity) && (this.thumbnail != null)) {
            ((Activity)getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getContext())
                            .load(thumbnail)
                            .into(imageView);
                }
            });
        }
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
