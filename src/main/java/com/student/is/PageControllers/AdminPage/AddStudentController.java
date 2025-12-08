package com.student.is.PageControllers.AdminPage;
import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import com.student.is.PageControllers.ContentLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddStudentController {

    @FXML private TextField studentNumberField;
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField birthDateField;
    @FXML private TextField classField;
    @FXML private TextField facultyField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    private Stage dialogStage;

    public void initialize() {
        String studentNumber = Database.getNewStudentId();
        studentNumberField.setText(studentNumber);
        System.out.println(studentNumber);

    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void AddErorPopup() throws IOException {
        Stage popupStage =ContentLoader.loadPopupStage("/com/student/is/fxml/AdminAddErrorPopUp.fxml");
        popupStage.showAndWait();
    }

    @FXML
    private void addStudentActionButton() throws IOException {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            System.out.println("Lütfen zorunlu alanları doldurun!");
            AddErorPopup();
            return;

        }

        try {
            String studentNumber = Database.getNewStudentId();
            String name = nameField.getText();
            String surname = surnameField.getText();
            String birthDate = birthDateField.getText();
            int classYear = Integer.parseInt(classField.getText());
            String faculty = facultyField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            boolean status = true;
            for(Character chr: name.toCharArray()) {
                if(!Character.isAlphabetic(chr))
                    status = false;

            }
            if (status || (surname instanceof String) || (birthDate instanceof String) || (faculty instanceof String) || (phone instanceof String) || (email instanceof String)){
                if((name.length()<=25) || (name.length()>=2) || (surname.length()<=25) || (surname.length()>=2) || (birthDate.length()==10) || (classYear>=1) || (classYear<=6) || (faculty.contains("Fakültesi")) || (phone.length()==13) || (email.length()<=50) || (email.contains("@"))){
                    Student newStudent = new Student(studentNumber, name, surname, birthDate, classYear, faculty, phone, email);
                    Database.studentList.add(newStudent); //database ekle
                    dialogStage.close();
                }

            }
            else{
                nameField.clear();
                surnameField.clear();
                birthDateField.clear();
                classField.clear();
                facultyField.clear();
                phoneField.clear();
                emailField.clear();

            }
            //Student newStudent = new Student(studentNumber, name, surname, birthDate, classYear, faculty, phone, email);

            //DtaBase
            /*Database.studentList.add(newStudent); //database ekle
            dialogStage.close();*/

        } catch (Exception e) {
            System.out.println("Öğrenci ekleme işlemi sırasında hata oluştu: " );
        }

    }
    @FXML
    private void cancelActionButton() {
        // İptal butonuna basılınca pencereyi kapat
        dialogStage.close();
    }
}
