package com.student.is.PageControllers.AdminPage;

import com.student.is.ClassStructure.Lecture;
import com.student.is.DataManagement.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LectureOperationsController {


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

        loadLectureTable();
    }

    public void loadLectureTable(){
        ObservableList<Lecture> list = FXCollections.observableArrayList(Database.lectureList);
        LectureOperationTable.setItems(list);
    }
    public void deleteLectureActionButton() throws IOException {
        Lecture lec = LectureOperationTable.getSelectionModel().getSelectedItem();
        if (lec == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/is/fxml/AdminDeleteQuestionPopUp.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Silme Onayı");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(LectureOperationTable.getScene().getWindow());  // onay kutusu ortada açılsın

            AdminDeletePopUpController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            if (controller.isstatus()) {
                Database.deleteObject(lec); // database den sil
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

}
