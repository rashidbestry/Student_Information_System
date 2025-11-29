package com.student.is.PageControllers.ScholarPage;

import com.student.is.PageControllers.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GradeOperationController {
    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/ScholarDashboard.fxml");
    }
}
