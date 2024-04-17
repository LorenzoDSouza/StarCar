package Database;

import java.sql.*; 

public class DbConnection {//singleton java
	
	private String JDBCdriver;
	public static Connection connection;
	private String username;
	private String password;
	private String port;
	private String database;
	public static Statement statement;
	
	public DbConnection (String database) {

		this.JDBCdriver = "com.mysql.jdbc.Driver";
		this.username = "root";
		this.password = "enzo2015";
		this.port = "3306";
		this.database = database;
		
		try{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + database, username, password);
			this.statement = connection.createStatement();
		} catch (Exception e){
			System.out.println("Couldn't connect to the Data Base. Error: " + e.getMessage());
		}
	}

}
