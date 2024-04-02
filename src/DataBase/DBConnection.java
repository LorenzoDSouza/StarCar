package DataBase;

import java.sql.*; 

public class DBConnection {//singleton java
	
	private String JDBCdriver;
	public static Connection connection;
	private String username;
	private String password;
	private String port;
	public static Statement statement;
	
	public DBConnection () {

		this.JDBCdriver = "com.mysql.jdbc.Driver";
		this.username = "root";
		this.password = "enzo2015";
		this.port = "3306";
		
		try{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/carros", username, password);
			this.statement = connection.createStatement();
			System.out.println("DB Connected with succes!");
		} catch (Exception e){
			System.out.println("Couldn't connect to the Data Base. Error: " + e.getMessage());
		}
	}

}
