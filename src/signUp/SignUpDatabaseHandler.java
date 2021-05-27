package signUp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DatabaseHandler;
import dto.Account;
import dto.Person;
import utils.Constants;

public class SignUpDatabaseHandler extends DatabaseHandler {

	private static final String ADD_NEW_PERSON = "insert into person(peusername, pepassowrd, peisowner, pefirstname, pelastname, peidnumber, peemail, pephone, pestatus) values (?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_PEID = "select peid from person where peidnumber = ? and pestatus = ?";
	private static final String ADD_NEW_ACCOUNT = "insert into account(acpeid, acbank, acholder, actype, acnumber, acbrcode) values (?,?,?,?,?,?)";

	public boolean signUpUser(Person person, String username, String password) {

		try {
			PreparedStatement addNewPerson = getDbConnection().prepareStatement(ADD_NEW_PERSON);
			addNewPerson.setString(1, username);
			addNewPerson.setString(2, password);
			addNewPerson.setBoolean(3, person.isOwner());
			addNewPerson.setString(4, person.getFirstName());
			addNewPerson.setString(5, person.getLastName());
			addNewPerson.setString(6, person.getIdNumber());
			addNewPerson.setString(7, person.getEmail());
			addNewPerson.setString(8, person.getPhone());
			addNewPerson.setInt(9, Constants.ACTIVE);

			if (addNewPerson.executeUpdate() > 0) {
				if (person.isOwner()) {
					System.out.println("New User Signed Up.");
					
					return addNewAccount(retrievePersonId(person.getIdNumber()), person.getAccounts());
				} else {
					System.out.println("New Person Added.");
				}
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int retrievePersonId(String idNumber) {
		try {
			PreparedStatement retrievePersonId = getDbConnection().prepareStatement(SELECT_PEID);
			retrievePersonId.setString(1, idNumber);
			retrievePersonId.setInt(2, Constants.ACTIVE);
			ResultSet rs = retrievePersonId.executeQuery();

			if (rs.next()) {
				return rs.getInt("PEID");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public boolean addNewAccount(int personId, List<Account> accounts) {
		try {
			PreparedStatement ps = getDbConnection().prepareStatement(ADD_NEW_ACCOUNT);
			ps.setInt(1, personId);
			for (Account account : accounts) {
				ps.setString(2, account.getBank());
				ps.setString(3, account.getAccountHolder());
				ps.setString(4, account.getAccountType());
				ps.setString(5, account.getAccountNumber());
				ps.setString(6, account.getBranchCode());
				if (ps.executeUpdate() > 0) {
					System.out.println("Account: " + account.getAccountNumber() + " successfully added.");
				} else {
					System.out.println("Account: " + account.getAccountNumber() + " failed to insert.");
				}

			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}
