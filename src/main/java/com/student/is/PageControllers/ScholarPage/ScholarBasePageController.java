package com.student.is.PageControllers.ScholarPage;

import com.student.is.ClassStructure.Personal;
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

public class ScholarBasePageController {

    @FXML
    public AnchorPane scholarMainPageAnchorPane;
    public Label scholarNameLabel;


    @FXML
    public void initialize() {
        ContentLoader.setMainContentPane(scholarMainPageAnchorPane);
        // varsayılan ekran
        ContentLoader.loadPage("/com/student/is/fxml/ScholarDashboard.fxml");

        Object sessionUser = ContentLoader.getCurrentUserSession();
        if (sessionUser instanceof Personal) {
            Personal currentPersonal = (Personal) sessionUser;
            scholarNameLabel.setText(currentPersonal.getName() + " " + currentPersonal.getSurname());
        }
    }

    public void GradeOperationsButtonAction(ActionEvent actionEvent) throws IOException {
        ContentLoader.loadPage("/com/student/is/fxml/ScholarGradeOperations.fxml");
    }
    public void AbsenteeismOperationsButtonAction(ActionEvent actionEvent) throws IOException {
        ContentLoader.loadPage("/com/student/is/fxml/ScholarAbsenteeism.fxml");
    }
    public void DashboardOperationsButtonAction(ActionEvent actionEvent) throws IOException {
        ContentLoader.loadPage("/com/student/is/fxml/ScholarDashboard.fxml");
    }


    public  void exitButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/Welcome.fxml"),ContentLoader.getResourceBundle());
        Parent root = loader.load();
        Scene oncekiSahne = new Scene(root);

        // Mevcut pencereyi kapatır ve yeni sahneyi gösterir.
        Node source = (Node) event.getSource();
        Stage mevcutStage = (Stage) source.getScene().getWindow();

        mevcutStage.setScene(oncekiSahne);
        mevcutStage.show();
    }
}
