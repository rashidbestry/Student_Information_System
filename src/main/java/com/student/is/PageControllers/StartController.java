package com.student.is.PageControllers;
import com.student.is.DataManagement.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StartController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("/com/student/is/fxml/Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1280,768);
        stage.setTitle("OBS Giriş Ekranı");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
