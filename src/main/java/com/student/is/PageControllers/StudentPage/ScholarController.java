package com.student.is.PageControllers.StudentPage;

import com.student.is.PageControllers.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ScholarController {

    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }
}
