package com.student.is.PageControllers.ScholarPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personal;
import com.student.is.PageControllers.ContentLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.student.is.PageControllers.ContentLoader.resourceBundle;

public class DashboardController {
    @FXML Label welcomeScholarTitle;
    @FXML Label scholarLectureCountLabel;
    @FXML Label totalStudentLabel;
    private IntegerProperty toplamOgrenciSayisi;

    public void initialize() {
        Object sessionUser = ContentLoader.getCurrentUserSession();
        sessionUser = Authentication.currentPersonalUser;

        if (sessionUser instanceof Personal) {
            Personal personal = (Personal) sessionUser;
            welcomeScholarTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("scholar.welcome"), personal.getName()+" " +personal.getSurname()));
            scholarLectureCountLabel.textProperty().bind(Bindings.concat(resourceBundle.getString("scholar.label.lecture_count"), String.valueOf(personal.lectures.size())));

            int toplam = 0;
            for(Lecture lec : personal.lectures) {
                toplam+=lec.getLectureStudentList().size();
            }
            this.toplamOgrenciSayisi = new SimpleIntegerProperty(toplam);
            totalStudentLabel.textProperty().bind(Bindings.concat(resourceBundle.getString("scholar.label.student_count"), toplamOgrenciSayisi.asString()));

        }
    }
}