package utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import signUp.SignUpController;

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
	
	public void openNewStage(String screenToLoad) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(screenToLoad));
		Parent root = loader.load();
		
		SignUpController controller = loader.getController();
		controller.setOwner(true);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		
	}

}
