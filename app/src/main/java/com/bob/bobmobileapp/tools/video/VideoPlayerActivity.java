package com.bob.bobmobileapp.tools.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.listener.VideoControlsSeekListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import com.bob.bobmobileapp.R;


public class VideoPlayerActivity extends Activity implements VideoControlsSeekListener, OnPreparedListener {
    public static final String EXTRA_URL = "EXTRA_URL";
    public static final int PLAYLIST_ID = 6; //Arbitrary, for the example (different from audio)

    protected VideoView videoView;

    protected String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player_activity);

        retrieveExtras();
        init();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onSeekStarted() {
        return true;
    }

    @Override
    public boolean onSeekEnded(long seekTime) {
        return true;
    }

    /**
     * Retrieves the extra associated with the selected playlist index
     * so that we can start playing the correct item.
     */
    protected void retrieveExtras() {
        Bundle extras = getIntent().getExtras();
        url = extras.getString(EXTRA_URL, "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_30mb.mp4");
    }

    protected void init() {

        videoView = findViewById(R.id.video_play_activity_video_view);
        videoView.setHandleAudioFocus(false);
        videoView.getVideoControls().setSeekListener(this);
        videoView.setOnPreparedListener(this);
        videoView.setVideoURI(Uri.parse(this.url));
    }


    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        videoView.start();
    }

}
