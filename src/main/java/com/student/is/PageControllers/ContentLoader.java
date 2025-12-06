package com.student.is.PageControllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ContentLoader {

    private static AnchorPane currentMainContentPane; //içerik Alanı
    private static Object currentUserSession; //kullanıcı oturumu

    private static final String BUNDLE_NAME = "com.student.is.language.messages";  //güncel bundle ismi
    public static ResourceBundle resourceBundle;

    public static void initializeLanguage(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }
    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void setMainContentPane(AnchorPane pane) {
        currentMainContentPane = pane;
    }
    public static void setCurrentUserSession(Object user) {  //kullanıcıyı oluşturur
        currentUserSession = user;
    }
    public static Object getCurrentUserSession() {  //kullanıcıyı çeker
        return currentUserSession;
    }

    public static void loadPage(String fxmlAdress) {
        if (currentMainContentPane == null) {
            System.out.println("Hata: Ana içerik AnchorPane ayarlanmadı!");
            return;
        }

        try {
            java.net.URL fxmlUrl = ContentLoader.class.getResource(fxmlAdress);

            if (fxmlUrl == null) {
                System.out.println("Hata: fxml url null -> " + fxmlAdress);
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);

            if (resourceBundle != null) {
                loader.setResources(resourceBundle);
            }

            Parent newContent = loader.load();

            currentMainContentPane.getChildren().clear();
            currentMainContentPane.getChildren().add(newContent);

            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);

        } catch (IOException e) {
            System.err.println("Hata! FXML yüklenemedi: " + e.getMessage());
        }
    }
    public static Stage loadPopupStage(String fxmlPath) throws IOException {
        ResourceBundle bundle = ContentLoader.getResourceBundle();
        if (bundle == null) {
            // Hata durumunda varsayılan dili kullan
            bundle = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("tr", "TR"));
        }

        // FXML yükleyiciyi oluştur ve ResourceBundle'ı ayarla
        FXMLLoader fxmlLoader = new FXMLLoader(ContentLoader.class.getResource(fxmlPath), bundle);
        Parent root = fxmlLoader.load();

        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));

        return popupStage;
    }
}
