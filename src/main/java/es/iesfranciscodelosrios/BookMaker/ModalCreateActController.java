package es.iesfranciscodelosrios.BookMaker;

import java.net.URL;
import java.util.ResourceBundle;


import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalCreateActController implements Initializable {
	
	   @FXML
	    private Button botGuardar;

	    @FXML
	    private Button botVolver;

	    @FXML
	    private TextField txtNombre;

	    @FXML
	    private TextArea txtResumen;
	    
	    private static Book currentBook;
	    public static Act createdAct;
	    
		@Override
		public void initialize(URL location, ResourceBundle resources){
			currentBook=SecondaryController.currentBook;
		}

		@FXML
		public void insertAct() {
			
			String name=this.txtNombre.getText();
			String resume=this.txtResumen.getText();
			
			if(!name.isEmpty()&&!resume.isEmpty()) {
				Act a=new Act(currentBook, name, resume);
				
				ActDAO adao=new ActDAO();
				
				try {
					adao.save(a);
					createdAct=a;
					Utils.popInfo("Acto creado con exito");
					Stage stage=(Stage) this.botVolver.getScene().getWindow();
					stage.close();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				Utils.popWarning("Por favor, rellene todos los campos");
			}
		}
		
		@FXML
	    public void goBack() {
	    	Stage stage=(Stage) this.botVolver.getScene().getWindow();
			stage.close();
	    }
}
