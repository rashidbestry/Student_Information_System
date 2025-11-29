package com.student.is.PageControllers.StudentPage;
import com.student.is.PageControllers.ContentLoader;
import com.student.is.ClassStructure.Student;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GradeListController {

    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }
}
