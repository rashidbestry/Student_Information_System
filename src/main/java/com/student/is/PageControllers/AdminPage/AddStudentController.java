package com.student.is.PageControllers.AdminPage;
import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void addStudentActionButton() {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            System.out.println("Lütfen zorunlu alanları doldurun!");
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

            Student newStudent = new Student(studentNumber, name, surname, birthDate, classYear, faculty, phone, email);

            //DtaBase
            Database.studentList.add(newStudent); //database ekle
            dialogStage.close();

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
