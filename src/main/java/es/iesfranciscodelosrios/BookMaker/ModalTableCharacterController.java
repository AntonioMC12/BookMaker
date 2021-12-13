package es.iesfranciscodelosrios.BookMaker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.BookMaker.model.DAO.CharacterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModalTableCharacterController implements Initializable{
	
	@FXML
	private TableView<es.iesfranciscodelosrios.BookMaker.model.DO.Character> tbwCharacter;
	
	@FXML
	private TableColumn<es.iesfranciscodelosrios.BookMaker.model.DO.Character, String> tcName;
	
	@FXML
	private TableColumn<es.iesfranciscodelosrios.BookMaker.model.DO.Character, String> tcRol;
	
	@FXML
	private TableColumn<es.iesfranciscodelosrios.BookMaker.model.DO.Character, String> tcDescription;
	
	@FXML
	private Button botVolver;
	
	private ObservableList<es.iesfranciscodelosrios.BookMaker.model.DO.Character> characterList;
	
	@FXML
	public void handleBack(ActionEvent event) {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("MainScreen");
			stage.show();
			stage.close();
		} catch (Exception e) {
			Utils.popError("Error al cerrar la ventana");
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCharacterTable();
		
	}
	
	public void setCharacterTable() {
		CharacterDAO cdao=new CharacterDAO();
		
		try {
			this.characterList=FXCollections.observableArrayList(cdao.showAll());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tcName.setCellValueFactory(new PropertyValueFactory<es.iesfranciscodelosrios.BookMaker.model.DO.Character, String>("name"));
		this.tcRol.setCellValueFactory(new PropertyValueFactory<es.iesfranciscodelosrios.BookMaker.model.DO.Character, String>("rol"));
		this.tcDescription.setCellValueFactory(new PropertyValueFactory<es.iesfranciscodelosrios.BookMaker.model.DO.Character, String>("description"));
		
		this.tbwCharacter.setItems(characterList);
	}
	
	@FXML
	public void deleteCharacter() {
		es.iesfranciscodelosrios.BookMaker.model.DO.Character c;
		
		c=this.tbwCharacter.getSelectionModel().getSelectedItem();
		
		if(c!=null) {
			CharacterDAO cdao=new CharacterDAO();
			
			try {
				cdao.delete(c);
				this.characterList.remove(c);
				Utils.popInfo("Personaje borrado con exito");
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Utils.popInfo("Seleccione un personaje primero");
		}
	}

}
