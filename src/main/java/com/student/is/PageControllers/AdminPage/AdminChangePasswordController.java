package com.student.is.PageControllers.AdminPage;

import com.student.is.Authentication.Authentication;
import com.student.is.PageControllers.ContentLoader;
import com.student.is.PageControllers.AdminPage.StudentOperationsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminChangePasswordController {

    @FXML private TextField emailField ;
    @FXML private TextField passwordField;


    private Stage dialogStage; // Pop-up penceresini kapatmak için

    // Ana Controller'dan çağrılır
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void changePassword() throws IOException {
        Stage popupStage= ContentLoader.loadPopupStage("/com/student/is/fxml/AdminChangePasswordSuccessfulPopUp.fxml");
        popupStage.showAndWait();
    }

    @FXML
    private void handleChangePassword() throws IOException {
        String email = emailField.getText();
        String newPassword = passwordField.getText();

        if (newPassword.isEmpty()) {
            return;
        }
        boolean success = Authentication.changePassword(email, newPassword);
        if (success) {
             dialogStage.close();
             System.out.println("Şifre değiştirildi");
             // başarılı popUp çıkar
             changePassword();

        } else {
            System.out.println("Şifre değiştirilmedi");
              //Hata mesajı göster
        }
    }
    @FXML
    private void handleCancel() {
        if (dialogStage != null) {
            dialogStage.close();
        }
    }

}
