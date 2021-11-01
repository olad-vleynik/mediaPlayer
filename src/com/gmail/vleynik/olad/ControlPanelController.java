package com.gmail.vleynik.olad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    public void setViewCount(int views) {

        viewCount.setText(String.valueOf(views));
    }

    public void setVideoName(String name) {

        videoName.setText(name);
    }
}
