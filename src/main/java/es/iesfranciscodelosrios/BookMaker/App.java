package es.iesfranciscodelosrios.BookMaker;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage roostage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("SecondaryScreen"), 1330, 965);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
	/**
	 * Método para cambiar de vista
	 * 
	 * @param event evento de javafx
	 * @param url   ruta a dirigirse
	 */
	public static void GoTo(Event event, String url) {
		Parent root;
		try {
			root = FXMLLoader.load(App.class.getResource(url + ".fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}