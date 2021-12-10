module es.iesfranciscodelosrios.BookMaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;


    opens es.iesfranciscodelosrios.BookMaker to javafx.fxml;
    exports es.iesfranciscodelosrios.BookMaker;
}
