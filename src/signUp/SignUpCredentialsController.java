package signUp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpCredentialsController {

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;
    
    @FXML
    private Button registerButton;
    
    @FXML
    void initialize() {
    	System.out.println("Sign UP with credentials");
    	
    	registerButton.setOnAction(event -> {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("SignUp.fxml"));
    		Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}

    		Scene scene = new Scene(parent);

    		SignUpController controller = loader.getController();
    		System.out.println("The Controller is: " + controller.toString());
    		System.out.println(usernameInput.getText().trim() + " " +  passwordInput.getText().trim());
    		controller.setCredentials(usernameInput.getText().trim(), passwordInput.getText().trim());
    	});
    	
    	
    }
    
    public String getCredentials() {
    	return usernameInput.getText().trim() + "$" + passwordInput.getText().trim();
    }
    

}
