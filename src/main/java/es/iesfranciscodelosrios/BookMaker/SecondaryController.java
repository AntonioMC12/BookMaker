package es.iesfranciscodelosrios.BookMaker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class SecondaryController {
	@FXML
    private MenuBar menuBar;

    @FXML
    private Menu m_characters;

    @FXML
    private Menu m_notes;

    @FXML
    private SplitPane sp_main;

    @FXML
    private ImageView iv_logo;

    @FXML
    private ComboBox<?> cb_selBook;

    @FXML
    private ComboBox<?> cb_selAct;

    @FXML
    private TableView<?> tv_chapters;

    @FXML
    private TableColumn<?, ?> tc_chapName;

    @FXML
    private TableColumn<?, ?> tc_chapState;

    @FXML
    private Button b_newChapter;

    @FXML
    private Button b_addReminder;

    @FXML
    private Button b_newAct;

    @FXML
    private ComboBox<?> cb_selChapter;

    @FXML
    private TextArea ta_text;

    @FXML
    private Button b_saveText;

    @FXML
    private Button b_cancel;

    @FXML
    private Button b_back;

    @FXML
    private TextArea ta_note;

    @FXML
    private TableView<?> tv_notes;

    @FXML
    private TableColumn<?, ?> tc_noteName;

    @FXML
    private TableColumn<?, ?> tc_NoteChapter;

    @FXML
    private Button b_saveNote;

    @FXML
    private Button b_delNote;
}