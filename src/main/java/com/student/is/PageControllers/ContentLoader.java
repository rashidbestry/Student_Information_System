package com.student.is.PageControllers;


import com.student.is.ClassStructure.*; // Student, Admin, Personal vb. için tüm ClassStructure'ı import edin
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ContentLoader {

    private static AnchorPane currentMainContentPane; //içerik Alanı
    private static Object currentUserSession; //kullanıcı oturumu

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
            System.out.println("Hata: Ana içerik AnchorPane ayarlanmadı! Base Controller'ın initialize() metodunu kontrol edin.");
            return;
        }
        try {
            // 1. FXML Dosyasını Yükle
            FXMLLoader loader = new FXMLLoader(ContentLoader.class.getResource(fxmlAdress));
            Parent newContent = loader.load();

            // Önceki içeriği temizle
            currentMainContentPane.getChildren().clear();
            // Yeni içeriği ekle
            currentMainContentPane.getChildren().add(newContent);

            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);

        } catch (IOException e) {
            System.err.println("Hata! FXML Yolu Yanlış veya Dosya Bulunamadı: ");

        }
    }
}
