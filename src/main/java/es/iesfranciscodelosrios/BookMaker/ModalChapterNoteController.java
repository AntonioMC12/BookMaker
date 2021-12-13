package es.iesfranciscodelosrios.BookMaker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.event.ActionEvent;

public class ModalChapterNoteController {

    @FXML
    private Button b_Save;

    @FXML
    private Button b_Back;

    @FXML
    private TextField t_Name;

    @FXML
    private TextArea t_Text;
    
    @FXML
    public void goBack(ActionEvent event) {
    	Stage stage=(Stage) this.b_Back.getScene().getWindow();
		stage.close();
    }
    
    @FXML
    public void insertChapterNote(ActionEvent event) {
    	String name = this.t_Name.getText();
    	String text = this.t_Text.getText();
    	
    	if(!this.t_Name.getText().isEmpty()&&!this.t_Text.getText().isEmpty()) {
    		ChapterNote cn = new ChapterNote(name, text, SecondaryController.currentChapter);
    		try {
    			ChapterNoteDAO cndao=new ChapterNoteDAO();
				cndao.save(cn);
				Utils.popInfo("¡Nota creada con exito!");
				Stage stage=(Stage) this.b_Back.getScene().getWindow();
				stage.close();
			} catch (DAOException e) {
				e.printStackTrace();
				Utils.popError("Error al insertar la nota. ");
			}
    	}else {
    		Utils.popWarning("Por favor, rellene todos los campos");
    	}
    }
}