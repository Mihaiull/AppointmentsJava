module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;

    opens GUI to javafx.fxml;

    exports GUI;


}