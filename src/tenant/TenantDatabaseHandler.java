package tenant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseHandler;
import dto.Tenant;
import utils.Constants;

public class TenantDatabaseHandler extends DatabaseHandler{
	
	private static final String RETRIEVE_TENANT = "select * from tenant where tenantId = ? and testatus = ?";
	
	public Tenant retrieveTenant(int tenantId) {
		Tenant tenant = null;
		try(PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_TENANT)){
			ps.setInt(1, tenantId);
			ps.setInt(2, Constants.ACTIVE);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				tenant = new Tenant();
				tenant.setTenantId(rs.getInt("TEID"));
				tenant.setPersonId(rs.getInt("TEPEID"));
				tenant.setPropertyId(rs.getInt("TEPRID"));
//				return tenant;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenant;
	}

}
