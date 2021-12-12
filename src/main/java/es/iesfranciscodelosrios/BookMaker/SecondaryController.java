package es.iesfranciscodelosrios.BookMaker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.BookDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.model.DO.GlobalNote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SecondaryController implements Initializable{
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

    private ObservableList<Act> actList;
    private ObservableList<Chapter> chapterList;
    private ObservableList<ChapterNote> chapterNotes;
    private ObservableList<Chapter> sortedChapterList;
    
    private Chapter currentChapter;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(MainScreenCrontroller.currentBook.toString());
		setObservablesList();
		setComboboxes();
		setChapterTable();
		setChapterNotesTable();
	}
	
	public void setObservablesList() {
		ActDAO adao=new ActDAO();
		ChapterDAO cdao=new ChapterDAO();
		ChapterNoteDAO cndao=new ChapterNoteDAO();
		
		try {
			this.actList=FXCollections.observableArrayList(adao.showAll());
			this.chapterList=FXCollections.observableArrayList(cdao.showAll());
			this.chapterNotes=FXCollections.observableArrayList(cndao.showAll());
			this.sortedChapterList=FXCollections.observableArrayList();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setComboboxes() {
		this.cb_selAct.setItems(actList);
	}
	
	public void setChapterTable() {
		this.tv_chapters.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					
					//Lo que va a hacer cuando el usuario haga click en la tabla
					
					if (tv_chapters.getSelectionModel().getSelectedItem() != null) {
						if(cb_selAct.getValue()!=null) {
							currentChapter=tv_chapters.getSelectionModel().getSelectedItem();
							
							l_chapName.setText(currentChapter.getName());
							ta_text.setText(currentChapter.getText());
						}
					}
				}
			}
		});

		this.tc_chapName.setCellValueFactory(new PropertyValueFactory<Chapter, String>("name"));
		this.tc_chapState.setCellValueFactory(new PropertyValueFactory<Chapter, String>("state"));
		
		this.tc_chapState.setEditable(true);
		this.tc_chapState.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Chapter, String>>() { //EventHandler para celda editable
			@Override
			public void handle(CellEditEvent<Chapter, String> event) {
				
				//Codigo que se va a ejecutar cuando la celda sea editada
				
				Chapter ec=tv_chapters.getSelectionModel().getSelectedItem();
				String newSate=event.getNewValue();
				
				for(int i=0; i<chapterList.size(); i++) {
					if(chapterList.get(i).equals(ec)) {
						chapterList.get(i).setState(newSate);
						
						ChapterDAO cdao=new ChapterDAO();
						
						try {
							cdao.save(chapterList.get(i));
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						i=chapterList.size();
					}
				}
			}
	    });
		
		this.tv_chapters.getSortOrder().add(this.tc_chapName);
	}
	
	public void setChapterNotesTable() {
		this.tv_notes.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					
					//Lo que va a hacer cuando el usuario haga click en la tabla
					
					if (tv_notes.getSelectionModel().getSelectedItem() != null) {
						l_noteName.setText(tv_notes.getSelectionModel().getSelectedItem().getName());
						ta_note.setText(tv_notes.getSelectionModel().getSelectedItem().getContent());
					}
				}
			}
		});

		this.tc_noteName.setCellValueFactory(new PropertyValueFactory<ChapterNote, String>("name"));
		this.tc_NoteChapter.setCellValueFactory(new PropertyValueFactory<ChapterNote, Long>("chapter_id"));

		this.tv_notes.getSortOrder().add(this.tc_noteName);
	}
	
	@FXML
	public void checkBookChapters() {
		Act a=this.cb_selAct.getValue();
		
		if(a!=null) {
			this.sortedChapterList.clear();
			
			for (Chapter c: chapterList) {
				if(c.getAct().getId()==a.getId()) {
					this.sortedChapterList.add(c);
				}
			}
			
			this.tv_chapters.setItems(this.sortedChapterList);
		}
	}
	
	@FXML
	public void saveChapter() {
		this.currentChapter.setText(this.ta_text.getText());
		
		ChapterDAO cdao=new ChapterDAO();
		try {
			cdao.save(this.currentChapter);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void saveChapterNote() {
		ChapterNote cn=this.tv_notes.getSelectionModel().getSelectedItem();
		
		if(cn!=null&&this.ta_note.getText()!=null) {
			cn.setContent(this.ta_note.getText());
			
			ChapterNoteDAO cndao=new ChapterNoteDAO();
			try {
				cndao.save(cn);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void deleteChapterNote() {
		ChapterNote cn=this.tv_notes.getSelectionModel().getSelectedItem();
		
		if(cn!=null) {
			ChapterNoteDAO cndao=new ChapterNoteDAO();
			try {
				cndao.delete(cn);
				this.chapterNotes.remove(cn);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}