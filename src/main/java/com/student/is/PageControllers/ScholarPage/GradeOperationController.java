package com.student.is.PageControllers.ScholarPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personal;
import com.student.is.ClassStructure.PersonalGradeOperations;
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

public class GradeOperationController {

    @FXML private MenuButton lectureMenuButton;

    @FXML private TableView<PersonalGradeOperations> StudentNoteOperationTable;
    @FXML private TableColumn<PersonalGradeOperations, String> studentNumberColumn;
    @FXML private TableColumn<PersonalGradeOperations, String> studentNameColumn;
    @FXML private TableColumn<PersonalGradeOperations, String> studentSurnameColumn;
    @FXML private TableColumn<PersonalGradeOperations, Double> vizeNoteColumn;
    @FXML private TableColumn<PersonalGradeOperations, Double> finalNoteColumn;
    @FXML private TableColumn<PersonalGradeOperations, Double> averageNoteColumn;
    @FXML private TableColumn<PersonalGradeOperations, Double> letterNoteColumn;
    @FXML private TableColumn<PersonalGradeOperations, String> statusColumn;

    public void initialize(){

        Personal user = Authentication.currentPersonalUser;
        if(user != null){
            List<Lecture> personalLectures = user.getLectures();
            lectureMenuButtonOnAction(personalLectures);
        }
        StudentNoteOperationTable.setEditable(true);


        studentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));
        vizeNoteColumn.setCellValueFactory(new PropertyValueFactory<>("vizeNote"));
        finalNoteColumn.setCellValueFactory(new PropertyValueFactory<>("finalNote"));
        averageNoteColumn.setCellValueFactory(new PropertyValueFactory<>("averageNote"));
        letterNoteColumn.setCellValueFactory(new PropertyValueFactory<>("letterNote"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        vizeNoteColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DoubleStringConverter()));
        finalNoteColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DoubleStringConverter()));


    }
    public void lectureMenuButtonOnAction(List<Lecture> lectures) {
        lectureMenuButton.getItems().clear();
        for(Lecture lecture : lectures){
            MenuItem menuItem = new MenuItem(lecture.getLectureName());

            menuItem.setOnAction(event -> {
                lectureMenuButton.setText(lecture.getLectureName());
                load(lecture);
            });
            lectureMenuButton.getItems().add(menuItem);
        }

    }

    public void load(Lecture lec){
        //Lecture lec = user.getLectures().get(0);

        ArrayList<Student> student=lec.lectureStudentList;  // dersin öğrencilerini al
        String studentName;
        String studentNumber;
        String studentSurname;
        double vizeNote;
        double finalNote;
        double averageNote=0.0;
        String status="";
        String letterNote="";

        ObservableList<PersonalGradeOperations> Data = FXCollections.observableArrayList();

        for (Student stu : student){
            studentName=stu.getFirstName();
            studentNumber=stu.getStuId();
            studentSurname=stu.getLastName();

            // ögrencinin referansını tut

            String Notes =stu.getStuNotes().get(lec.lectureCode);
            String[] stuNoteArray = Notes.split(",");
             vizeNote = Double.parseDouble(stuNoteArray[0]);
             finalNote = Double.parseDouble(stuNoteArray[1]);

            averageNote = Math.round((vizeNote*0.4) + (finalNote*0.6));

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
            PersonalGradeOperations yeni = new PersonalGradeOperations(studentNumber, studentName, studentSurname,vizeNote,finalNote,averageNote, letterNote, status);
            Data.add(yeni);
        }
        StudentNoteOperationTable.setItems(Data);


    }
    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/ScholarDashboard.fxml");
    }




}
