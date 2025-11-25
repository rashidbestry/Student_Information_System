package com.student.is.PageControllers;
import com.student.is.Authentication.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    Authentication authentication = new Authentication();
    public TextField login;
    public TextField password;

    @FXML
    public void studentButton(ActionEvent event)throws IOException {
        System.out.println("Öğrenci giriş butonuna tıklandı!");
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/StudentLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void personnelButton(ActionEvent event)throws IOException {
        System.out.println("Akademisyen giris butonuna basıldı");
        Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/PersonelLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void studentMainPage(ActionEvent event)throws IOException {
        String login = this.login.getText();
        String password = this.password.getText();
        Boolean status= authentication.chekAuth(login,password);
        if(status) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/student/is/fxml/StudentMainPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            System.out.println("Hatalı kullanıcı adı veya şifre!!");
        }
        this.login.clear();
        this.password.clear();
    }
}
