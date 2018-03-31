package com.bob.bobmobileapp.tools.video;

import android.content.Context;
import android.util.SparseArray;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

/**
 * Created by User on 22/01/2018.
 */

public class MyYoutubeExtractor extends YouTubeExtractor {

    YoutubeExtractorHandler handler;

    public MyYoutubeExtractor(Context con) {
        super(con);
        this.handler = null;
    }

    @Override
    protected void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta videoMeta) {
        if (this.handler != null) {
            this.handler.handle(ytFiles, videoMeta);
        }
    }

    public void setYoutubeExtractorHandler(YoutubeExtractorHandler handler) {
        this.handler = handler;
    }

    public static abstract class YoutubeExtractorHandler {
        public abstract void handle(SparseArray<YtFile> ytFiles, VideoMeta videoMeta);
    }
}
