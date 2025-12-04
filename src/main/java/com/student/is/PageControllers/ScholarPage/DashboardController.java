package com.student.is.PageControllers.ScholarPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personal;
import com.student.is.PageControllers.ContentLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {
    @FXML
    Label scholarFullNameLabel;
    @FXML
    Label scholarLectureCountLabel;
    @FXML
    Label totalStudentsLabel;

    public void initialize() {
        Object sessionUser = ContentLoader.getCurrentUserSession();
        sessionUser = Authentication.currentPersonalUser;

        if (sessionUser instanceof Personal) {
            Personal personal = (Personal) sessionUser;

            scholarFullNameLabel.setText(personal.getName()+" " +personal.getSurname());
            scholarLectureCountLabel.setText(String.valueOf(personal.lectures.size()));

            int toplam=0;
            for(Lecture lec : personal.lectures) {
                toplam+=lec.getLectureStudentList().size();
            }
            totalStudentsLabel.setText(String.valueOf(toplam));

        }
    }
}