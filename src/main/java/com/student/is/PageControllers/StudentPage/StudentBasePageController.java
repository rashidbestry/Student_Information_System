package com.student.is.PageControllers.StudentPage;

import com.student.is.ClassStructure.Student;
import com.student.is.PageControllers.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
public class StudentBasePageController {


    @FXML public AnchorPane studentMainPageAnchorPane;
    @FXML private Label studentNameLabel;

    @FXML
    public void initialize() {
        ContentLoader.setMainContentPane(studentMainPageAnchorPane);
        // varsayılan ekran
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");

        Object sessionUser = ContentLoader.getCurrentUserSession();
        if (sessionUser instanceof Student) {
            Student currentStudent = (Student) sessionUser;
            studentNameLabel.setText(currentStudent.getFirstName() + " " + currentStudent.getLastName());
        }

    }

    public void StudentInformationButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentInformation.fxml");
    }
    public void ListOfGradesButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentListOfGrades.fxml");
    }
    public void AbsenteeismButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentAbsenteeism.fxml");
    }
    public void AcademicCalendarButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentAcademicCalendar.fxml");
    }
    public void ScholarsButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentScholars.fxml");
    }

    public  void exitButtonAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/Welcome.fxml"));
        Parent root = loader.load();
        Scene oncekiSahne = new Scene(root);

        // Mevcut pencereyi kapatır ve yeni sahneyi gösterir.
        Node source = (Node) event.getSource();
        Stage mevcutStage = (Stage) source.getScene().getWindow();

        mevcutStage.setScene(oncekiSahne);
        mevcutStage.show();
    }
}
