module es.iesfranciscodelosrios.BookMaker {

	requires javafx.controls;
	requires javafx.fxml;
	requires java.persistence;
	requires java.sql;
	requires javafx.base;
	requires org.hibernate.orm.core;
	requires org.hibernate.commons.annotations;
	requires transitive javafx.graphics;

	opens es.iesfranciscodelosrios.BookMaker
			to org.hibernate.commons.annotations, org.hibernate.orm.core, javafx.fxml, java.persistence;

	exports es.iesfranciscodelosrios.BookMaker;
	exports es.iesfranciscodelosrios.BookMaker.model.DO;

	opens es.iesfranciscodelosrios.BookMaker.model.DO
			to org.hibernate.commons.annotations, org.hibernate.orm.core, java.persistence;
	opens es.iesfranciscodelosrios.BookMaker.utils
			to org.hibernate.commons.annotations, org.hibernate.orm.core, java.persistence;
}
