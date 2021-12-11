package es.iesfranciscodelosrios.BookMaker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


import es.iesfranciscodelosrios.BookMaker.model.DAO.BookDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ModalBookController {

    @FXML
    private Button botGuardar;

    @FXML
    private Button botVolver;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtResumen;

    @FXML
    private TextField txtGenero;
    
    
    private BookDAO book;

    @FXML
    void handleBack(ActionEvent event) throws DAOException {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("MainScreen");
			stage.show();
			if (this.book != null) {
				stage.close();
			}		
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
    }
    
    @FXML
    void insertBook(ActionEvent event) throws DAOException {
    	String name = this.txtNombre.getText();
    	String gen = this.txtGenero.getText();
    	String res = this.txtResumen.getText();
    	
    	if(this.txtNombre.getText().length()>0&&this.txtGenero.getText().length()>0&&this.txtResumen.getText().length()>0) {
    		Book b = null;
    		//Update
    		if(this.book!=null) {
    			//b = new Book(this.book.show(id), name, gen, res); implementar editar
    		}else { //Nuevo Libro
    			b = new Book(name, gen, res);
    		}		
    		try {
				new BookDAO().save(b);
			} catch (Exception e) {
				throw new DAOException("Ha ocurrido un error.", e);
			}
    	}
    }
    

    

}