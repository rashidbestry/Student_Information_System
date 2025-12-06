package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Personal;
import com.student.is.DataManagement.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ScholarOperationsController {
    @FXML
    private TableView<Personal> personalTable;
    @FXML private TextField searchField;

    @FXML private TableColumn<Personal, String> colPerId;
    @FXML private TableColumn<Personal, String> colName;
    @FXML private TableColumn<Personal, String> colSurname;
    @FXML private TableColumn<Personal, String> colTitle;
    @FXML private TableColumn<Personal, String> colEmail;
    @FXML private TableColumn<Personal, String> colWeb;
    @FXML private TableColumn<Personal, String> colOfficehours;

    public void initialize() {

        colPerId.setCellValueFactory(new PropertyValueFactory<>("perId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colWeb.setCellValueFactory(new PropertyValueFactory<>("web"));
        colOfficehours.setCellValueFactory(new PropertyValueFactory<>("officehours"));

        loadTable();
    }

    public void loadTable() {
        ObservableList<Personal> list = FXCollections.observableArrayList(Database.personalList);
        personalTable.setItems(list);
    }
    public void searchActionButton() {
        String searchText = searchField.getText();

        if (searchText.isEmpty()) {
            loadTable();
            return;
        }
        try {
            Database.searchInPersonalData(Database.personalList, searchText);
            ArrayList<Personal> scholarList = Database.findedPersonalList;
            ObservableList<Personal> scholar = FXCollections.observableArrayList(scholarList);
            personalTable.setItems(scholar);

        } catch (Exception e) {
            System.err.println("Akademisyen arama işlemi sırasında hata oluştu: ");
        }
    }


    public void deleteScholarActionButton() throws IOException {
        Personal scholar = personalTable.getSelectionModel().getSelectedItem();
        if (scholar == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminDeleteQuestionPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(personalTable.getScene().getWindow());  // onay kutusu ortada açılsın

            AdminDeletePopUpController controller = loader.getController();
            controller.setDialogStage(dialogStage);


            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            if (controller.isstatus()) {
                Database.deleteObject(scholar); // database den sil
                System.out.println("Akademisyen silindi silindi");
                // silme işlemi başarılı
                loadTable();
            }
            else{
                System.out.println("silme iptal");
            }

        } catch (IOException e) {
            System.out.println(e);

        }
    }
    public void changePasswordScholarActionButton() throws IOException {
        Personal scholar = personalTable.getSelectionModel().getSelectedItem();
        if (scholar == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminChangePasswordPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(personalTable.getScene().getWindow());  // onay kutusu ortada açılsın

            AdminChangePasswordController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

        }
        catch (IOException e) {

        }
    }
    public void addScholarActionButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminAddScholarPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED); // İsteğe bağlı
            dialogStage.initModality(Modality.WINDOW_MODAL);
            // Pencere sahibini (ana pencere) belirle
            dialogStage.initOwner(personalTable.getScene().getWindow());

            AddScholarController controller = loader.getController();
            controller.setDialogStage(dialogStage); // Controller'a pencere nesnesini ilet

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            loadTable(); // tabloyu güncelle

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}