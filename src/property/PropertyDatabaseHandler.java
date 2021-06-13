package property;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseHandler;
import dto.Property;
import utils.Constants;

public class PropertyDatabaseHandler extends DatabaseHandler {
	
	private static final String RETRIEVE_PROPERTY = "select * from property where prid = ? and prstatus = ?";
	
	public Property retrieveProperty(int propertyId) {
		Property property = null;
		
		try(PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_PROPERTY)){
			ps.setInt(1, propertyId);
			ps.setInt(2, Constants.ACTIVE);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				property = new Property();
				property.setPropertyId(rs.getInt("PRID"));
				property.setPersonId(rs.getInt("PRPEID"));
				property.setComplexName(rs.getString("PRCOMPLEX"));
				property.setStreetAddress(rs.getString("PRSTADD"));
				property.setSuburb(rs.getString("PRSUBURB"));
				property.setAreaCode(rs.getString("PRAREACODE"));
				property.setProvince(rs.getString("PRPROVINCE"));
				property.setPurchaseDate(rs.getString("PRPURCHDATE"));
				property.setFirstWaterReading(rs.getInt("PRWATERSTART"));
				property.setFirstElecReading(rs.getInt("PRELECSTART"));
				property.setStatus(rs.getInt(rs.getInt("PRSTATUS")));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return property;
	}

}
