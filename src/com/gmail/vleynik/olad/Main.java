package com.gmail.vleynik.olad;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.Objects;

public class Main extends Application {

    public static File videoFile;

    @Override
    public void start(Stage primaryStage) throws Exception {

        videoFile = new File(LastVideo.load());
        MediaViewSetup mediaViewSetup = new MediaViewSetup(videoFile, 640,480);

        Scene scene = new Scene(new Pane(mediaViewSetup.getMediaView()));
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();


        Stage controlPanel = new Stage();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("ControlPanel.fxml")));
        Parent root = loader.load();
        ControlPanelController controlPanelController = loader.getController();
        controlPanelController.setPrimaryStage(primaryStage);
        controlPanelController.setMediaView(mediaViewSetup.getMediaView());
        controlPanelController.setMediaPlayer(mediaViewSetup.getMediaPlayer());
        controlPanelController.setVideoName(videoFile.getName());
        mediaViewSetup.setCpController(controlPanelController);
        controlPanel.setScene(new Scene(root));
        controlPanel.setAlwaysOnTop(true);
        controlPanel.setX(0);
        controlPanel.setY(485);
        controlPanel.setResizable(false);
        controlPanel.initStyle(StageStyle.UTILITY);
        controlPanel.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                primaryStage.close();
            }
        });
        controlPanel.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void restartApp() {
//        Platform.exit();
//        Platform.runLater( () -> {
//            try {
//                new Main().launch();
//                //new Main().start( new Stage() );
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
    }
}
