package utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseController {
	
	public void hideScreen(Node node) {
		node.getScene().getWindow().hide();
	}
	
	public void loadScreen(String screenToLoad) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource(screenToLoad));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
