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
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class  LoginController {
    public TextField studentLogin;
    public TextField studentPassword;
    public TextField studentPasswordVisible;
    public TextField personelLogin;
    public TextField personelPassword;
    public TextField personelPasswordVisible;
    public CheckBox studentCheckBox;
    public CheckBox scholarCheckBox;
    private static Locale currentLocale = new Locale("tr", "TR");

    public void loadNewScene(ActionEvent event, String fxmlPath) throws IOException {
        ResourceBundle bundle = ContentLoader.getResourceBundle();
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("messages", new Locale("tr", "TR"));
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void LoginShowErorPopup() throws IOException {
        Stage popupStage =ContentLoader.loadPopupStage("/com/student/is/fxml/LoginErrorPopUp.fxml");
        popupStage.showAndWait();
    }
    public void ScholarShowErorPopup() throws IOException{
        Stage popupStage =ContentLoader.loadPopupStage("/com/student/is/fxml/LoginErrorPopUp.fxml");
        popupStage.showAndWait();
    }
    public void playErrorSound() {
        URL resource = getClass().getResource("/com/student/is/sounds/error.mp3");
        if (resource != null) {
            AudioClip audioClip = new AudioClip(resource.toExternalForm());
            audioClip.play();
        }
        else{
            System.out.println("resource == null");
        }
    }
    @FXML
    public void studentButton(ActionEvent event) throws IOException {
       loadNewScene(event, "/com/student/is/fxml/LoginStudent.fxml");
    }
    public void personnelButton(ActionEvent event) throws IOException {
        loadNewScene(event, "/com/student/is/fxml/LoginPersonel.fxml");
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
    public void studentMainPage(ActionEvent event) throws IOException, JRException {
        String login = this.studentLogin.getText();
        String password = this.studentPassword.getText();

        if (Authentication.checkStudentAuth(login,password)) {
            //Object loggedInUser = Authentication.currentUser;
            ContentLoader.setCurrentUserSession(Authentication.currentStudentUser); // kullanıcıyı oturuma kaydet

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/StudentBase.fxml"),ContentLoader.getResourceBundle());
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }
        else if (login.equals("admin") && password.equals("admin")) {
            ContentLoader.setCurrentUserSession(Authentication.currentAdminUser);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminBase.fxml"),ContentLoader.getResourceBundle());
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            System.out.println("Hatalı öğrenci kullanıcı adı veya şifre!");
            playErrorSound();
            LoginShowErorPopup();
        }

        this.studentLogin.clear();
        this.studentPassword.clear();
    }
    public void personelMainPage(ActionEvent event) throws IOException {
        String login = this.personelLogin.getText();
        String password = this.personelPassword.getText();
        if (Authentication.checkPersonalAuth(login, password)) {
            ContentLoader.setCurrentUserSession(Authentication.currentPersonalUser);

            Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/ScholarBase.fxml"),ContentLoader.getResourceBundle());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (login.equals("admin") && password.equals("admin")) {
            ContentLoader.setCurrentUserSession(Authentication.currentAdminUser);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminBase.fxml"),ContentLoader.getResourceBundle());
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            System.out.println("Hatalı kullanıcı adı veya şifre!!");
            playErrorSound();
            LoginShowErorPopup();
        }
        this.personelLogin.clear();
        this.personelPassword.clear();
    }
    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        loadNewScene(event, "/com/student/is/fxml/Welcome.fxml");
    }
    @FXML
    public void forgotPasswordButtonAction(ActionEvent event) throws IOException,IOException{
       loadNewScene(event, "/com/student/is/fxml/ForgotPassword.fxml");
    }
    @FXML
    public void changeLanguageButtonAction(ActionEvent event) throws IOException {
        if (currentLocale.getLanguage().equals("tr")) {
            currentLocale = new Locale("en", "US");
        } else {
            currentLocale = new Locale("tr", "TR");
        }
        ContentLoader.initializeLanguage(currentLocale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/Welcome.fxml"), ContentLoader.getResourceBundle());
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

