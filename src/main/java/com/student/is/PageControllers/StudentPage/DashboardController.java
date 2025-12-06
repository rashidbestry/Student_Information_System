package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Student;
import com.student.is.PageControllers.ContentLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.student.is.PageControllers.ContentLoader.resourceBundle;

public class DashboardController {


    @FXML private Label nameLabelTitle;
    @FXML private Label lastNameLabelTitle;
    @FXML private Label bornDateLabelTitle;
    @FXML private Label facultyLabelTitle;
    @FXML private Label sectionLabelTitle;

    @FXML private Label classLabelTitle;
    @FXML private Label termLabelTitle;
    @FXML private Label agnoLabelTitle;
    @FXML private Label lectureCountLabelTitle;
    @FXML private Label advisorLabelTitle;


    public void initialize(){
        Object sessionUser = ContentLoader.getCurrentUserSession();
        sessionUser=Authentication.currentStudentUser;

        if(sessionUser instanceof Student){
            Student student = (Student) sessionUser;

            nameLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.name"), student.getFirstName()));
            lastNameLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.surname"), student.getLastName()));
            bornDateLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.birth_date"), student.getBornDate()));
            facultyLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.faculty"), student.getFaculty()));
            sectionLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.section"), student.getSection()));

            classLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.class"), student.getClassYear()));
            termLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.term"), "2025-2026 Öğretim Yılı"));
            agnoLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.gpa"), student.getGpa()));
            lectureCountLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.lecture_count"), String.valueOf(student.lectures.size())));

            String advisor = switch (student.getClassYear()){
                case 1 -> "Cengiz Hark";
                case 2 -> "Adnan Fatih Kocamaz";
                case 3 -> "Barış Baykant Alagöz";
                case 4 -> "Taha Burak Özdemir";
                default -> " ";
            };
            advisorLabelTitle.textProperty().bind(Bindings.concat(resourceBundle.getString("student.dashboard.label.advisor"), advisor));

        }
    }

}