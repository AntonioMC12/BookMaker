package es.iesfranciscodelosrios.BookMaker;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ModalCreateChapterController implements Initializable{
	
   @FXML
    private Button botGuardar;

    @FXML
    private Button botVolver;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<Act> cmbActo;
    
    private ObservableList<Act> actList;
    public static Book currentBook;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	currentBook=SecondaryController.currentBook;
    	
    	ActDAO adao=new ActDAO();
    	
    	try {
    		this.actList=FXCollections.observableArrayList(adao.showAll());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Iterator<Act> myIterator=this.actList.iterator();
    	
    	while(myIterator.hasNext()) {
    		Act a=myIterator.next();
    		
    		if(a.getBook().equals(currentBook)==false) {
    			myIterator.remove();
    		}
    	}
    	
    	this.cmbActo.setItems(this.actList);
    }

    @FXML
    public void insertChapter(ActionEvent event) throws DAOException {
    	Act a=this.cmbActo.getSelectionModel().getSelectedItem();
    	Chapter c=new Chapter(); 
    	
    	if(a!=null&&this.txtNombre.getText().isEmpty()==false) {
    		c.setAct(a);
    		c.setName(this.txtNombre.getText());
    		
    		ChapterDAO cdao=new ChapterDAO();
    		
    		try {
    			cdao.save(c);    			
    		}catch(DAOException e) {
    			e.printStackTrace();
    		}
    		
    		Utils.popInfo("Captitulo creado correctamente");
    		Stage stage=(Stage) this.botVolver.getScene().getWindow();
			stage.close();
    	} else {
    		Utils.popWarning("Por favor, inserte un valor todos los campos");
    	}
    }
    
    @FXML
    public void goBack() {
    	Stage stage=(Stage) this.botVolver.getScene().getWindow();
		stage.close();
    }
}
