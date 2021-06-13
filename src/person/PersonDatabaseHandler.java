package person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseHandler;
import dto.Person;
import utils.Constants;

public class PersonDatabaseHandler extends DatabaseHandler {
	
	private static final String RETRIEVE_PERSON = "select * from person where peid = ? and pestatus = ?";
	
	public Person retrievePerson(int personId) {
		Person person = null;
		
		try(PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_PERSON)){
			ps.setInt(1, personId);
			ps.setInt(2, Constants.ACTIVE);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				person = new Person();
				person.setPersonId(rs.getInt("peid"));
				person.setOwner(rs.getBoolean("peisowner"));
				person.setFirstName(rs.getString("pefirstname"));
				person.setLastName(rs.getString("pelastname"));
				person.setIdNumber(rs.getString("peidnumber"));
				person.setEmail(rs.getString("peemail"));
				person.setPhone(rs.getString("pephone"));
				person.setStatus(rs.getInt("pestatus"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

}
