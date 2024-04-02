package DataBase;

import java.util.*;
import java.util.ArrayList;
import java.sql.*;
import Entities.*;

public class Querrys {
	
	
	public ArrayList<Car> selectCars (String table, String id, int index_id, Statement statement) {
		
		ArrayList<Car> result = new ArrayList<Car>();
		String querrySelect = "SELECT * FROM " + table + " WHERE " + id + " = " + index_id;
			
		try {			
			ResultSet resultSet = statement.executeQuery(querrySelect);
			
			for(int index = 1; resultSet.next(); index++) {
			int car_Id = resultSet.getInt(1);
			String car_name = resultSet.getString(2);
			int brand_Id = resultSet.getInt(3);
			double price = resultSet.getDouble(4);
			byte sold = resultSet.getByte(5);
			
			Car carX = new Car(car_Id, car_name, brand_Id, price, sold);
			result.add(carX);
			}
			
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value position is invalid or the datatype missmatch.");
			
		}
		
		return result;
	}
	
	
	
	
}
