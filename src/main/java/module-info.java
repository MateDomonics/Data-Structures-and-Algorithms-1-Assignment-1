module com.example.assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires xstream;


    opens com.example.assignment to javafx.fxml, xstream;

    exports com.example.assignment;
}