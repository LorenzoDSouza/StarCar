package DataBase;

import java.util.*;
import java.sql.*;

public interface Querrys {
	
	public static int selectInt(String table, String column, int index, String id, Statement stmt) {
		int result;
		String q = "SELECT " + column + " FROM " + table + " WHERE " + id + " = " + index;
		
		try {
			ResultSet rs = stmt.executeQuery(q);
			result = rs.getInt(column);
			
			
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value position is invalid or the datatype missmatch.");
			result = 0;
		}
		
		
		return result;
	}
	
}
