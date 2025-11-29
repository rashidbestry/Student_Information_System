package com.student.is.PageControllers;

import com.student.is.Authentication.Authentication;
import com.student.is.DataManagement.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class  LoginController {
    public TextField studentLogin;
    public TextField studentPassword;
    public TextField personelLogin;
    public TextField personelPassword;
    //private MediaPlayer errorPlayer;

    public void StudentShowErorPopup() throws IOException {
        // 1. FXML dosyasını yükle
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/StudentWrongPasswordPopUp.fxml"));
        Parent root = fxmlLoader.load();
        Stage popupStage = new Stage();

        popupStage.initStyle(StageStyle.UNDECORATED);//pencere ayarlarını ayarla
        popupStage.initModality(Modality.APPLICATION_MODAL); // popUp kapatılmadan diger işlemler yapılamaz
        popupStage.setScene(new Scene(root));

        popupStage.showAndWait();
    }

    public void ScholarShowErorPopup() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/ScholarWrongPasswordPopUp.fxml"));
        Parent root = fxmlLoader.load();

        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED); //pencere ayarlarını ayarla
        popupStage.initModality(Modality.APPLICATION_MODAL); // popUp kapatılmadan diger işlemler yapılamaz
        popupStage.setScene(new Scene(root));

        popupStage.showAndWait();
    }
    /*
    private void playErrorSound() {
        URL resource = getClass().getResource("/sounds/error-126627.wav");
        if (resource != null) {
            AudioClip audioClip = new AudioClip(resource.toExternalForm());
            audioClip.play();
        }
    }
    */

    @FXML
    public void studentButton(ActionEvent event) throws IOException {
        System.out.println("Öğrenci giriş butonuna tıklandı!");
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/LoginStudent.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void personnelButton(ActionEvent event) throws IOException {
        System.out.println("Akademisyen giris butonuna basıldı");
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/LoginPersonel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void studentMainPage(ActionEvent event) throws IOException {
        String login = this.studentLogin.getText();
        String password = this.studentPassword.getText();
        Boolean status = Authentication.checkStudentAuth(login, password);
        if (status) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/StudentBase.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Hatalı kullanıcı adı veya şifre!!");
            StudentShowErorPopup();
            //playErrorSound();
        }

        this.studentLogin.clear();
        this.studentPassword.clear();
        Database.createTemp();
    }



    public void personelMainPage(ActionEvent event) throws IOException {
        String login = this.personelLogin.getText();
        String password = this.personelPassword.getText();
        Boolean status = Authentication.checkPersonalAuth(login, password);
        if (status) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/ScholarBase.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Hatalı kullanıcı adı veya şifre!!");
            ScholarShowErorPopup();
            //playErrorSound();
        }
        this.personelLogin.clear();
        this.personelPassword.clear();
    }

    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/Welcome.fxml"));
        Parent root = loader.load();
        Scene oncekiSahne = new Scene(root);

        Node source = (Node) event.getSource();
        Stage mevcutStage = (Stage) source.getScene().getWindow();

        mevcutStage.setScene(oncekiSahne);
        mevcutStage.show();

    }
    public void forgotPasswordButtonAction(ActionEvent event) throws IOException,IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/ForgotPassword.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}