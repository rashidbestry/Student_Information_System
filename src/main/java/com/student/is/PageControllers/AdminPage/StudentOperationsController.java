package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.ArrayList;

public class StudentOperationsController {
    @FXML
    private TableView<Student> studentTable;
    @FXML private TextField searchField;
    @FXML
    private TableColumn<Student, String> studentNumberColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> surnameColumn;
    @FXML private TableColumn<Student, String> birthDateColumn;
    @FXML private TableColumn<Student, Integer> studentClassColumn;
    @FXML private TableColumn<Student, String> facultyColumn;
    @FXML private TableColumn<Student, String> phoneNumberColumn;
    @FXML private TableColumn<Student, String> emailColumn;


    public void initialize() {

        studentTable.setEditable(true);
        // 1) Kolonları Student modeline bağlama
        studentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("bornDate"));
        studentClassColumn.setCellValueFactory(new PropertyValueFactory<>("classYear"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        studentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        studentNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DefaultStringConverter()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DefaultStringConverter()));
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DefaultStringConverter()));
        birthDateColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DefaultStringConverter()));
        studentClassColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.IntegerStringConverter()));
        facultyColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DefaultStringConverter()));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DefaultStringConverter()));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn( new javafx.util.converter.DefaultStringConverter()));



        loadStudentTable();
    }
    private void loadStudentTable() {
        ObservableList<Student> stu = FXCollections.observableArrayList(Database.studentList);
        studentTable.setItems(stu);
    }
    public void searchActionButton() {
        String searchText = searchField.getText();

        if (searchText.isEmpty()) {
            loadStudentTable();
            return;
        }
        try {
            Database.searchInStudentData(Database.studentList, searchText);
            ArrayList<Student> studentList = Database.findedStudentList;
            ObservableList<Student> stu = FXCollections.observableArrayList(studentList);
            studentTable.setItems(stu);

        } catch (Exception e) {
            System.err.println("Öğrenci arama işlemi sırasında hata oluştu: ");
        }
    }
    public void changePasswordActionButton() throws IOException {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminChangePasswordPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();

            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(studentTable.getScene().getWindow());  // onay kutusu ortada açılsın

            AdminChangePasswordController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            String st =selectedStudent.getStuId()+"@ogr.inonu.edu.tr";
            controller.setEmail(st);

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();



        } catch (IOException e) {
            System.out.println(e);

        }

    }
    public void deleteStudentActionButton() throws IOException {
        ObservableList<Student> seciliOgrenci = studentTable.getSelectionModel().getSelectedItems();
        if (seciliOgrenci == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminDeleteQuestionPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(studentTable.getScene().getWindow());  // onay kutusu ortada açılsın

            AdminDeletePopUpController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            if (controller.isstatus()) {
                for (Student stu : seciliOgrenci){
                    Database.deleteObject(stu); // database den sil  ve RAM dende silmeli
                }
                System.out.println("Öğrenci silindi");
                // silme işlemi başarılı
                loadStudentTable();
            }
            else{
                System.out.println("silme iptal");
            }

        } catch (IOException e) {
            System.out.println(e);

        }
    }
    public void addStudentActionButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminAddStudentPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            // Ana pencerenin ortasında açılması için
            dialogStage.initOwner(studentTable.getScene().getWindow());

            AddStudentController controller = loader.getController();
            controller.setDialogStage(dialogStage); // Controller'a pencere nesnesini ilet

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();


            loadStudentTable(); //tabloyu güncelle

        } catch (IOException e) {
            System.err.println("Öğrenci Ekle pop-up'ı açılırken hata oluştu: " + e);
            e.printStackTrace();
        }
    }

}