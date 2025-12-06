package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Lecture;
import com.student.is.DataManagement.Database;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class AddLectureController {

    private Stage dialogStage;
    @FXML private TextField courseCodeField;
    @FXML private TextField courseNameField;
    @FXML private TextField creditField;
    @FXML private TextField aktsField;
    @FXML private TextField classField;
    @FXML private TextField seasonField;
    @FXML private TextField theoreticalHoursField; // Teorik Ders Saati
    @FXML private TextField applicationHoursField;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void addCourseActionButton() {
        if (courseCodeField.getText().isEmpty() || courseNameField.getText().isEmpty() || creditField.getText().isEmpty()) {
            return;
        }
        try {
            int credit = Integer.parseInt(creditField.getText());
            int akts = Integer.parseInt(aktsField.getText());
            int theoreticalHours = Integer.parseInt(theoreticalHoursField.getText());
            int applicationHours = Integer.parseInt(applicationHoursField.getText());
            int classYear = Integer.parseInt(classField.getText());


            String courseCode = courseCodeField.getText();
            String courseName = courseNameField.getText();
            String season = seasonField.getText();

            Lecture newCourse = new Lecture(courseCode, courseName, credit, akts, classYear,  theoreticalHours, applicationHours,season);

            Database.lectureList.add(newCourse); //DataBase ekle

            dialogStage.close();

        } catch (NumberFormatException e) {

        } catch (Exception e) {
            System.err.println("Ders ekleme işlemi sırasında bir hata oluştu: ");
        }
    }
    @FXML
    private void cancelActionButton() {
        dialogStage.close();
    }

}
