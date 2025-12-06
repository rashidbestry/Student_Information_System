package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Personal;
import com.student.is.DataManagement.Database;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class AddScholarController {
    private Stage dialogStage;

    @FXML
    private TextField academicianNumberField;
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField titleField; // Ünvan
    @FXML private TextField websiteField;
    @FXML private TextField emailField;
    @FXML private TextField officeHoursField;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void addAcademicianActionButton() {
        if (academicianNumberField.getText().isEmpty() || nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            System.out.println("Lütfen zorunlu alanları doldurun!");
            return;
        }

        try {
            String academicianNumber = academicianNumberField.getText();
            String name = nameField.getText();
            String surname = surnameField.getText();
            String title = titleField.getText();
            String website = websiteField.getText();
            String email = emailField.getText();
            String officeHours = officeHoursField.getText();

            Personal newScholar = new Personal(academicianNumber, name, surname, title, website, email, officeHours);

            Database.personalList.add(newScholar);//Database kaydetme işlemi

            System.out.println("Akademisyen başarıyla eklendi.");
            dialogStage.close();

        } catch (Exception e) {
            System.err.println("Akademisyen ekleme işlemi sırasında hata oluştu: " + e);

        }
    }
    @FXML
    private void cancelActionButton() {
        dialogStage.close();
    }
}
