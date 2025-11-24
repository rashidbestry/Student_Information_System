package com.example.obs.PageControllers;

import com.example.obs.Authentication.SifreConroller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    SifreConroller sifreConroller = new SifreConroller();
    @FXML
    private Button ogrenci_giris;  // fx:id ile bağladık

    // onAction metodu (ismi tam olarak FXML ile aynı olmalı)
    @FXML
    public void ogrenci_giris_button(ActionEvent event)throws IOException {
        System.out.println("Öğrenci giriş butonuna tıklandı!");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/obs/fxml/OgrenciGiris.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void akademisyen_giris_button(ActionEvent event)throws IOException {
        System.out.println("akademisyen giris butonuna basıldı");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/obs/fxml/AkademisyenGiris.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public TextField ogrenci_numarasi;
    public TextField ogrenci_sifre;

    public void ogrenci_anasayfa(ActionEvent event)throws IOException {
        String numarasi = ogrenci_numarasi.getText();
        String sifre = ogrenci_sifre.getText();
        Boolean kontrol=sifreConroller.sifre_kontrol_ogrenci(numarasi,sifre);
        if(kontrol==true) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/obs/fxml/OgrenciAnaSayfa.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        else{
            System.out.println("Hatalı kullanıcı adı veya şifre!!");
        }
        ogrenci_numarasi.clear();
        ogrenci_sifre.clear();

    }
    public void sifremi_unuttum(ActionEvent event)throws IOException {

    }


}
