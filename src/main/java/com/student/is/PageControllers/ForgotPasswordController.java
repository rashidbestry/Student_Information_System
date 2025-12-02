package com.student.is.PageControllers;

import com.student.is.Authentication.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class ForgotPasswordController {

    public void BackButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/Welcome.fxml"));
            Parent root = loader.load();
            Scene oncekiSahne = new Scene(root);

            Node source = (Node) actionEvent.getSource();
            Stage mevcutStage = (Stage) source.getScene().getWindow();

            mevcutStage.setScene(oncekiSahne);
            mevcutStage.show();

        } catch (IOException e) {
            System.err.println("FXML dosyası yüklenirken bir hata oluştu:");
        }

    }
    public void SendMailShowFailedPopup() throws IOException {
        //  FXML dosyasını yükle
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/LoginErrorPopUp.fxml"));
        Parent root = fxmlLoader.load();
        Stage popupStage = new Stage();

        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        popupStage.showAndWait();
    }
    public void SendMailShowSuccessfulPopup() throws IOException {
        //  FXML dosyasını yükle
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/SendMailSuccessfulPopUp.fxml"));
        Parent root = fxmlLoader.load();
        Stage popupStage = new Stage();

        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        popupStage.showAndWait();
    }
    public TextField mailAdress;
    public void SendEmailButtonAction(ActionEvent actionEvent) throws IOException {
        String mail = this.mailAdress.getText();
        // Authentication.forgotPassword(mail) "mail adresi kontrol edilecek ve mail gönderilecek"
        if(Authentication.forgotPassword(mail)){
            SendMailShowSuccessfulPopup();
        }
        else{
            SendMailShowFailedPopup();
        }

    }

}
