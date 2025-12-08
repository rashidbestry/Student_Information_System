package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Student;
import com.student.is.ClassStructure.StudentAbsenceList;
import com.student.is.PageControllers.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.HashMap;

public class AbsenteeismController {

    @FXML private TableView<StudentAbsenceList> lectureAbsenceTable;
    @FXML private TableColumn<StudentAbsenceList, String> lectureCodeColumn;
    @FXML private TableColumn<StudentAbsenceList, String> lectureNameColumn;
    @FXML private TableColumn<StudentAbsenceList, String> lectureAbsenceColumn;
    @FXML private TableColumn<StudentAbsenceList, Integer> lectureCreditColumn;
    @FXML private TableColumn<StudentAbsenceList, Integer> lectureEKTSColumn;
    @FXML private TableColumn<StudentAbsenceList, Integer> TotalAbsenceColumn;

    public void initialize(){

        lectureCodeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureCode"));
        lectureNameColumn.setCellValueFactory(new PropertyValueFactory<>("lectureName"));
        lectureAbsenceColumn.setCellValueFactory(new PropertyValueFactory<>("lectureAbsence"));
        lectureCreditColumn.setCellValueFactory(new PropertyValueFactory<>("lectureCredit"));
        lectureEKTSColumn.setCellValueFactory(new PropertyValueFactory<>("lectureEKTS"));
        TotalAbsenceColumn.setCellValueFactory(new PropertyValueFactory<>("totalAbsence"));

        load();
    }
    public void load(){
        Student user = Authentication.currentStudentUser;

        ArrayList<Lecture> lectures = user.getLectures();
        HashMap<String, String> stuAbsence = user.getStuAbsence();

        ObservableList<StudentAbsenceList> Data = FXCollections.observableArrayList();

        for (Lecture lecture : lectures) {
            String lectureCode = lecture.getLectureCode();
            String lectureName = lecture.getLectureName();
            String lectureAbsence=((lecture.getLectureTheory())+"+"+(lecture.getLectureApplication()));

            int lectureCredit=lecture.getLectureCredit();
            int lectureEKTS =lecture.getLectureAKTS();
            int teorikAbsence;
            int praticAbsence;
            int totalAbsence=0;

            String studentAbsence = stuAbsence.get(lectureCode);
            if (studentAbsence!=null){
                String[] lectureAbsenceArray = studentAbsence.split(",");
                teorikAbsence= Integer.parseInt(lectureAbsenceArray[0]);
                praticAbsence= Integer.parseInt(lectureAbsenceArray[1]);
                totalAbsence= teorikAbsence + praticAbsence;

            }
            StudentAbsenceList yeni = new StudentAbsenceList(lectureCode,lectureName,lectureAbsence,lectureCredit,lectureEKTS,totalAbsence);
            Data.add(yeni);

        }
        lectureAbsenceTable.setItems(Data);
    }
    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }
}
