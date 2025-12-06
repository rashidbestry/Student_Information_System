package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.PersonalGradeOperations;
import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;

public class GradeOperationsController {
    @FXML private MenuButton lectureMenuButton;
    @FXML public TextField searchTextField;
    @FXML public Button searchButton;
    @FXML public CheckBox multiChoice;
    @FXML public Button applyButton;
    @FXML public TextField cellVize;
    @FXML public TextField cellFinal;

    @FXML
    private TableView<PersonalGradeOperations> noteTable;

    @FXML private TableColumn<PersonalGradeOperations, String> colStudentNumber;
    @FXML private TableColumn<PersonalGradeOperations, String> colStudentName;
    @FXML private TableColumn<PersonalGradeOperations, String> colStudentSurname;
    @FXML private TableColumn<PersonalGradeOperations, Integer> colVize;
    @FXML private TableColumn<PersonalGradeOperations, Integer> colFinal;
    @FXML private TableColumn<PersonalGradeOperations, Double> colAverage;
    @FXML private TableColumn<PersonalGradeOperations, String> colLetter;
    @FXML private TableColumn<PersonalGradeOperations, String> colStatus;


    public void initialize() {

        lectureMenuButtonOnAction(Database.lectureList);

        noteTable.setEditable(true);
        colStudentNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colStudentSurname.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));
        colVize.setCellValueFactory(new PropertyValueFactory<>("vizeNote"));
        colFinal.setCellValueFactory(new PropertyValueFactory<>("finalNote"));
        colAverage.setCellValueFactory(new PropertyValueFactory<>("averageNote"));
        colLetter.setCellValueFactory(new PropertyValueFactory<>("letterNote"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colVize.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));
        colFinal.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));


        colVize.setOnEditCommit(event -> {
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

        colFinal.setOnEditCommit(event -> {
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

    public void loadTable(Lecture lec, ArrayList<Student> student) {
        ObservableList<PersonalGradeOperations> Data = FXCollections.observableArrayList();

        searchButton.setOnAction(event -> {
            Database.searchInStudentData(student,searchTextField.getText());
            ArrayList<Student> stu = SearchButtonAction();
            searchTextField.clear();
            loadTable(lec,stu);
            // her aramada bir önceki öğrenci listesi temizlenmeli

        });

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
        noteTable.setItems(Data);

    }
    public void lectureMenuButtonOnAction(List<Lecture> lectures) {
        lectureMenuButton.getItems().clear();
        for(Lecture lecture : lectures){
            MenuItem menuItem = new MenuItem(lecture.getLectureName());

            menuItem.setOnAction(event -> {
                lectureMenuButton.setText(lecture.getLectureName());
                loadTable(lecture,lecture.lectureStudentList);
            });
            lectureMenuButton.getItems().add(menuItem);
        }

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
            noteTable.getSelectionModel().setSelectionMode(
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
            noteTable.getSelectionModel().setSelectionMode(
                    SelectionMode.SINGLE
            );
        }
    }
    public void applyButtonAction(ActionEvent actionEvent) {

        for (PersonalGradeOperations item : noteTable.getSelectionModel().getSelectedItems()) {
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

