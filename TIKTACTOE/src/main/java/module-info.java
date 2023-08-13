module com.example.tiktactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tiktactoe to javafx.fxml;
    exports com.example.tiktactoe;
}