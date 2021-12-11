package es.iesfranciscodelosrios.BookMaker;

import java.net.URL;
import java.util.ResourceBundle;


import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.BookDAO;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ModalCreateActController implements Initializable {
	
	   @FXML
	    private Button botGuardar;

	    @FXML
	    private Button botVolver;

	    @FXML
	    private TextField txtNombre;

	    @FXML
	    private TextField txtResumen;

	    @FXML
	    private ComboBox<Book> cmbActo;
	    
	    private ActDAO book;
	    
	    private final BookDAO comboBook = new BookDAO();
	    
		@Override
		public void initialize(URL location, ResourceBundle resources){
			try {
				ObservableList<Book> lista =  (ObservableList<Book>) comboBook.showAll();
				cmbActo.getItems().addAll(lista);
				cmbActo.setConverter(new bookConverter());
			} catch (Exception e) {
				System.out.println("Error");
			}			
		}
		
	    @FXML
	    void handleBack(ActionEvent event) throws DAOException {
			Stage stage = (Stage) this.botVolver.getScene().getWindow();
			try {
				App.setRoot("SecondaryScreen");
				stage.show();
				if (this.book != null) {
					stage.close();
				}		
			} catch (Exception e) {
				throw new DAOException("Ha ocurrido un error.", e);
			}
	    }
	    
	    @FXML
	    void insertAct(ActionEvent event) throws DAOException {
	    	String name = this.txtNombre.getText();
	    	String res = this.txtResumen.getText();
	    	Long idBook = cmbActo.getSelectionModel().getSelectedItem().getId();
	    	
	    	if(this.txtNombre.getText().length()>0&&this.txtResumen.getText().length()>0) {
	    		Act b = null;
	    		//Update
	    		if(this.book!=null) {
	    			//b = new Book(this.book.show(id), name, gen, res); implementar editar
	    		}else { //Nuevo Libro
	    			b = new Act(idBook, name, res);
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setTitle("Confirmación");
					alert.setContentText("Se ha podido incluir el Acto de la base de datos");
					alert.showAndWait();
	    		}		
	    		try {
					new ActDAO().save(b);
				} catch (Exception e) {
					throw new DAOException("Ha ocurrido un error.", e);
				}
	    	}
	    }
	    
		public class bookConverter extends StringConverter<Book>{

			@Override
			public String toString(Book book) {
				// TODO Auto-generated method stub
				return book == null ? null :book.getTittle()+" "+book.getId();
			}

			@Override
			public Book fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
		




	    

}
