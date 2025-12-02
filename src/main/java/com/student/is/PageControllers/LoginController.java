package com.student.is.PageControllers;

import com.student.is.Authentication.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class  LoginController {
    public TextField studentLogin;
    public TextField studentPassword;
    public TextField studentPasswordVisible;
    public TextField personelLogin;
    public TextField personelPassword;
    public TextField personelPasswordVisible;
    public CheckBox studentCheckBox;
    public CheckBox scholarCheckBox;

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

    public void passwordShowCheck(ActionEvent event) throws IOException {
        if(studentCheckBox.isSelected()){
            studentPassword.setDisable(true);
            studentPassword.setVisible(false);
            studentPasswordVisible.setText(studentPassword.getText());
            //studentPassword=studentPasswordVisible;
        }
        if(!studentCheckBox.isSelected()){
            studentPassword.setDisable(false);
            studentPassword.setVisible(true);
            studentPassword.setText(studentPasswordVisible.getText());
        }
    }
    public void scholarPasswordShowCheck(ActionEvent event) throws IOException {
        if(scholarCheckBox.isSelected()){
            personelPassword.setDisable(true);
            personelPassword.setVisible(false);
            personelPasswordVisible.setText(personelPassword.getText());
            //personelPassword=personelPasswordVisible;
        }
        if(!scholarCheckBox.isSelected()){
            personelPassword.setDisable(false);
            personelPassword.setVisible(true);
            personelPassword.setText(personelPasswordVisible.getText());
        }
    }

    public void studentMainPage(ActionEvent event) throws IOException {
        String login = this.studentLogin.getText();
        String password = this.studentPassword.getText();


        if (Authentication.checkStudentAuth(login,password)) {
            //Object loggedInUser = Authentication.currentUser;
            ContentLoader.setCurrentUserSession(Authentication.currentStudentUser); // kullanıcıyı oturuma kaydet

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/StudentBase.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } else {
            System.out.println("Hatalı öğrenci kullanıcı adı veya şifre!");
            StudentShowErorPopup();
        }

        this.studentLogin.clear();
        this.studentPassword.clear();
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

