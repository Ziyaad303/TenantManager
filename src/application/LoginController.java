package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Hyperlink signUpHyperlink, forgottenPasswordHyperlink;

    @FXML
    private Button loginButton;

    @FXML
    void initialize() {

    }
}
