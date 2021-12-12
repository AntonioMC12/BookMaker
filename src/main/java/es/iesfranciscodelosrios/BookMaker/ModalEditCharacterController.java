package es.iesfranciscodelosrios.BookMaker;

import es.iesfranciscodelosrios.BookMaker.model.DAO.CharacterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Character;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalEditCharacterController {

	@FXML
	private Button botGuardar;

	@FXML
	private Button botVolver;

	@FXML
	private TextField txtDescripcion;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtRol;
	
	@FXML
	private ComboBox<Character> cb_characters;
	
	protected static ObservableList<Character> characters = FXCollections.observableArrayList();

	
	@FXML
	public void initialize() {
		try {
			characters.setAll(new CharacterDAO().showAll());
			this.cb_characters.setItems(characters);
		} catch (DAOException e) {
			e.printStackTrace();
			Utils.popError("Error al cargar los personajes");
		}
	}

	@FXML
	public void handleBack(ActionEvent event) {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("MainScreen");
			stage.show();
			stage.close();
		} catch (Exception e) {
			Utils.popError("Error al cerrar la ventana");
		}
	}
	
	/**
	 * Método que setea los campos con el artista seleccionado
	 */
	public void setCampos() {
		this.txtNombre.setText(this.cb_characters.getValue().getName());
		this.txtDescripcion.setText(this.cb_characters.getValue().getDescription());
		this.txtRol.setText(this.cb_characters.getValue().getRol());
	}
	
	@FXML
	public void createCharacter() {
		if(checkFields()) {
			try {
				new CharacterDAO().edit(new Character(this.cb_characters.getValue().getId(),this.txtNombre.getText(), this.txtDescripcion.getText(), this.txtRol.getText(),this.cb_characters.getValue().getBooks()));
				Utils.popConfirmation("Personaje creado con éxito");
			} catch (DAOException e) {
				Utils.popError("Error al crear el personaje");
				e.printStackTrace();
			}
		}else {
			Utils.popError("Rellene todos los campos correctamente");
		}
	}
	
	private boolean checkFields() {
		return (!this.txtNombre.getText().trim().isEmpty() && !this.txtDescripcion.getText().trim().isEmpty() && !this.txtRol.getText().trim().isEmpty());
	}
	

}
