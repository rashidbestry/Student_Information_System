package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import com.student.is.PageControllers.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class InformationController {

    @FXML TextField nameTextField;
    @FXML TextField surnameTextField;
    @FXML TextField birthDateTextField;
    @FXML TextField numberTextField;
    @FXML TextField mailTextField;
    @FXML TextField phoneTextField;
    @FXML TextArea adressTextArea;
    @FXML Button applyButton;

    public void initialize(){
        Object sessionUser = ContentLoader.getCurrentUserSession();
        sessionUser= Authentication.currentStudentUser;

        if(sessionUser instanceof Student) {
            Student student = (Student) sessionUser;

            nameTextField.setText(student.getFirstName());
            surnameTextField.setText(student.getLastName());
            birthDateTextField.setText(student.getBornDate());
            numberTextField.setText(student.getStuId());

            mailTextField.setText(student.getEmail());
            phoneTextField.setText(student.getPhoneNo());
            adressTextArea.setText(student.getAddress());

        }
        Student student = (Student) sessionUser;
        applyButton.setOnAction(event -> {
            student.email=mailTextField.getText();
            student.phoneNo=phoneTextField.getText();
            student.address=adressTextArea.getText();
            Database.changeObjectData(student);

            try {
                Stage popupStage = ContentLoader.loadPopupStage("/com/student/is/fxml/ChangesApplied.fxml");
                popupStage.showAndWait();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }

}
