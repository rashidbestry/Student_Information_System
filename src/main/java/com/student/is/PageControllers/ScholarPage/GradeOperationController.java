package com.student.is.PageControllers.ScholarPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.Personal;
import com.student.is.ClassStructure.PersonalGradeOperations;
import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import com.student.is.PageControllers.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;

public class GradeOperationController {

    @FXML private MenuButton lectureMenuButton;
    @FXML public TextField searchTextField;
    @FXML public Button searchButton;
    @FXML public CheckBox multiChoice;
    @FXML public TextField cellVize;
    @FXML public TextField cellFinal;
    @FXML public Button applyButton;

    @FXML private TableView<PersonalGradeOperations> StudentNoteOperationTable;
    @FXML private TableColumn<PersonalGradeOperations, String> studentNumberColumn;
    @FXML private TableColumn<PersonalGradeOperations, String> studentNameColumn;
    @FXML private TableColumn<PersonalGradeOperations, String> studentSurnameColumn;
    @FXML private TableColumn<PersonalGradeOperations, Integer> vizeNoteColumn;
    @FXML private TableColumn<PersonalGradeOperations, Integer> finalNoteColumn;
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

        vizeNoteColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));
        finalNoteColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));

        vizeNoteColumn.setOnEditCommit(event -> {
            PersonalGradeOperations satir = event.getRowValue();
            int vize =event.getNewValue();  // girilen degeri al
            satir.setVizeNote(event.getNewValue()); //ve tabloyu güncelle

            Student student =satir.getStudentReferans();
            Lecture lecture = satir.getLectureReferans();

            // yeni not hashMap ini oluşturulur
            String yeniNot = String.format("%d,%d",vize,satir.getFinalNote());
            student.getStuNotes().put(lecture.lectureCode,yeniNot); // öğrencinin o andaki dersinin notunu güncelle

            Database.changeObjectData(student); // student objesini veritabanında güncelle

        });

        finalNoteColumn.setOnEditCommit(event -> {
            PersonalGradeOperations satir = event.getRowValue();
            int Final =event.getNewValue();
            satir.setFinalNote(Final);

            Student student =satir.getStudentReferans();
            Lecture lecture = satir.getLectureReferans();

            String yeniNot = String.format("%d,%d",satir.getVizeNote(),Final); // vize ve finali hashMap yap
            student.getStuNotes().put(lecture.lectureCode,yeniNot); //öğrencinin o andaki dersinin notunu güncelle

            Database.changeObjectData(student); // student objesini veritabanında güncelle

        });

    }
    public void lectureMenuButtonOnAction(List<Lecture> lectures) {
        lectureMenuButton.getItems().clear();
        for(Lecture lecture : lectures){
            MenuItem menuItem = new MenuItem(lecture.getLectureName());

            menuItem.setOnAction(event -> {
                lectureMenuButton.setText(lecture.getLectureName());
                load(lecture,lecture.lectureStudentList);
            });
            lectureMenuButton.getItems().add(menuItem);
        }

    }


    public void load(Lecture lec,ArrayList<Student> student){
        //Lecture lec = user.getLectures().get(0);

        searchButton.setOnAction(event -> {
            Database.searchInStudentData(student,searchTextField.getText());
            ArrayList<Student> stu = SearchButtonAction();
            searchTextField.clear();
            load(lec,stu);
            // her aramada bir önceki öğrenci listesi temizlenmeli

        });

        ObservableList<PersonalGradeOperations> Data = FXCollections.observableArrayList();

        String studentName;
        String studentNumber;
        String studentSurname;
        int vizeNote;
        int finalNote;
        double averageNote=0.0;
        String status="";
        String letterNote="";

        for (Student stu : student){
            studentName=stu.getFirstName();
            studentNumber=stu.getStuId();
            studentSurname=stu.getLastName();

            // ögrencinin referansını tut

            String Notes =stu.getStuNotes().get(lec.lectureCode);
            String[] stuNoteArray = Notes.split(",");
             vizeNote = Integer.parseInt(stuNoteArray[0]);
             finalNote = Integer.parseInt(stuNoteArray[1]);

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
            PersonalGradeOperations yeni = new PersonalGradeOperations(studentNumber, studentName, studentSurname,vizeNote,finalNote,averageNote, letterNote, status,stu,lec);
            Data.add(yeni);
        }
        StudentNoteOperationTable.setItems(Data);

    }

    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/ScholarDashboard.fxml");
    }
    public ArrayList<Student> SearchButtonAction(){
        return Database.findedStudentList;


    }
    public void multiChoiceAction(ActionEvent actionEvent) {
        if (multiChoice.isSelected()){
            cellVize.setEditable(true);
            cellFinal.setEditable(true);
            cellVize.setDisable(false);
            cellFinal.setDisable(false);
            applyButton.setDisable(false);
            StudentNoteOperationTable.getSelectionModel().setSelectionMode(
                    SelectionMode.MULTIPLE
            );
        }
        if (!multiChoice.isSelected()){
            cellVize.setEditable(false);
            cellFinal.setEditable(false);
            cellVize.setDisable(true);
            cellFinal.setDisable(true);
            applyButton.setDisable(true);
            cellVize.setText("");
            cellFinal.setText("");
            StudentNoteOperationTable.getSelectionModel().setSelectionMode(
                    SelectionMode.SINGLE
            );
        }
    }
    public void applyButtonAction(ActionEvent actionEvent) {

        for (PersonalGradeOperations item : StudentNoteOperationTable.getSelectionModel().getSelectedItems()) {
            Student student =item.getStudentReferans();
            Lecture lecture = item.getLectureReferans();
            if (!cellVize.getText().isBlank()){
                item.setVizeNote(Integer.parseInt(cellVize.getText()));
                student.getStuNotes().put(lecture.lectureCode,cellVize.getText() +","+String.valueOf(item.getFinalNote()));
            }
            if (!cellFinal.getText().isBlank()) {
                item.setFinalNote(Integer.parseInt(cellFinal.getText()));
                student.getStuNotes().put(lecture.lectureCode, String.valueOf(item.getVizeNote()+","+cellFinal.getText())); //öğrencinin o andaki dersinin notunu güncelle


            }
            Database.changeObjectData(student); // student objesini veritabanında güncelle

        }

    }

}
