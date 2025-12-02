package com.student.is.PageControllers.ScholarPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personal;
import com.student.is.ClassStructure.PersonalAbsenceOperations;
import com.student.is.ClassStructure.Student;
import com.student.is.PageControllers.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;

public class AbsenteeismOperationController {

    @FXML private MenuButton absenceMenuButton;

    @FXML private TableView<PersonalAbsenceOperations> StudentAbsenceOperationTable;
    @FXML private TableColumn<PersonalAbsenceOperations, String> studentNumberColumn;
    @FXML private TableColumn<PersonalAbsenceOperations, String> studentNameColumn;
    @FXML private TableColumn<PersonalAbsenceOperations, String> studentSurnameColumn;
    @FXML private TableColumn<PersonalAbsenceOperations, Integer> teorikAbsenceColumn;
    @FXML private TableColumn<PersonalAbsenceOperations, Integer> praciteAbsenceColumn;
    @FXML private TableColumn<PersonalAbsenceOperations, String> absenceStatusColumn;

    public void initialize(){
        Personal user = Authentication.currentPersonalUser;
        if(user != null){
            List<Lecture> personalLectures = user.getLectures();
            absenceMenuButtonOnAction(personalLectures);
        }

        studentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));
        teorikAbsenceColumn.setCellValueFactory(new PropertyValueFactory<>("teorikAbsence"));
        praciteAbsenceColumn.setCellValueFactory(new PropertyValueFactory<>("praciteAbsence"));
        absenceStatusColumn.setCellValueFactory(new PropertyValueFactory<>("absenceStatus"));

        StudentAbsenceOperationTable.setEditable(true);

        teorikAbsenceColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));
        praciteAbsenceColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));


    }
    public void absenceMenuButtonOnAction(List<Lecture> personalLectures){
        absenceMenuButton.getItems().clear();
       for(Lecture lecture : personalLectures){
           MenuItem menuItem = new MenuItem(lecture.getLectureName());

           menuItem.setOnAction(event -> {
               absenceMenuButton.setText(lecture.getLectureName());
               load(lecture);
           });
           absenceMenuButton.getItems().add(menuItem);
       }
    }
    public void load(Lecture lec){

        ArrayList<Student> student=lec.lectureStudentList;

        String studentNumber;
        String studentName;
        String studentSurname;
        int teorikAbsence;
        int praciteAbsence;
        String absenceStatus;

        ObservableList<PersonalAbsenceOperations> Data = FXCollections.observableArrayList();

        for  (Student stu : student){
            studentNumber=stu.getStuId();
            studentName=stu.getFirstName();
            studentSurname=stu.getLastName();

            String Absence =stu.getStuAbsence().get(lec.lectureCode);
            String[] stuAbseceArray = Absence.split(",");
            teorikAbsence = Integer.parseInt(stuAbseceArray[0]);
            praciteAbsence = Integer.parseInt(stuAbseceArray[1]);

            if((praciteAbsence>=9) || (teorikAbsence>=12)){
                absenceStatus = "Kaldı";
            }
            else{
                absenceStatus="Geçti";
            }
            PersonalAbsenceOperations yeni = new PersonalAbsenceOperations(studentNumber,studentName,studentSurname,teorikAbsence,praciteAbsence,absenceStatus);
            Data.add(yeni);

        }
        StudentAbsenceOperationTable.setItems(Data);

    }


    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/ScholarDashboard.fxml");
    }
}
