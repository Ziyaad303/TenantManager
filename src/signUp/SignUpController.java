package signUp;

import java.util.ArrayList;
import java.util.List;

import dto.Account;
import dto.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.BaseController;

public class SignUpController extends BaseController{

    @FXML
    private TextField firstNameInput, lastNameInput, idNumberInput, emailInput, phoneInput, usernameInput;
    
    @FXML
    private TextField bankInput, accountHolderInput, accountTypeInput, accountNumberInput, branchCodeInput;

    @FXML
    private AnchorPane credentialsPane;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button registerButton;

    private String username, password;
    private boolean isOwner;
    
    @FXML
    void initialize() {
    	
    	registerButton.setOnAction(event -> {
    		Person person = capturePerson();
    		
    	});
    	
    }
    
    private Person capturePerson() {
    	Person person = new Person();
    	person.setFirstName(firstNameInput.getText().trim());
    	person.setLastName(lastNameInput.getText().trim());
    	person.setIdNumber(idNumberInput.getText().trim());
    	person.setEmail(emailInput.getText().trim());
    	person.setPhone(phoneInput.getText().trim());
    	person.setOwner(isOwner);
    	
    	Account account = new Account();
    	account.setBank(bankInput.getText().trim());
    	account.setAccountHolder(accountHolderInput.getText().trim());
    	account.setAccountType(accountTypeInput.getText().trim());
    	account.setAccountNumber(accountNumberInput.getText().trim());
    	account.setBranchCode(branchCodeInput.getText().trim());
    	
    	List<Account> accounts = new ArrayList<Account>();
    	accounts.add(account);
    	
    	person.setAccounts(accounts);
    	return person;
    }

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
		credentialsPane.setVisible(isOwner);
	}
}
