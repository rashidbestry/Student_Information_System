package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Student;
import com.student.is.PageControllers.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class GradeListController {

    @FXML private TableView<Lecture> lectureGradeTable;
    @FXML private TableColumn<Lecture, String> lectureCodeColumn;
    @FXML private TableColumn<Lecture, String> lectureNameColumn;
    @FXML private TableColumn<Lecture, String> lectureStatusColumn;
    @FXML private TableColumn<Lecture, String> visaGradeColumn;
    @FXML private TableColumn<Lecture, String> finalGradeColumn;
    @FXML private TableColumn<Lecture, String> makeUpGradeColumn;
    @FXML private TableColumn<Lecture, String> averageColumn;
    @FXML private TableColumn<Lecture, String> letterGradeColumn;
    @FXML private TableColumn<Lecture, String> statusColumn;

    public void initialize(){

        lectureCodeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureCode"));
        lectureNameColumn.setCellValueFactory(new PropertyValueFactory<>("lectureName"));
        lectureStatusColumn.setCellValueFactory(new PropertyValueFactory<>("Sonuçlandırıldı"));
        visaGradeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureAKTS"));
        finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureApplication"));
        makeUpGradeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureType"));
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("lectureLang"));
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureClass"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("lectureTheory"));

        loadData();
    }
    public void loadData(){
        Student user = Authentication.currentStudentUser;

        ArrayList<Lecture> lectures = user.getLectures();

        ObservableList<Lecture> observableData = FXCollections.observableArrayList(lectures);
        lectureGradeTable.setItems(observableData);




    }
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }
}

