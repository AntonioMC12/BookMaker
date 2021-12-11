module es.iesfranciscodelosrios.BookMaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
	requires javafx.base;


    opens es.iesfranciscodelosrios.BookMaker to javafx.fxml;
    exports es.iesfranciscodelosrios.BookMaker;
}
