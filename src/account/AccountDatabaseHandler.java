package account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseHandler;
import dto.Account;

public class AccountDatabaseHandler extends DatabaseHandler {
	
	private static final String RETRIEVE_ACTIVE_ACCOUNTS = "select * from account where acpeid = ?"; // and acstatus = ?";
	
	public Account retrieveActiveAccount(int personId) {
		Account account = null;
		try(PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_ACTIVE_ACCOUNTS)){
			
			ps.setInt(1, personId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				account = new Account();
				account.setBank(rs.getString("ACBANK"));
				account.setAccountHolder(rs.getString("ACHOLDER"));
				account.setAccountType(rs.getString("ACTYPE"));
				account.setAccountNumber(rs.getString("ACNUMBER"));
				account.setBranchCode(rs.getString("ACBRCODE"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

}
