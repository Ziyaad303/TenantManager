package application;

import java.io.IOException;

import dto.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
				loadScreen("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid Credentials");
		}
	}
	
	private void signUpUser() {
		hideScreen(loginButton);
		try {
			loadScreen("/signUp/SignUp.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
