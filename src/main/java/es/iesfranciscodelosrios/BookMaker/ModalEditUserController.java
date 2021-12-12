package es.iesfranciscodelosrios.BookMaker;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.UserSesion;
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
			throw new DAOException("Ha ocurrido un error.", e);
		}
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
				throw new DAOException("Ha ocurrido un error.", e);
			}
		}
	}

	public void initialize() {
		System.out.println("ENTRO EN EL INITIALIZEEEEEEEEEEE");
		user = UserSesion.getInstance().getUser();
		System.out.println(user);
		this.txtNombre.setText(user.getName());
		this.txtPassword.setText(user.getPassword().replaceAll(user.getPassword(), "******"));
	}

}
