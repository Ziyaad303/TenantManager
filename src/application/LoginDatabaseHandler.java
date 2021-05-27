package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import dto.Account;
import dto.Person;
import utils.Constants;

public class LoginDatabaseHandler extends DatabaseHandler {
	
	private static final String SIGN_IN_USER = "select * from person where peusername = ? and pepassword = ? and pestatus = ?";
	private static final String SELECT_ACCOUNTS = "select * from account where acpeid = ?";
	
	public Person signIn(String username, String password) {
		Person person;
		
		try(PreparedStatement ps = getDbConnection().prepareStatement(SIGN_IN_USER)){
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, Constants.ACTIVE);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				person = new Person();
				person.setPersonId(rs.getInt("PEID"));
				person.setOwner(rs.getBoolean("PEISOWNER"));
				person.setFirstName(rs.getString("PEFIRSTNAME"));
				person.setLastName(rs.getString("PELASTNAME"));
				person.setIdNumber(rs.getString("PEIDNUMBER"));
				person.setEmail(rs.getString("PEEMAIL"));
				person.setPhone(rs.getString("PEPHONE"));
				person.setStatus(rs.getInt("PESTATUS"));
				person.setAccounts(retrieveUserAccounts(person.getPersonId()));
				return person;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private List<Account> retrieveUserAccounts(int personId){
		List<Account> accounts = new ArrayList<Account>();
		Account account;
		try(PreparedStatement ps = getDbConnection().prepareStatement(SELECT_ACCOUNTS)){
			ps.setInt(1, personId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				account = new Account();
				account.setAccountId(rs.getInt("ACID"));
				account.setBank(rs.getString("ACBANK"));
				account.setAccountHolder(rs.getString("ACHOLDER"));
				account.setAccountType(rs.getString("ACTYPE"));
				account.setAccountNumber(rs.getString("ACNUMBER"));
				account.setBranchCode(rs.getString("ACBRCODE"));
				accounts.add(account);
			}
			return accounts;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
