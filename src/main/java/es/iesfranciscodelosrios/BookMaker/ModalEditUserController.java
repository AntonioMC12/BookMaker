package es.iesfranciscodelosrios.BookMaker;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.UserSesion;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalEditUserController {

	@FXML
	private Button botGuardar;

	@FXML
	private Button botVolver;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPassword;

	protected User user;

	@FXML
	void handleBack(ActionEvent event) throws DAOException {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("MainScreen");
			stage.show();
			stage.close();

		} catch (Exception e) {
			e.printStackTrace();
			Utils.popError("Error al dejar la pantalla. ");		}
	}

	@FXML
	void editUser(ActionEvent event) throws DAOException {

		if (this.txtNombre.getText().length() > 0 && this.txtPassword.getText().length() > 0) {
			try {
				user.setName(this.txtNombre.getText());
				user.setPassword(this.txtPassword.getText());
				new UserDAO().edit(user);
				UserSesion.getInstance().setUser(user);
				System.out.println(UserSesion.getInstance().getUser());
			} catch (Exception e) {
				e.printStackTrace();
				Utils.popError("Error al editar el usuario. ");
			}
		}
	}

	public void initialize() {

		user = UserSesion.getInstance().getUser();
		this.txtNombre.setText(user.getName());
		this.txtPassword.setText(user.getPassword().replaceAll(user.getPassword(), "******"));
	}

}
