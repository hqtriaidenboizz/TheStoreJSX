module com.example.TheStore {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.TheStore to javafx.fxml;
    exports com.example.TheStore;
    exports com.example.TheStore.Database;
    opens com.example.TheStore.Database to javafx.fxml;
}