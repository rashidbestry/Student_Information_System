package com.example.obs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OBSApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OBSApplication.class.getResource("/com/example/obs/fxml/MainLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1280,768);
        stage.setTitle("OBS Giriş Ekranı");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
