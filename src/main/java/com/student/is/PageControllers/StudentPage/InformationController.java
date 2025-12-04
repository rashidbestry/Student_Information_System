package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Student;
import com.student.is.PageControllers.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InformationController {

    @FXML TextField nameTextField;
    @FXML TextField surnameTextField;
    @FXML TextField birthDateTextField;
    @FXML TextField numberTextField;

    @FXML TextField mailTextField;
    @FXML TextField phoneTextField;
    @FXML TextArea adressTextArea;

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


    }













    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }

}
