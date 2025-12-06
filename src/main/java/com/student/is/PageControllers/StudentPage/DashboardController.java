package com.student.is.PageControllers.StudentPage;

import com.student.is.Authentication.Authentication;
import com.student.is.ClassStructure.Student;
import com.student.is.PageControllers.ContentLoader;
import com.student.is.beans.Transcript;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;

public class DashboardController {
    @FXML private Label nameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label bornDateLabel;
    @FXML private Label facultyLabel;
    @FXML private Label sectionLabel;

    @FXML private Label classLabel;
    @FXML private Label termLabel;
    @FXML private Label agnoLabel;
    @FXML private Label lectureCountLabel;
    @FXML private Label advisorLabel;
    @FXML public Button saveTranscript;


    public void initialize(){
        Object sessionUser = ContentLoader.getCurrentUserSession();
        sessionUser=Authentication.currentStudentUser;

        if(sessionUser instanceof Student){
            Student student = (Student) sessionUser;

            nameLabel.setText(student.getFirstName());
            lastNameLabel.setText(student.getLastName());
            bornDateLabel.setText(student.getBornDate());
            facultyLabel.setText(student.getFaculty());
            sectionLabel.setText(student.getSection());

            classLabel.setText(String.valueOf(student.getClassYear())); //stringe dönüştür
            termLabel.setText("2025-2026 Öğretim Yılı");
            agnoLabel.setText(String.valueOf(student.getGpa())); // stringe dönüştür
            lectureCountLabel.setText(String.valueOf(student.lectures.size()));

            switch (student.getClassYear()){
                case 1 -> advisorLabel.setText("Cengiz Hark");
                case 2 -> advisorLabel.setText("Adnan Fatih Kocamaz");
                case 3 -> advisorLabel.setText("Barış Baykant Alagöz");
                case 4 -> advisorLabel.setText("Taha Burak Özdemir");
            }
            saveTranscript.setOnAction(ActionEvent -> {
                try {
                    Transcript.doTranscript();
                } catch (JRException e) {
                    throw new RuntimeException(e);
                }
                FileChooser fileChooser = new FileChooser();
                String home = System.getProperty("user.home");
                home = home + "/Desktop/";
                fileChooser.setInitialDirectory(new File(home));
                fileChooser.setTitle("Transcript Save");
                fileChooser.setInitialFileName("transcript_"+Authentication.currentStudentUser.stuId+".pdf");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pdf File","*.pdf"));
                File file = fileChooser.showSaveDialog(new Stage());
                copy(file);
            });
        }
    }
    public void copy(File file) {
        File sourceFile = new File("src/main/resources/com/student/is/pdf/transcript.pdf");
        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel targetChannel = new FileOutputStream(file).getChannel()) {
            long transferred = sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
        } catch (IOException e) {
        }
    }

}
