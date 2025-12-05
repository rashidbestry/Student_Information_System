package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Student;
import com.student.is.ClassStructure.StudentGradeList;
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

public class GradeListController {

    @FXML private TableView<StudentGradeList> lectureGradeTable;
    @FXML private TableColumn<StudentGradeList, String> lectureCodeColumn;
    @FXML private TableColumn<StudentGradeList, String> lectureNameColumn;
    @FXML private TableColumn<StudentGradeList, String> lectureStatusColumn;
    @FXML private TableColumn<StudentGradeList, Double> visaGradeColumn;
    @FXML private TableColumn<StudentGradeList, Double> finalGradeColumn;
    @FXML private TableColumn<StudentGradeList, Double> averageColumn;
    @FXML private TableColumn<StudentGradeList, String> letterGradeColumn;
    @FXML private TableColumn<StudentGradeList, String> statusColumn;

    public void initialize(){

        lectureCodeColumn.setCellValueFactory(new PropertyValueFactory<>("LectureCode"));
        lectureNameColumn.setCellValueFactory(new PropertyValueFactory<>("LectureName"));
        lectureStatusColumn.setCellValueFactory(new PropertyValueFactory<>("LectureStatus"));
        visaGradeColumn.setCellValueFactory(new PropertyValueFactory<>("vizeNote"));
        finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("finalNote"));
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("averageNote"));
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterNote"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadData();
    }
    public void loadData(){
        Student user = Authentication.currentStudentUser;

        ArrayList<Lecture> lectures = user.getLectures();
        HashMap<String, String> stuNotes = user.getStuNotes();

        ObservableList<StudentGradeList> Data = FXCollections.observableArrayList();

        for (Lecture lecture : lectures) {
            String lectureCode = lecture.getLectureCode();
            String lectureName = lecture.getLectureName();

            double vizeNote=0.0;
            double finalNote=0.0;
            double averageNote=0.0;
            String lectureStatus="Sonuçlandırıldı";
            String letterNote;
            String status;

            String lectureNote = stuNotes.get(lectureCode);
            if (lectureNote != null) {

                String[] lectureNoteArray = lectureNote.split(",");
                vizeNote = Double.parseDouble(lectureNoteArray[0]);
                finalNote = Double.parseDouble(lectureNoteArray[1]);

                averageNote = Math.round((vizeNote*0.4) + (finalNote*0.6));
            }
            if (averageNote >= 90.0) {
                letterNote = "AA";
            } else if (averageNote >= 85.0) {
                letterNote = "BA";
            } else if (averageNote >= 80.0) {
                letterNote = "BB";
            } else if (averageNote >= 75.0) {
                letterNote = "CB";
            } else if (averageNote >= 70.0) {
                letterNote = "CC";
            } else if (averageNote >= 60.0) {
                letterNote = "DC";
            } else if (averageNote >= 50.0) {
                letterNote = "DD";
            } else {
                letterNote = "FF";
            }

            if(letterNote.equals("FF")){
                status="Kaldı";
            }
            else{
                status="Geçti";
            }
            StudentGradeList yeni = new StudentGradeList(lectureCode,lectureName,lectureStatus,letterNote,status,vizeNote,finalNote,averageNote);
            Data.add(yeni);
        }
        lectureGradeTable.setItems(Data);

    }
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }
}

