package es.iesfranciscodelosrios.BookMaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.BookDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.GlobalNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ReminderDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.model.DO.Character;
import es.iesfranciscodelosrios.BookMaker.model.DO.GlobalNote;
import es.iesfranciscodelosrios.BookMaker.model.DO.Reminder;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.UserSesion;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class MainScreenCrontroller {

	@FXML
	private Button btn_continue;

	@FXML
	private Button btn_delete;

	@FXML
	private Button btn_editUser;

	@FXML
	private Button btn_exit;

	@FXML
	private Button btn_export;

	@FXML
	private Button btn_newBook;

	@FXML
	private Button btn_removeUser;

	@FXML
	private TableColumn<Book, String> col_bookGenre;

	@FXML
	private TableColumn<Book, String> col_bookName;

	@FXML
	private TableColumn<Book, String> col_bookSummary;

	@FXML
	private ImageView img_userImg;

	@FXML
	private Label lb_email;

	@FXML
	private Label lb_userName;

	@FXML
	private TableView<Book> tbl_books;

	protected static ObservableList<Book> listas = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		UserSesion.getInstance();
		this.btn_delete.setDisable(true);
		setInfo();

	}

	/**
	 * Seteo la info de la pantalla principal
	 */
	private void setInfo() {
		UserSesion holder = UserSesion.getInstance();
		if (holder.getUser() != null) {
			this.lb_userName.setText(holder.getUser().getName());
			this.lb_email.setText(holder.getUser().getMail());
		}
		setListas(holder.getUser());
	}

	/**
	 * Método para setear las listas del usuario, tanto las listas propias como las
	 * listas que está suscrito el usuario.
	 * 
	 * @param holder Singleton para almecenar el usuario actual y sus listas.
	 */
	private void setListas(User user) {
		try {
			listas.setAll(new BookDAO().showAllByUser(user));
//			user.setBooks(listas);
			this.col_bookName.setCellValueFactory(listas -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(listas.getValue().getTittle());
				return v;
			});
			;

			this.col_bookGenre.setCellValueFactory(listas -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(listas.getValue().getGenre());
				return v;
			});
			;

			this.col_bookSummary.setCellValueFactory(listas -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(listas.getValue().getSummary());
				return v;
			});
			;

			this.tbl_books.setItems(listas);

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void crearLibroButton(ActionEvent event) {
		openModal(event, ".fxml");
	}

	@FXML
	public void editarUserButton(ActionEvent event) {
		openModal(event, ".fxml");
	}
	
	/**
	 * Método que abre una ventana modal
	 * 
	 * @param event
	 * @param url   nombre del fichero fxml
	 */
	private void openModal(ActionEvent event, String url) {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(url));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.roostage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@FXML
	public void showValid(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setHeaderText(null);
		alert.setTitle("Éxito");
		alert.setContentText(text);
		alert.showAndWait();
	}

	@FXML
	public void showError(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(text);
		alert.showAndWait();
	}
}
