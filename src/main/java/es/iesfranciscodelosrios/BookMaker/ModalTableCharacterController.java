package es.iesfranciscodelosrios.BookMaker;

import java.net.URL;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.BookMaker.utils.Utils;
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
	private TableView<Character> tbwCharacter;
	
	@FXML
	private TableColumn<Character, String> tcName;
	
	@FXML
	private TableColumn<Character, String> tcRol;
	
	@FXML
	private TableColumn<Character, String> tcDescription;
	
	@FXML
	private Button botVolver;
	
	private ObservableList<Character> characterList;
	
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
		this.tcName.setCellValueFactory(new PropertyValueFactory<Character, String>("name"));
		this.tcRol.setCellValueFactory(new PropertyValueFactory<Character, String>("rol"));
		this.tcDescription.setCellValueFactory(new PropertyValueFactory<Character, String>("description"));
		this.tbwCharacter.setItems(characterList);
	}

}
