package com.student.is.PageControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErorPopupController {

    @FXML
    private Button closeButton;

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
