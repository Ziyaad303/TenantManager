package owner.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.BaseController;

public class HomeController extends BaseController{

    @FXML
    private Button updateDetailsButton, propertyButton, tenantButton, createStatementButton, exitButton, archivesButton;

    @FXML
    void initialize() {
    	
    	tenantButton.setOnAction(event -> {
    		hideScreen(tenantButton);
    		signUpPerson(false);
    	});

    }
}
