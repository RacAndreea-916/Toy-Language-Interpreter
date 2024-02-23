module com.example.haisamoriamu {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.toyLanguage to javafx.fxml;
    opens Model to javafx.base;

    exports com.example.toyLanguage;
}