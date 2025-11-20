module org.example.bookmanagerclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.bookmanagerclient to javafx.fxml;
    exports org.example.bookmanagerclient;
}