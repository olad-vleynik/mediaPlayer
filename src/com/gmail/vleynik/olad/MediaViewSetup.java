package com.gmail.vleynik.olad;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class MediaViewSetup {
    private final MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private int viewCount = 0;
    private ControlPanelController cpController;

    public MediaViewSetup(File videoFile, int width, int height) {
        MediaPlayerSetup(videoFile, width, height);

        mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(width);
        mediaView.setFitHeight(height);
        mediaView.setPreserveRatio(false);
    }

    public MediaViewSetup(File videoFile, int width, int height, MediaView mediaView) {
        MediaPlayerSetup(videoFile, width, height);

        this.mediaView = mediaView;
        mediaView.setMediaPlayer(mediaPlayer);
    }


    public void MediaPlayerSetup(File videoFile, int width, int height) {
        mediaPlayer = new MediaPlayer(new Media(videoFile.toURI().toString()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setOnRepeat(new Runnable() {
            @Override
            public void run() {
                viewCount++;
                cpController.setViewCount(viewCount);
            }
        });
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setCpController(ControlPanelController cpController) {
        this.cpController = cpController;
    }
}
