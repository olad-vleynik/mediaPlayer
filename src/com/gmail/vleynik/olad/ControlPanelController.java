package com.gmail.vleynik.olad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.FileChooser;

import java.io.File;

public class ControlPanelController {

    private Stage primaryStage;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    @FXML
    private Text viewCount;

    @FXML
    private TextField videoName;

    @FXML
    private void playPause(ActionEvent event) {
        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }
    }

    @FXML
    private void replay(ActionEvent event) {
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
    }

    @FXML
    private void restart(ActionEvent event) {
        Main.restartApp();
        //TODO
    }

    @FXML
    private void openNew(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video files", "*.mp4");
        fileChooser.setInitialDirectory(new File("resources"));
        fileChooser.getExtensionFilters().add(filter);
        File videoFile = fileChooser.showOpenDialog(primaryStage);
        if (videoFile != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            MediaViewSetup mediaViewSetup = new MediaViewSetup(videoFile, 640,480, mediaView);
            mediaViewSetup.setCpController(this);
            setVideoName(videoFile.getName());
            mediaPlayer = mediaViewSetup.getMediaPlayer();
            mediaView.setMediaPlayer(mediaPlayer);
            LastVideo.save(videoFile.getAbsolutePath());
            setViewCount(0);
        }
    }

    public void setViewCount(int views) {
        viewCount.setText(String.valueOf(views));
    }

    public void setVideoName(String name) {
        videoName.setText(name);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void setMediaView(MediaView mediaView) {
        this.mediaView = mediaView;
    }
}
