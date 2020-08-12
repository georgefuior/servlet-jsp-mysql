package ro.georgefuior.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

	private static final String URL = "jdbc:mysql://localhost:8889/test?serverTimezone=UTC";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static Connection connection = null;

	public static Connection openConnection() {

		if(connection!=null) {
			return connection;
		}else {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				System.out.println("Succesfully connected to database");
				return connection;
			}catch(SQLException e) {
				System.out.println("Failed to connect to database: "+e.getMessage());
				return null;
			}catch(ClassNotFoundException e) {
				System.out.println("Failed to load the driver: "+e.getMessage());
				return null;
			}
		}

	}
}
