package owner.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import utils.BaseController;
import utils.Constants;

public class ViewPropertyTenantController extends BaseController {

    @FXML
    private Button newButton;

    @FXML
    private Button maintainButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backButton;
    
    @FXML
    private TableColumn<ViewPropertyTenantBean, String> infoTable;
    
    private String infoType = Constants.TENANT;

    @FXML
    void initialize() {
    	
    	if(infoType.equals(Constants.TENANT)) {
//    		infoTable.get().get(0).setText("Tenants");
    	}
    	

    }

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
}
