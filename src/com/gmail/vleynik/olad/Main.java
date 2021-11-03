package com.gmail.vleynik.olad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

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


        ControlPanelStageSetup cpStageSetup = new ControlPanelStageSetup ("ControlPanel.fxml", primaryStage, mediaViewSetup, videoFile);
        cpStageSetup.getControlPanel().show();
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
