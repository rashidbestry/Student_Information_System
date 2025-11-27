package com.student.is.PageControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.student.is.Authentication.Authentication;
import com.student.is.DataManagement.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import java.io.IOException;

public class StudentPageController {

    public  void exitButtonAction(ActionEvent event) throws IOException,IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/Welcome.fxml"));
        Parent root = loader.load();
        Scene oncekiSahne = new Scene(root);

        Node source = (Node) event.getSource();
        Stage mevcutStage = (Stage) source.getScene().getWindow();

        mevcutStage.setScene(oncekiSahne);
        mevcutStage.show();

    }
    public  void AcademicCalendarButtonAction(ActionEvent event) throws IOException,IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/StudentAcademicCalendar.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public  void AbsenteeismButtonAction(ActionEvent event) throws IOException,IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/StudentAbsenteeism.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public  void ListOfGradesButtonAction(ActionEvent event) throws IOException,IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/StudentListOfGrades.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void studentMainBackButtonAction(ActionEvent event) throws IOException,IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/StudentMainPage.fxml"));
        Parent root = loader.load();
        Scene oncekiSahne = new Scene(root);

        Node source = (Node) event.getSource();
        Stage mevcutStage = (Stage) source.getScene().getWindow();

        mevcutStage.setScene(oncekiSahne);
        mevcutStage.show();
    }
}
