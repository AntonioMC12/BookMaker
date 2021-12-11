package es.iesfranciscodelosrios.BookMaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.UserSesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalEditUserController implements Initializable {
	
    @FXML
    private Button botGuardar;

    @FXML
    private Button botVolver;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;
    
    protected User user;
    private UserDAO userD;

    @FXML
    void handleBack(ActionEvent event) throws DAOException {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("MainScreen");
			stage.show();
			if (this.userD != null) {
				stage.close();
			}		
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
    }
    
    @FXML
    void editUser(ActionEvent event) throws DAOException {
    	
    	if(this.txtNombre.getText().length()>0&&this.txtPassword.getText().length()>0) {
    		try {
				new UserDAO().edit(user);
			} catch (Exception e) {
				throw new DAOException("Ha ocurrido un error.", e);
			}
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UserSesion userS = UserSesion.getInstance();
		user = userS.getUser();
		
		this.txtNombre.setText(user.getName());
		this.txtPassword.setText(user.getPassword().replaceAll(user.getPassword(), "******"));		
	}
    


}
