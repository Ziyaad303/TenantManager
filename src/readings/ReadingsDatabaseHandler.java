package readings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import dto.ElectricityReading;
import dto.WaterReading;

public class ReadingsDatabaseHandler extends DatabaseHandler {

	private static final String INSERT_WATER_READINGS = "insert into wreadings values(?,?,?,?,?)";
	private static final String INSERT_ELEC_READINGS = "insert into ereadings values(?,?,?,?,?)";

	private static final String SELECT_NEXT_WATER_SEQ = "select max(wrseq) + 1 where wrprid = ? and wrteid = ?";
	private static final String SELECT_NEXT_ELEC_SEQ = "select max(erseq) + 1 where erprid = ? and erteid = ?";

	private static final String RETRIEVE_WATER_READINGS = "select * from wreadings where wrprid = ? and wrteid = ? order by wrseq asc";
	private static final String RETRIEVE_ELEC_READINGS = "select * from ereadings where erprid = ? and erteid = ? order by erseq asc";

//	private static final String RETRIEVE_TOTAL_WATER_USAGE = "select * from wreadings where wrprid = ? and wrteid = ? and wrdate = ? order by wrseq asc";
//	private static final String RETRIEVE_TOTAL_ELEC_USAGE = "select * from ereadings where erprid = ? and erteid = ? and erdate = ? order by erseq asc";

	public boolean insertReadings(WaterReading waterReading, ElectricityReading elecReading) {
		try (Connection conn = getDbConnection();
				PreparedStatement insertWater = conn.prepareStatement(INSERT_WATER_READINGS);
				PreparedStatement insertElec = conn.prepareStatement(INSERT_ELEC_READINGS);) {

			insertWater.setInt(1, waterReading.getPropertyId());
			insertWater.setInt(2, waterReading.getTenantId());
			insertWater.setString(3, waterReading.getReadingDate());
			insertWater.setInt(4, waterReading.getUnitsUsed());
			insertWater.setInt(5, waterReading.getSequence());

			insertElec.setInt(1, elecReading.getPropertyId());
			insertElec.setInt(2, elecReading.getTenantId());
			insertElec.setString(3, elecReading.getReadingDate());
			insertElec.setInt(4, elecReading.getUnitsUsed());
			insertElec.setInt(5, elecReading.getSequence());

			if ((insertWater.executeUpdate() > 0) && (insertElec.executeUpdate() > 0)) {
				return true;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getNextReadingsSeq(int propertyId, int tenantId) {

		try (Connection conn = getDbConnection();
				PreparedStatement selectNextWaterSeq = conn.prepareStatement(SELECT_NEXT_WATER_SEQ);
				PreparedStatement selectNextElecSeq = conn.prepareStatement(SELECT_NEXT_ELEC_SEQ);) {

			selectNextWaterSeq.setInt(1, propertyId);
			selectNextWaterSeq.setInt(2, tenantId);
			ResultSet waterRs = selectNextWaterSeq.executeQuery();
			selectNextElecSeq.setInt(1, propertyId);
			selectNextElecSeq.setInt(2, tenantId);
			ResultSet elecRs = selectNextElecSeq.executeQuery();

			if (waterRs.next() && elecRs.next()) {
				int nextWaterSeq = waterRs.getInt("WRSEQ");
				int nextElecSeq = elecRs.getInt("ERSEQ");

				if (nextWaterSeq == nextElecSeq) {
					return nextWaterSeq;
				} else {
					return nextWaterSeq > nextElecSeq ? nextWaterSeq : nextElecSeq;
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<WaterReading> retrieveWaterReadings(int propertyId, int tenantId) {
		List<WaterReading> waterReadings = new ArrayList<WaterReading>();
		try (PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_WATER_READINGS)) {
			ps.setInt(1, propertyId);
			ps.setInt(2, tenantId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				WaterReading reading = new WaterReading();
				reading.setPropertyId(rs.getInt("WRPRID"));
				reading.setTenantId(rs.getInt("WRTEID"));
				reading.setReadingDate(rs.getString("WRDATE"));
				reading.setUnitsUsed(rs.getInt("WRUNITS"));
				reading.setSequence(rs.getInt("WRSEQ"));
				waterReadings.add(reading);
			}

			return waterReadings;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ElectricityReading> retrieveElectricityReadings(int propertyId, int tenantId) {
		List<ElectricityReading> elecReadings = new ArrayList<ElectricityReading>();
		try (PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_ELEC_READINGS)) {
			ps.setInt(1, propertyId);
			ps.setInt(2, tenantId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ElectricityReading reading = new ElectricityReading();
				reading.setPropertyId(rs.getInt("ERPRID"));
				reading.setTenantId(rs.getInt("ERTEID"));
				reading.setReadingDate(rs.getString("ERDATE"));
				reading.setUnitsUsed(rs.getInt("ERUNITS"));
				reading.setSequence(rs.getInt("ERSEQ"));
				elecReadings.add(reading);
			}

			return elecReadings;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int retrieveTenantsWaterUsageUntilDate(int propertyId, int tenantId, String untilDate) {

		try (PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_WATER_READINGS)) {
			ps.setInt(1, propertyId);
			ps.setInt(2, tenantId);
			ResultSet rs = ps.executeQuery();
			int totalUsage = 0;
			while (rs.next()) {
				do {
					totalUsage += rs.getInt("WRUNITS");
				} while (!rs.getString("WRDATE").equalsIgnoreCase(untilDate));
			}
			return totalUsage;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int retrieveTenantsElecUsageUntilDate(int propertyId, int tenantId, String untilDate) {

		try (PreparedStatement ps = getDbConnection().prepareStatement(RETRIEVE_ELEC_READINGS)) {
			ps.setInt(1, propertyId);
			ps.setInt(2, tenantId);
			ResultSet rs = ps.executeQuery();
			int totalUsage = 0;
			while (rs.next()) {
				do {
					totalUsage += rs.getInt("ERUNITS");
				} while (!rs.getString("ERDATE").equalsIgnoreCase(untilDate));
			}

			return totalUsage;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
