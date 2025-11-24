module com.example.obs {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;

    opens com.example.obs to javafx.fxml;
    exports com.example.obs;
    exports com.example.obs.PageControllers;
    opens com.example.obs.PageControllers to javafx.fxml;
    exports com.example.obs.Authentication;
    opens com.example.obs.Authentication to javafx.fxml;
}