package es.iesfranciscodelosrios.BookMaker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DAO.BookDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.UserSesion;
import javafx.event.ActionEvent;

public class ModalBookController {

	@FXML
	private Button botGuardar;

	@FXML
	private Button botVolver;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtResumen;

	@FXML
	private TextField txtGenero;

	protected Book book;

	@FXML
	void handleBack(ActionEvent event) throws DAOException {
		Stage stage = (Stage) this.botVolver.getScene().getWindow();
		try {
			App.setRoot("MainScreen");
			stage.show();
			if (this.book != null) {
				stage.close();
			}
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
	}

	@FXML
	void insertBook(ActionEvent event) {
		String name = this.txtNombre.getText();
		String gen = this.txtGenero.getText();
		String res = this.txtResumen.getText();
		User user = UserSesion.getInstance().getUser();

		if (this.txtNombre.getText().length() > 0 && this.txtGenero.getText().length() > 0
				&& this.txtResumen.getText().length() > 0 && user != null) {
			this.book = new Book(name, res, gen, user);
			try {
				List<Book> books = new BookDAO().showAllByTittle(this.book.getTittle());
				if (books != null && books.size()>0) {
					showError("El libro ya existe");
				} else {
					try {
						Book aux;
						new BookDAO().save(this.book);
						aux = new BookDAO().showAllByTittle(this.book.getTittle()).get(0);
						MainScreenCrontroller.listas.add(aux);
						user.getBooks().add(aux);
						new UserDAO().save(user);
						UserSesion.getInstance().setUser(user);
						showValid("Libro insertado correctamente");
					} catch (DAOException e) {
						showError("Error al guardar el libro");
						e.printStackTrace();
					}
				}
			} catch (DAOException e) {
				showError("Error al consultar los libros");
				e.printStackTrace();
			}
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