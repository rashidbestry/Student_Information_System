package com.student.is.PageControllers;

import com.student.is.DataManagement.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Database.createLectureList();
        Database.createStudentList();
        Database.createPersonalList();
        Database.createTemp();



        //logo ekleme
        Image applicationIcon = new Image(getClass().getResourceAsStream("/com/student/is/images/MainApp_logo.jpeg"));
        stage.getIcons().add(applicationIcon);

        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("/com/student/is/fxml/Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1280,768);
        stage.setTitle("AORA Üniversitesi Öğrenci Bilgi Sistemi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();




    }
}
