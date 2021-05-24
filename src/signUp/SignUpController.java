package signUp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.BaseController;

public class SignUpController extends BaseController{

    @FXML
    private TextField firstNameInput, lastNameInput, idNumberInput, emailInput, phoneInput;

    @FXML
    private AnchorPane credentialsPane, pane;

    @FXML
    private Button registerButton;

    private String username, password;
    @FXML
    void initialize() {
    	
    	try {
    		pane = FXMLLoader.load(getClass().getResource("SignUpCredentials.fxml"));
    		credentialsPane.getChildren().setAll(pane);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}

    }
    
    public void setCredentials(String username, String password) {
    	this.username = username;
    	this.password = password;
    	
    	System.out.println("SignUpController: ");
    	System.out.println("FirstName: " + firstNameInput.getText());
    	System.out.println("SecondName: " + lastNameInput.getText());
    	System.out.println("idNumber : " + idNumberInput.getText());
    	System.out.println("Username : " + username);
    	
    	
    }
}
