package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Lecture;
import com.student.is.DataManagement.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
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

public class LectureOperationsController {

    @FXML private TextField searchField;

    @FXML
    private TableView<Lecture> LectureOperationTable;
    @FXML private TableColumn<Lecture, String> LectureCodeColumn;
    @FXML private TableColumn<Lecture, String> LectureNameColumn;
    @FXML private TableColumn<Lecture, String> LectureCreditColumn;
    @FXML private TableColumn<Lecture, String> LectureAktsColumn;
    @FXML private TableColumn<Lecture, String> LectureClassColumn;
    @FXML private TableColumn<Lecture, String> LectureTeorikColumn;
    @FXML private TableColumn<Lecture, String> LecturePraticColumn;
    @FXML private TableColumn<Lecture, String> LectureSeasonColumn;



    public void initialize(){

        LectureCodeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureCode"));
        LectureNameColumn.setCellValueFactory(new PropertyValueFactory<>("lectureName"));
        LectureCreditColumn.setCellValueFactory(new PropertyValueFactory<>("lectureCredit"));
        LectureAktsColumn.setCellValueFactory(new PropertyValueFactory<>("lectureAKTS"));
        LectureClassColumn.setCellValueFactory(new PropertyValueFactory<>("lectureClass"));
        LectureTeorikColumn.setCellValueFactory(new PropertyValueFactory<>("lectureTheory"));
        LecturePraticColumn.setCellValueFactory(new PropertyValueFactory<>("lectureApplication"));
        LectureSeasonColumn.setCellValueFactory(new PropertyValueFactory<>("lectureSeason"));

        LectureOperationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        loadLectureTable();
    }

    public void loadLectureTable(){
        ObservableList<Lecture> list = FXCollections.observableArrayList(Database.lectureList);
        LectureOperationTable.setItems(list);
    }

    public void searchActionButton() {
        String searchText = searchField.getText();

        if (searchText.isEmpty()) {
            loadLectureTable();
            return;
        }
        try {
            Database.searchInLectureData(Database.lectureList, searchText);
            ArrayList<Lecture> lectureList = Database.findedLectureList;
            ObservableList<Lecture> lec = FXCollections.observableArrayList(lectureList);
            LectureOperationTable.setItems(lec);

        } catch (Exception e) {
            System.err.println("Ders arama işlemi sırasında hata oluştu: ");
        }
    }



    public void deleteLectureActionButton() throws IOException {
        ObservableList<Lecture> lec = LectureOperationTable.getSelectionModel().getSelectedItems();
        if (lec == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminDeleteQuestionPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(LectureOperationTable.getScene().getWindow());  // onay kutusu ortada açılsın

            AdminDeletePopUpController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            if (controller.isstatus()) {
                for (Lecture lecture : lec){
                    Database.deleteObject(lecture); // database den sil

                }
                System.out.println("ders silindi");
                // silme işlemi başarılı
                loadLectureTable();
            }
            else{
                System.out.println("silme iptal");
            }

        } catch (IOException e) {
            System.out.println(e);

        }
    }
    public void addLectureActionButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminAddLecturePopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED); //popup ayarla
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(LectureOperationTable.getScene().getWindow());

            AddLectureController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            loadLectureTable(); // Ders tablosunu güncelle

        } catch (IOException e) {
            System.err.println("Ders Ekle pop-up'ı açılırken hata oluştu: " );
            e.printStackTrace();
        }
    }

}
