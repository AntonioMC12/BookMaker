package es.iesfranciscodelosrios.BookMaker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {
	@FXML
	private MenuBar menuBar;

	@FXML
	private Menu m_characters;

	@FXML
	private Menu m_notes;
	
	@FXML
	private MenuItem mit_showCharacter;

	@FXML
	private SplitPane sp_main;

	@FXML
	private ImageView iv_logo;

	@FXML
	private ComboBox<Act> cb_selAct;

	@FXML
	private TableView<Chapter> tv_chapters;

	@FXML
	private TableColumn<Chapter, String> tc_chapName;

	@FXML
	private TableColumn<Chapter, String> tc_chapState;

	@FXML
	private Button b_newChapter;

	@FXML
	private Button b_addReminder;

	@FXML
	private Button b_newAct;

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
	private TableView<ChapterNote> tv_notes;

	@FXML
	private TableColumn<ChapterNote, String> tc_noteName;

	@FXML
	private TableColumn<ChapterNote, Long> tc_NoteChapter;

	@FXML
	private Button b_saveNote;

	@FXML
	private Button b_delNote;

	@FXML
	private Label l_selectedBook;

	@FXML
	private Label l_chapName;

	@FXML
	private Label l_noteName;

	@FXML
	private Button b_createNote;

	@FXML
	private Button b_delChapter;

	@FXML
	private Button b_delAct;

	@FXML
	private TextField tf_state;

	@FXML
	private Button b_saveSate;
	
	

	private ObservableList<Act> actList;
	private ObservableList<Chapter> chapterList;
	private ObservableList<ChapterNote> chapterNotes;
	private ObservableList<Chapter> sortedChapterList;

	public static Chapter currentChapter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println(MainScreenCrontroller.currentBook.toString());
		this.l_selectedBook.setText(MainScreenCrontroller.currentBook.toString());
		setObservablesList();
		setComboboxes();
		setChapterTable();
		setChapterNotesTable();

	}

	public void setObservablesList() {
		ChapterDAO cdao = new ChapterDAO();
		ChapterNoteDAO cndao = new ChapterNoteDAO();

		try {
			this.actList = FXCollections.observableArrayList();
			this.chapterList = FXCollections.observableArrayList(cdao.showAll());
			this.chapterNotes = FXCollections.observableArrayList();
			this.sortedChapterList = FXCollections.observableArrayList();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utils.popError("Error al cargar la pantalla. ");
		}
	}

	public void setComboboxes() {
		ActDAO adao = new ActDAO();

		List<Act> allActs = null;

		try {
			allActs = new ArrayList<Act>(adao.showAll());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utils.popError("Error al cargar la pantalla. ");
		}

		for (Act act : allActs) {
			if (act.getBook() == MainScreenCrontroller.currentBook) {
				this.actList.add(act);
			}
		}

		this.cb_selAct.setItems(actList);
	}

	public void setChapterTable() {
		this.tv_chapters.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {

					// Lo que va a hacer cuando el usuario haga click en la tabla

					if (tv_chapters.getSelectionModel().getSelectedItem() != null) {
						if (cb_selAct.getValue() != null) {
							currentChapter = tv_chapters.getSelectionModel().getSelectedItem();
							
							chapterNotes.clear();
							
							ChapterNoteDAO cndao=new ChapterNoteDAO();
							
							try {
								chapterNotes.setAll(cndao.selectedByChapter(currentChapter));
								//tv_notes.refresh();
							} catch (DAOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							l_chapName.setText(currentChapter.getName());
							ta_text.setText(currentChapter.getText());
							tf_state.setText(currentChapter.getState());
						}
					}
				}
			}
		});

		this.tc_chapName.setCellValueFactory(new PropertyValueFactory<Chapter, String>("name"));
		this.tc_chapState.setCellValueFactory(new PropertyValueFactory<Chapter, String>("state"));

		this.tv_chapters.getSortOrder().add(this.tc_chapName);
	}

	public void setChapterNotesTable() {
		this.tv_notes.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {

					// Lo que va a hacer cuando el usuario haga click en la tabla

					if (tv_notes.getSelectionModel().getSelectedItem() != null) {
						l_noteName.setText(tv_notes.getSelectionModel().getSelectedItem().getName());
						ta_note.setText(tv_notes.getSelectionModel().getSelectedItem().getContent());
					}
				}
			}
		});

		this.tc_noteName.setCellValueFactory(new PropertyValueFactory<ChapterNote, String>("name"));
		this.tc_NoteChapter.setCellValueFactory(new PropertyValueFactory<ChapterNote, Long>("id"));

		this.tv_notes.setItems(chapterNotes);

		this.tv_notes.getSortOrder().add(this.tc_noteName);
	}

	@FXML
	public void checkBookChapters() {
		Act a = this.cb_selAct.getValue();

		if (a != null) {
			this.sortedChapterList.clear();

			for (Chapter c : chapterList) {
				if (c.getAct().getId() == a.getId()) {
					this.sortedChapterList.add(c);
				}
			}

			this.tv_chapters.setItems(this.sortedChapterList);
		}
	}

	@FXML
	public void saveChapter() {
		currentChapter.setText(this.ta_text.getText());

		ChapterDAO cdao = new ChapterDAO();
		try {
			cdao.save(currentChapter);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utils.popError("Error al guardar el capítulo. ");
		}
	}

	@FXML
	public void saveChapterNote() {
		ChapterNote cn = this.tv_notes.getSelectionModel().getSelectedItem();

		if(cn!=null) {
			if (this.ta_note.getText() != null) {
				cn.setContent(this.ta_note.getText());
				
				ChapterNoteDAO cndao = new ChapterNoteDAO();
				try {
					cndao.save(cn);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Utils.popError("Error al guardar la nota. ");
				}
			}			
		}else {
			Utils.popWarning("Seleccione una nota primero");
		}
	}

	@FXML
	public void deleteChapterNote() {
		ChapterNote cn = this.tv_notes.getSelectionModel().getSelectedItem();

		if (cn != null) {
			ChapterNoteDAO cndao = new ChapterNoteDAO();
			try {
				cndao.delete(cn);
				this.chapterNotes.remove(cn);
				this.ta_note.setText("");
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.popError("Error al borrar la nota. ");
			}
		}else {
			Utils.popWarning("Seleccione una nota primero");
		}
	}

	@FXML
	public void createChapterNote() {
		if(currentChapter!=null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ModalChapterNote.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.popError("Error al cargar la pantalla. ");
			}
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
			
			ChapterNoteDAO cndao = new ChapterNoteDAO();
			
			this.chapterNotes.clear();
			try {
				this.chapterNotes.addAll(cndao.selectedByChapter(currentChapter));
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.popError("Error al añadir las notas. ");
			}	
		}else {
			Utils.popWarning("Seleccione un capitulo primero");
		}
	}

	@FXML
	public void createChapter() {
		if (cb_selAct.getSelectionModel().getSelectedItem() != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ModalCreateChapter.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.popError("Error al cargar la pantalla. ");
			}

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();

			ChapterDAO cdao = new ChapterDAO();

			if (ModalCreateChapterController.createdChapter != null) {
				this.chapterList.clear();

				try {
					this.sortedChapterList.add(ModalCreateChapterController.createdChapter);
					this.chapterList.addAll(cdao.showAll());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Utils.popError("Error al añadir los Capítulos. ");
				}
			}

		} else {
			Utils.popWarning("Elija un acto primero");
		}
	}

	@FXML
	public void deleteChapter() {
		Chapter c = tv_chapters.getSelectionModel().getSelectedItem();

		ChapterDAO cdao = new ChapterDAO();

		if (c != null) {
			try {
				cdao.delete(c);
				this.sortedChapterList.remove(c);
				this.chapterList.remove(c);
				Utils.popInfo("Capitulo borrado con exito");
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Utils.popWarning("Seleccione un capitulo a borrar");
		}
	}

	@FXML
	public void saveState() {
		String state = this.tf_state.getText();

		System.out.println(currentChapter);

		if (currentChapter != null) {
			if (!state.isEmpty()) {
				currentChapter.setState(state);
				ChapterDAO cdao = new ChapterDAO();

				try {
					cdao.save(currentChapter);

					for (int i = 0; i < this.chapterList.size(); i++) {
						if (this.chapterList.get(i).equals(currentChapter)) {
							this.chapterList.get(i).setState(state);
							this.tv_chapters.refresh();
							i = this.chapterList.size();
						}
					}

				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Utils.popInfo("Introduzca un estado");
			}
		} else {
			Utils.popInfo("Seleccione un capitulo primero");
		}
	}

	@FXML
	public void createAct() {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("ModalCreateAct.fxml"));
		Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utils.popError("Error al crear el Acto. ");
		}
		
		Scene scene=new Scene(root);
		Stage stage=new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.showAndWait();
		
		if(ModalCreateActController.createdAct!=null) {
			this.actList.add(ModalCreateActController.createdAct);			
		}
	}

	@FXML
	public void deleteAct() {
		Act a = cb_selAct.getSelectionModel().getSelectedItem();

		if (a != null) {
			ActDAO adao = new ActDAO();

			try {
				adao.delete(a);
				this.actList.remove(a);
				Utils.popInfo("Acto borrado");
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.popError("Error al eliminar el Acto. ");
			}
		} else {
			Utils.popWarning("Seleccione un acto primero");
		}
	}

	@FXML
	public void GoCreateCharacter(ActionEvent event) {
		openModal(event, "ModalCreateCharacter.fxml");
	}

	@FXML
	public void GoEditCharacter(ActionEvent event) {
		openModal(event, "ModalEditCharacter.fxml");
	}
	
	@FXML
	public void GoShowCharacters(ActionEvent event) {
		openModal(event, "ModalTableCharacter.fxml");
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
			Utils.popError("Error al mostrar la nueva pantalla. ");
		}
	}
	
	@FXML
	public void goToMainController(ActionEvent event) {
		App.GoTo(event, "MainScreen");
	}
}