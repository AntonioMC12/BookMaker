module es.iesfranciscodelosrios.BookMaker {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.iesfranciscodelosrios.BookMaker to javafx.fxml;
    exports es.iesfranciscodelosrios.BookMaker;
}
