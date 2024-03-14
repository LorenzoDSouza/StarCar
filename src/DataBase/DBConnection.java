package DataBase;

import java.sql.*; 

public class DBConnection {//singleton java
	
	private String JDBCdriver;
	private Connection con;
	private String username;
	private String password;
	private String port;
	private Statement stmt;
	
	public DBConnection () {

		this.JDBCdriver = "com.mysql.jdbc.Driver";
		this.username = "root";
		this.password = "enzo2015";
		this.port = "3306";
		
		try{
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/carros", username, password);
			this.stmt = con.createStatement();
			System.out.println("DB Connected with succes!");
		} catch (Exception e){
			System.out.println("Couldn't connect to the Data Base. Error: " + e.getMessage());
		}
	}

}
