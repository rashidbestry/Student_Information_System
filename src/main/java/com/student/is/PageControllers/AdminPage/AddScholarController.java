package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Personal;
import com.student.is.ClassStructure.Student;
import com.student.is.DataManagement.Database;
import com.student.is.PageControllers.ContentLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

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


    public void initialize (){
        String academicianNumber =Database.getNewPersonalId();
        academicianNumberField.setText(academicianNumber);
    }
    @FXML
    public void AddErorPopup() throws IOException {
        Stage popupStage = ContentLoader.loadPopupStage("/com/student/is/fxml/AdminAddErrorPopUp.fxml");
        popupStage.showAndWait();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void addAcademicianActionButton() throws IOException {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            System.out.println("Lütfen zorunlu alanları doldurun!");
            AddErorPopup();
            return;
        }

        try {
            String academicianNumber = Database.getNewPersonalId();
            String name = nameField.getText();
            String surname = surnameField.getText();
            String title = titleField.getText();
            String website = websiteField.getText();
            String email = emailField.getText();
            String officeHours = officeHoursField.getText();
            ArrayList<String> liste = new ArrayList<>();
            String[] titleList = {"Prof","Dr","PhD","Dç","ARGE","Ögr"};
            for (String str : titleList) liste.add(str);


            if ((name instanceof String) || (surname instanceof String) || liste.contains(title) || (website.contains("http://")) || (email.contains("@")) || (officeHours.isEmpty())){
                if((name.length()<=25) || (name.length()>=2) || (surname.length()<=25) || (surname.length()>=2) || (website.length()>10)  || (email.length()<=50)) {
                    Personal newScholar = new Personal(academicianNumber, name, surname, title, website, email, officeHours);
                    Database.personalList.add(newScholar);//Database kaydetme işlemi
                    dialogStage.close();
                }

            }
            else{
                nameField.clear();
                surnameField.clear();
                titleField.clear();
                websiteField.clear();
                officeHoursField.clear();
                emailField.clear();

            }

        } catch (Exception e) {
            System.err.println("Akademisyen ekleme işlemi sırasında hata oluştu: " + e);

        }
    }
    @FXML
    private void cancelActionButton() {
        dialogStage.close();
    }
}
