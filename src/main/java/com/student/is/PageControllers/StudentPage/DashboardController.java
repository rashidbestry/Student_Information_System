package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Student;
import com.student.is.PageControllers.ContentLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML private Label nameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label bornDateLabel;
    @FXML private Label facultyLabel;
    @FXML private Label sectionLabel;

    @FXML private Label classLabel;
    @FXML private Label termLabel;
    @FXML private Label agnoLabel;
    @FXML private Label lectureCountLabel;
    @FXML private Label advisorLabel;

    public void initialize(){
        Object sessionUser = ContentLoader.getCurrentUserSession();
        sessionUser=Authentication.currentStudentUser;

        if(sessionUser instanceof Student){
            Student student = (Student) sessionUser;

            nameLabel.setText(student.getFirstName());
            lastNameLabel.setText(student.getLastName());
            bornDateLabel.setText(student.getBornDate());
            facultyLabel.setText(student.getFaculty());
            sectionLabel.setText(student.getSection());

            classLabel.setText(String.valueOf(student.getClassYear())); //stringe dönüştür
            termLabel.setText("2025-2026 Öğretim Yılı");
            agnoLabel.setText(String.valueOf(student.getGpa())); // stringe dönüştür
            lectureCountLabel.setText(String.valueOf(student.lectures.size()));

            switch (student.getClassYear()){
                case 1 -> advisorLabel.setText("Cengiz Hark");
                case 2 -> advisorLabel.setText("Adnan Fatih Kocamaz");
                case 3 -> advisorLabel.setText("Barış Baykant Alagöz");
                case 4 -> advisorLabel.setText("Taha Burak Özdemir");
            }


        }
    }

}
