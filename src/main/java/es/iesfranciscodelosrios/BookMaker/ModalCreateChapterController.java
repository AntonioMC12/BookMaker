package es.iesfranciscodelosrios.BookMaker;

import java.net.URL;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
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

public class ModalCreateChapterController implements Initializable {
	
	   @FXML
	    private Button botGuardar;

	    @FXML
	    private Button botVolver;

	    @FXML
	    private TextField txtNombre;

	    @FXML
	    private ComboBox<Act> cmbActo;
	    
	    private ActDAO act;
	    
	    private final ActDAO comboAct = new ActDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			ObservableList<Act> lista =  (ObservableList<Act>) comboAct.showAll();
			cmbActo.getItems().addAll(lista);
			cmbActo.setConverter(new ActConverter());
		} catch (Exception e) {
			System.out.println("Error");
		}		
	}
	
    @FXML
    void insertAct(ActionEvent event) throws DAOException {
    	String name = this.txtNombre.getText();
    	Long idAct = cmbActo.getSelectionModel().getSelectedItem().getId();
    	
    	if(this.txtNombre.getText().length()>0) {
    		Chapter b = null;
    		//Update
    		if(this.act!=null) {
    			//b = new Book(this.book.show(id), name, gen, res); implementar editar
    		}else { //Nuevo Libro
    			b = new Chapter(idAct, name);
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setTitle("Confirmación");
				alert.setContentText("Se ha podido incluir el Acto de la base de datos");
				alert.showAndWait();
    		}		
    		try {
				new ChapterDAO().save(b);
			} catch (Exception e) {
				throw new DAOException("Ha ocurrido un error.", e);
			}
    	}
    }
	
	public class ActConverter extends StringConverter<Act>{

		@Override
		public String toString(Act act) {
			// TODO Auto-generated method stub
			return act == null ? null :act.getName()+" "+act.getId();
		}

		@Override
		public Act fromString(String string) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
    @FXML
    void handleBack(ActionEvent event) throws DAOException {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("SecondaryScreen");
			stage.show();
			if (this.act != null) {
				stage.close();
			}		
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
    }

}
