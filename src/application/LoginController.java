package application;

import java.io.IOException;

import dto.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import signUp.SignUpController;
import utils.BaseController;

public class LoginController extends BaseController {

	@FXML
	private TextField usernameInput;

	@FXML
	private PasswordField passwordInput;

	@FXML
	private Hyperlink signUpHyperlink, forgottenPasswordHyperlink;

	@FXML
	private Button loginButton;

	private LoginDatabaseHandler dbHandler;
	private String username, password;

	@FXML
	void initialize() {

		dbHandler = new LoginDatabaseHandler();

		loginButton.setOnAction(event -> loginUser());
		signUpHyperlink.setOnAction(event -> signUpUser());

	}

	private void loginUser() {
		username = usernameInput.getText().trim();
		password = passwordInput.getText().trim();
		Person person = dbHandler.signIn(username, password);

		if (person != null) {
			hideScreen(loginButton);
			try {
//				loadScreen("");
				System.out.println("Login Successful: " + person.getAccounts().get(0).getAccountNumber());
			} catch (Exception e) { // ioexception
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid Credentials");
		}
	}
	
	private void signUpUser() {
		hideScreen(loginButton);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/signUp/SignUp.fxml"));
			Parent root = (Parent) loader.load();
			
			SignUpController controller = loader.getController();
			controller.setOwner(true);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
