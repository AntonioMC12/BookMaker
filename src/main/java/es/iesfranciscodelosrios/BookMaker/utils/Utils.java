package es.iesfranciscodelosrios.BookMaker.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Utils {
	public static void popInfo(String content) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void popError(String content) {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static boolean popConfirmation(String content) {
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(content);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		    return false;
		}
	}
	
	public static void popWarning(String content) {
		Alert alert=new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Alerta");
		alert.setContentText(content);
		alert.showAndWait();
	}
}
