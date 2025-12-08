package com.student.is.PageControllers.StudentPage;

import com.student.is.ClassStructure.Personal;
import com.student.is.DataManagement.Database;
import com.student.is.PageControllers.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class ScholarController {

    @FXML private TableView<Personal> scholarTable;
    @FXML private TableColumn<Personal, String> nameColumn;
    @FXML private TableColumn<Personal, String> surnameColumn;
    @FXML private TableColumn<Personal, String> titleColumn;
    @FXML private TableColumn<Personal, String> emailColumn;
    @FXML private TableColumn<Personal, String> webColumn;
    @FXML private TableColumn<Personal, String> officehoursColumn;

    public void initialize(){

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        webColumn.setCellValueFactory(new PropertyValueFactory<>("web"));
        officehoursColumn.setCellValueFactory(new PropertyValueFactory<>("officehours"));

        loadScholarData();
    }
    public void loadScholarData(){
        ArrayList<Personal> dataList = Database.personalList;

        ObservableList<Personal> observableData = FXCollections.observableArrayList(dataList);
        scholarTable.setItems(observableData);
    }
    @FXML
    public void BackToMainButtonAction(ActionEvent event) {
        ContentLoader.loadPage("/com/student/is/fxml/StudentDashboard.fxml");
    }
}
