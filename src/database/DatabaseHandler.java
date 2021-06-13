package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
	protected String dbHost = "localhost";
	protected String dbPort = "3306";
	protected String dbUser = "root";
	protected String dbPass = "Ziyaad303!";
	protected String dbName = "invoice";
	protected String sqliteDriverName = "org.sqlite.JDBC";
	
	Connection dbConnection;
	
	public Connection getDbConnection() throws ClassNotFoundException, SQLException{
//		String connectionString = "jdbc:mysql://" + dbHost + ":"
//				+ dbPort + "/" + dbName;
		String connectionString = "jdbc:sqlite:C:\\Users\\Ziyaad\\OCDev\\Eclipse workspace\\misc\\TenantManager\\TenantManagerDb.db";
		
		Class.forName(sqliteDriverName);
		
		dbConnection = DriverManager.getConnection(connectionString);
		return dbConnection;
	}

}
