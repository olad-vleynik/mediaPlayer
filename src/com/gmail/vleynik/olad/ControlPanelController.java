package com.gmail.vleynik.olad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.stage.FileChooser;

import java.io.File;

public class ControlPanelController {

    @FXML
    private Text viewCount;

    @FXML
    private TextField videoName;

    @FXML
    private void playPause(ActionEvent event) {
        if (Main.mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            Main.mediaPlayer.pause();
        } else {
            Main.mediaPlayer.play();
        }
    }

    @FXML
    private void replay(ActionEvent event) {
        Main.mediaPlayer.seek(Duration.ZERO);
        Main.mediaPlayer.play();
    }

    @FXML
    private void restart(ActionEvent event) {
        //TODO
    }

    @FXML
    private void openNew(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video files", "*.mp4");
        fileChooser.setInitialDirectory(new File("resources"));
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(Main.pStage);
        if (file != null) {
            MediaPlayer newMediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
            setVideoName(file.getName());
            newMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            Main.mediaPlayer.stop();
            Main.mediaPlayer = newMediaPlayer;
            Main.videoFilePath = file.getAbsolutePath();
            Main.mediaView.setMediaPlayer(newMediaPlayer);
            Main.viewCount = 0;
            newMediaPlayer.setAutoPlay(true);
        }
    }

    public void setViewCount(int views) {

        viewCount.setText(String.valueOf(views));
    }

    public void setVideoName(String name) {

        videoName.setText(name);
    }
}
