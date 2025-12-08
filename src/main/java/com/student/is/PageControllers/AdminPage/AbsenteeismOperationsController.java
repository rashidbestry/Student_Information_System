package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Lecture;
import com.student.is.ClassStructure.PersonalAbsenceOperations;
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

public class AbsenteeismOperationsController {

    @FXML private MenuButton AbsenceMenuButton;
    @FXML public TextField searchTextField;
    @FXML public Button searchButton;
    @FXML public CheckBox multiChoice;
    @FXML public TextField cellTheory;
    @FXML public TextField cellApplication;
    @FXML public Button applyButton;
    @FXML
    private TableView<PersonalAbsenceOperations> AbsenceTable;
    @FXML private TableColumn<PersonalAbsenceOperations, String> colStudentNumber;
    @FXML private TableColumn<PersonalAbsenceOperations, String> colStudentName;
    @FXML private TableColumn<PersonalAbsenceOperations, String> colStudentSurname;
    @FXML private TableColumn<PersonalAbsenceOperations, Integer> colTeory;
    @FXML private TableColumn<PersonalAbsenceOperations, Integer> colPratic;
    @FXML private TableColumn<PersonalAbsenceOperations, Double> colStatus;

    public void initialize(){
        AbsenceMenuButtonOnAction(Database.lectureList);

        colStudentNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colStudentSurname.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));
        colTeory.setCellValueFactory(new PropertyValueFactory<>("teorikAbsence"));
        colPratic.setCellValueFactory(new PropertyValueFactory<>("praciteAbsence"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("absenceStatus"));

        AbsenceTable.setEditable(true);

        colTeory.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));
        colPratic.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));


        colTeory.setOnEditCommit(event -> {
            PersonalAbsenceOperations satir = event.getRowValue();
            int theory =event.getNewValue();  // girilen degeri al
            satir.setTeorikAbsence(event.getNewValue()); //ve tabloyu güncelle

            Student student =satir.getStudentReferans();
            Lecture lecture = satir.getLectureReferans();

            // yeni not hashMap ini oluşturulur
            String yeniNot = theory+","+satir.getPraciteAbsence();
            student.getStuAbsence().put(lecture.lectureCode,yeniNot); // öğrencinin o andaki dersinin notunu güncelle
            Database.changeObjectData(student); // student objesini veritabanında güncelle
        });

        colPratic.setOnEditCommit(event -> {
            PersonalAbsenceOperations satir = event.getRowValue();
            int application =event.getNewValue();
            satir.setPraciteAbsence(application);

            Student student =satir.getStudentReferans();
            Lecture lecture = satir.getLectureReferans();

            String yeniNot = satir.getTeorikAbsence() +","+application; // vize ve finali hashMap yap
            student.getStuAbsence().put(lecture.lectureCode,yeniNot); //öğrencinin o andaki dersinin notunu güncelle
            Database.changeObjectData(student); // student objesini veritabanında güncelle
        });

    }
    public void loadTable(Lecture lec, ArrayList<Student> student){
        ObservableList<PersonalAbsenceOperations> Data = FXCollections.observableArrayList();

        searchButton.setOnAction(event -> {
            Database.searchInStudentData(student,searchTextField.getText());
            ArrayList<Student> stu = SearchStudentButtonAction();
            searchTextField.clear();
            loadTable(lec,stu);
            stu.clear();  // her aramada bir önceki öğrenci listesi temizlenmeli

        });

        String studentNumber;
        String studentName;
        String studentSurname;
        int teorikAbsence;
        int praciteAbsence;
        String absenceStatus;

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
            PersonalAbsenceOperations yeni = new PersonalAbsenceOperations(studentNumber,studentName,studentSurname,teorikAbsence,praciteAbsence,absenceStatus,stu,lec);
            Data.add(yeni);
        }
        AbsenceTable.setItems(Data);
    }
    public void AbsenceMenuButtonOnAction(List<Lecture> lectures) {
        AbsenceMenuButton.getItems().clear();
        for(Lecture lecture : lectures){
            MenuItem menuItem = new MenuItem(lecture.getLectureName());

            menuItem.setOnAction(event -> {
                AbsenceMenuButton.setText(lecture.getLectureName());
                loadTable(lecture,lecture.lectureStudentList);
            });
            AbsenceMenuButton.getItems().add(menuItem);
        }
    }
    @FXML
    public ArrayList<Student> SearchStudentButtonAction(){
        return Database.findedStudentList;
    }
    public void multiChoiceAction(ActionEvent actionEvent) {
        if (multiChoice.isSelected()){
            cellTheory.setEditable(true);
            cellApplication.setEditable(true);
            cellTheory.setDisable(false);
            cellApplication.setDisable(false);
            applyButton.setDisable(false);
            AbsenceTable.getSelectionModel().setSelectionMode(
                    SelectionMode.MULTIPLE
            );
        }
        if (!multiChoice.isSelected()){
            cellTheory.setEditable(false);
            cellApplication.setEditable(false);
            cellTheory.setDisable(true);
            cellApplication.setDisable(true);
            applyButton.setDisable(true);
            cellTheory.setText("");
            cellApplication.setText("");
            AbsenceTable.getSelectionModel().setSelectionMode(
                    SelectionMode.SINGLE
            );
        }
    }
    public void applyButtonAction(ActionEvent actionEvent) {
        for (PersonalAbsenceOperations item : AbsenceTable.getSelectionModel().getSelectedItems()) {
            Student student =item.getStudentReferans();
            Lecture lecture = item.getLectureReferans();
            if (!cellTheory.getText().isBlank()){
                item.setTeorikAbsence(Integer.parseInt(cellTheory.getText()));
                student.getStuAbsence().put(lecture.lectureCode,cellTheory.getText() +","+String.valueOf(item.getPraciteAbsence()));

            }
            if (!cellApplication.getText().isBlank()) {
                item.setPraciteAbsence(Integer.parseInt(cellApplication.getText()));
                student.getStuAbsence().put(lecture.lectureCode, String.valueOf(item.getTeorikAbsence()+","+cellApplication.getText())); //öğrencinin o andaki dersinin notunu güncelle

            }
            Database.changeObjectData(student); // student objesini veritabanında güncelle
        }
    }
}
