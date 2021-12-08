module es.iesfranciscodelosrios.BookMaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;


    opens es.iesfranciscodelosrios.BookMaker to javafx.fxml;
    exports es.iesfranciscodelosrios.BookMaker;
}
