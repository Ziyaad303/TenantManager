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
	
	Connection dbConnection;
	
	public Connection getDbConnection() throws ClassNotFoundException, SQLException{
		String connectionString = "jdbc:mysql://" + dbHost + ":"
				+ dbPort + "/" + dbName;
		
//		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
		
		return dbConnection;
	}

}
