package es.iesfranciscodelosrios.BookMaker;

import java.util.ArrayList;
import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DAO.CharacterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.Character;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalCreateCharacterController {

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
	
	@FXML
	public void createCharacter() {
		if(checkFields()) {
			try {
				List<Book> books =  new ArrayList<>();
				books.add(MainScreenCrontroller.currentBook);
				new CharacterDAO().save(new Character(this.txtNombre.getText(), this.txtDescripcion.getText(), this.txtRol.getText(),books));
				System.out.println(new CharacterDAO().showAll());
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
