package com.student.is.PageControllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErorPopupController {

    @FXML
    private Label closeButton; // FXML'deki 'X' etiketi

    @FXML
    public void initialize() {
        // 'X' label
        closeButton.setOnMouseClicked(event -> {
            // Etiketin ait olduğu sahneyi/pencereyi bul ve kapat
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });
    }
}
