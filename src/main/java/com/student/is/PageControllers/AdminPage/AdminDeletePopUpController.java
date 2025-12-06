package com.student.is.PageControllers.AdminPage;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AdminDeletePopUpController {

    private Stage dialogStage;
    private boolean status = false; //

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isstatus() {
        return status;
    }

    @FXML
    private void handleOnayla() {
        status = true;
        dialogStage.close(); // Pencereyi kapat
    }

    @FXML
    private void handleIptalEt() {
        status = false;
        dialogStage.close(); // Pencereyi kapat
    }
}

