package es.iesfranciscodelosrios.BookMaker;

import java.io.IOException;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.UserSesion;
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
	
	@FXML
	private void eventKey(KeyEvent event) {
		Object e= event.getSource();
		if(e.equals(txtUsuario)) {
			if(event.getCharacter().equals(" ")) {
				event.consume();
			}
			
		}else if(e.equals(txtPass)){
			if(event.getCharacter().equals(" ")) {
				event.consume();
			}
		}
		
	}
	
	@FXML 
	private void eventAction (ActionEvent event) throws DAOException {
		Object evento=event.getSource();
		
		if(evento.equals(botEntrar)) {
			if(!txtUsuario.getText().isEmpty()&&!txtPass.getText().isEmpty()) {
				String mail = txtUsuario.getText();
				String password = txtPass.getText();
				User user = usuario.selectByEmailAndPass(mail, password);				
				
					if(user!=null) {
						UserSesion userS=UserSesion.getInstance();
						userS.setUser(user);
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("CORRECTO");
						alert.setContentText("El email y la contraseña son correctos");
						alert.showAndWait();
						try {
							App.setRoot("MainScreen");
						} catch (Exception e) {
							throw new DAOException(e);
						}
						//OpenSecondMenu();
					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setTitle("ERROR");
						alert.setContentText("El email o la contraseña no son correctos");
						this.txtUsuario.clear();
						this.txtPass.clear();
						alert.showAndWait();
					}						
				}
		}
	}
	
	@FXML
	public void registroUser(ActionEvent event) throws DAOException {
		Object evento=event.getSource();		
		if(evento.equals(botRegistro)) {
			if(!txtNombreRegistro.getText().isEmpty()&&!txtPassRegistro.getText().isEmpty()&&!txtEmailRegistro.getText().isEmpty()) {
				String user = txtNombreRegistro.getText();
				String password = txtPassRegistro.getText();
				String mail = txtEmailRegistro.getText();				
				if(this.txtPassRegistro.getText().length()>2&&this.txtEmailRegistro.getLength()>2
						&&this.txtNombreRegistro.getText().length()>2) {
					User c= new User(user,password,mail);
					UserDAO u = new UserDAO();
						try {
							u.save(c);
						} catch (DAOException e1) {
							throw new DAOException(e1);
						}
						if(c!=null) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setHeaderText(null);
							alert.setTitle("CORRECTO");
							alert.setContentText("El usuario se ha registrado correctamente");
							alert.showAndWait();
							UserSesion userS=UserSesion.getInstance();
							try {
								userS.setUser(u.selectByEmailAndPass(mail, password));
							} catch (DAOException e1) {
								// TODO Auto-generated catch block
								throw new DAOException(e1);
							}
							try {
								App.setRoot("MainScreen");
							} catch (Exception e) {
								throw new DAOException(e);
							}
						}
					}					
				}
				
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Debe rellenar todos los campos");
				this.txtUsuario.clear();
				this.txtPass.clear();
				alert.showAndWait();
			}
		}		

	
	//Abre la segunda pantalla si falla los setRoot se puede implantar
	@FXML
	public void OpenSecondMenu() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainScreen.fxml"));
			Parent modal;
			modal = fxmlLoader.load();
			
			Stage modalStage= new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.roostage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();

				}catch (IOException ex){
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No ha sido posible cargar la segunda pantalla");
					alert.showAndWait();
				}
	}
	
	
	
	
	//Botón para cerrar el Sistema
	@FXML
	public void handleClose(ActionEvent event) {
		Stage stage = (Stage) this.botSalir.getScene().getWindow();
		stage.close();
		System.exit(0);
	}
}
