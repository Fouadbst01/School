module com.example.tp2_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafaker;

    opens com.example.tp2_ to javafx.fxml;
    exports com.example.tp2_;
    exports com.example.tp2_.metier;
    opens com.example.tp2_.metier to javafx.fxml;
    exports com.example.tp2_.Controller;
    opens com.example.tp2_.Controller to javafx.fxml;
    exports com.example.tp2_.metierCRUD;
    opens com.example.tp2_.metierCRUD to javafx.fxml;
    exports com.example.tp2_.DBConnection;
    opens com.example.tp2_.DBConnection to javafx.fxml;
}