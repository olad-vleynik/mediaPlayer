package com.gmail.vleynik.olad;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.Objects;

public class Main extends Application {

    private static int viewCount = 0;
    public static String videoFilePath = lastVideo.load();
    public static MediaPlayer mediaPlayer;
    public static MediaView mediaView;
    public static Stage pStage;
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        pStage = primaryStage;
        File videoFile = new File(videoFilePath);
        Media media = new Media(videoFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(640);
        mediaView.setFitHeight(480);
        mediaView.setPreserveRatio(false);


        scene = new Scene(new Pane(mediaView), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();


        Stage controlPanel = new Stage();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("com.gmail.vleynik.olad.fxml")));
        Parent root = loader.load();
        ControlPanelController controlPanelController = loader.getController();
        controlPanel.setScene(new Scene(root));
        controlPanel.setAlwaysOnTop(true);
        controlPanel.setX(0);
        controlPanel.setY(485);
        controlPanel.setResizable(false);
        controlPanel.initStyle(StageStyle.UTILITY);
        controlPanel.show();
        controlPanel.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                primaryStage.close();
            }
        });

        mediaPlayer.setOnRepeat(new Runnable() {
            @Override
            public void run() {
                viewCount++;
                controlPanelController.setViewCount(viewCount);
            }
        });
        controlPanelController.setVideoName(videoFile.getName());
    }

    @Override
    public void stop() {
        lastVideo.save(new File(videoFilePath).getAbsolutePath());
    }

    public static void main(String[] args) {
        launch();
    }
}
