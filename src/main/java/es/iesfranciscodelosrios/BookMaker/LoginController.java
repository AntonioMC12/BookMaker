package es.iesfranciscodelosrios.BookMaker;

import java.io.IOException;
import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.UserSesion;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtPass;

	@FXML
	private Button botEntrar;

	@FXML
	private Button botSalir;

	@FXML
	private ComboBox<?> cmb_Datos;

	@FXML
	private TextField txtNombreRegistro;

	@FXML
	private PasswordField txtPassRegistro;

	@FXML
	private TextField txtEmailRegistro;

	@FXML
	private Button botRegistro;

	@FXML
	private UserDAO usuario = new UserDAO();

	/**
	 * Método que comprueba los datos de los usuarios para el correcto logeo en la
	 * aplicación haciendo una consulta en la base de datos para comprar que existe
	 * y la veracidad de la información introducida.
	 * 
	 * @param event
	 */
	@FXML
	public void Login(ActionEvent event) throws IOException {
		String email = this.txtUsuario.getText();
		String pass = this.txtPass.getText();
		UserSesion holder = UserSesion.getInstance();

		if (checkFieldsLogin()) {
			try {
				List<User> users = new UserDAO().showAll();
				if (compareUsers(users, email, pass)) {
					holder.setUser(new UserDAO().selectByEmailAndPass(email, pass));
					App.GoTo(event, "MainScreen");
				}else {
					Utils.popError("El usuario no existe");
				}
			} catch (DAOException e1) {
				Utils.popError("Error en la base de datos");
				e1.printStackTrace();
			}
		} else {
			Utils.popError("Has introducido mal algún dato");
		}
	}

	/**
	 * Método que registra al usuario, comprobando los campos y seteando el usuario
	 * singletone con el que acaba de registrarse.
	 * 
	 * @param event
	 */
	@FXML
	public void registroUser(ActionEvent event) {
		if (checkFields()) {
			String user = txtNombreRegistro.getText();
			String password = txtPassRegistro.getText();
			String mail = txtEmailRegistro.getText();

			try {
				User c = new User(user, password, mail);
				new UserDAO().save(c);
				if (c != null) {
					UserSesion userS = UserSesion.getInstance();
					try {
						userS.setUser(new UserDAO().selectByEmailAndPass(mail, password));
						App.setRoot("MainScreen");
					} catch (DAOException e1) {
						e1.printStackTrace();
						Utils.popError("Error al cargar el usuario");
					} catch (IOException e) {
						e.printStackTrace();
						Utils.popError("Error al cargar la vista");
					}
				}
			} catch (DAOException e1) {
				Utils.popError("Error al consultar la base de datos");
			}
		}
	}

	// Botón para cerrar el Sistema
	@FXML
	public void handleClose(ActionEvent event) {
		Stage stage = (Stage) this.botSalir.getScene().getWindow();
		stage.close();
		System.exit(0);
	}

	@FXML
	public boolean checkFieldsLogin() {
		return (!this.txtUsuario.getText().trim().isEmpty() && !this.txtPass.getText().trim().isEmpty());

	}

	public boolean checkFields() {
		return (!this.txtEmailRegistro.getText().trim().isEmpty() && !this.txtNombreRegistro.getText().trim().isEmpty()
				&& !this.txtPassRegistro.getText().trim().isEmpty());
	}

	public boolean compareUsers(List<User> users, String email, String pass) {
		boolean result = false;
		for (User user : users) {
			if (user.getMail().equals(email) && user.getPassword().equals(pass)) {
				return true;
			}
		}

		return result;
	}
}
