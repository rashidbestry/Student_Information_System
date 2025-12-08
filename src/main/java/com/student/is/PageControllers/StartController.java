package com.student.is.PageControllers;

import com.student.is.ClassStructure.Lecture;
import com.student.is.DataManagement.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

public class StartController extends Application {
    private static final Locale DEFAULT_LOCALE = new Locale("tr", "TR"); //varsayılan dil türkçe
    @Override
    public void start(Stage stage) throws IOException {

        Database.createTemp();
        Database.createLectureList();
        Database.createStudentList();
        Database.createPersonalList();

        for (Lecture lec : Database.lectureList)
            lec.createStudentListForLecture();

        //logo ekleme
        Image applicationIcon = new Image(getClass().getResourceAsStream("/com/student/is/images/MainApp_logo.jpeg"));
        stage.getIcons().add(applicationIcon);

        ContentLoader.initializeLanguage(DEFAULT_LOCALE);

        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("/com/student/is/fxml/Welcome.fxml"),ContentLoader.getResourceBundle());
        Scene scene = new Scene(fxmlLoader.load(),1280,768);
        stage.setTitle("AORA Üniversitesi Öğrenci Bilgi Sistemi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }
}
