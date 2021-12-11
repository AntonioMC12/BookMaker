package es.iesfranciscodelosrios.BookMaker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

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
    private TableColumn<?, ?> col_bookDate;

    @FXML
    private TableColumn<?, ?> col_bookName;

    @FXML
    private TableColumn<?, ?> col_bookSummary;

    @FXML
    private ImageView img_userImg;

    @FXML
    private Label lb_email;

    @FXML
    private Label lb_userName;

    @FXML
    private TableView<?> tbl_books;

}
