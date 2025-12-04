package com.student.is;

import com.student.is.DataManagement.Database;
import com.student.is.PageControllers.StartController;
import javafx.application.Application;
import java.io.IOException;

public class Launcher {
    static void main(String[] args) throws IOException {
        Application.launch(StartController.class, args);
        Database.saveTempToData();
        Database.deleteTemp();
    }
}

