package com.student.is.PageControllers.AdminPage;

import com.student.is.PageControllers.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminBasePageController {

    @FXML
    public AnchorPane adminMainPageAnchorPane;

    @FXML
    public void initialize() {
        ContentLoader.setMainContentPane(adminMainPageAnchorPane);

    }

    @FXML
    public void StudentOperationsButtonAction(ActionEvent actionEvent) throws IOException {
            ContentLoader.loadPage("/com/student/is/fxml/AdminStudentOperations.fxml");
    }
    @FXML
    public void ScholarOperationsButtonAction(ActionEvent actionEvent) throws IOException {
            ContentLoader.loadPage("/com/student/is/fxml/AdminScholarOperations.fxml");
    }
    @FXML
    public void LectureOperationsActionButton(ActionEvent event) throws IOException {
        ContentLoader.loadPage("/com/student/is/fxml/AdminLectureOperations.fxml");

    }
    @FXML
    public void GradeOperationsActiomButton(ActionEvent event) throws IOException {
        ContentLoader.loadPage("/com/student/is/fxml/AdminGradeOperations.fxml");
    }
    @FXML
    public void AbsenceOperationsActionButton(ActionEvent event) throws IOException {
        ContentLoader.loadPage("/com/student/is/fxml/AdminAbsenteeismOperations.fxml");

    }
    @FXML
    public  void exitButtonAction(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/Welcome.fxml"), ContentLoader.getResourceBundle());
        Parent root = loader.load();
        Scene oncekiSahne = new Scene(root);

        // Mevcut pencereyi kapatır ve yeni sahneyi gösterir.
        Node source = (Node) event.getSource();
        Stage mevcutStage = (Stage) source.getScene().getWindow();

        mevcutStage.setScene(oncekiSahne);
        mevcutStage.show();
    }

}
