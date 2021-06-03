package owner.home;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.BaseController;
import utils.Constants;

public class HomeController extends BaseController{

    @FXML
    private Button updateDetailsButton, propertyButton, tenantButton, createStatementButton, exitButton, archivesButton;

    @FXML
    void initialize() {
    	
    	tenantButton.setOnAction(event -> {
    		hideScreen(tenantButton);
    		//signUpPerson(false);
    		loadViewScreen(Constants.TENANT);
    		
    	});

    }
    
    private void loadViewScreen(String type) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/owner/home/ViewPropertyTenant.fxml"));
			Parent root = (Parent) loader.load();
			
			ViewPropertyTenantController controller = loader.getController();
			controller.setInfoType(type);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
    }
}
