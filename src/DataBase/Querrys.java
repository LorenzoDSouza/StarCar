package DataBase;

import java.util.*;
import java.util.ArrayList;
import java.sql.*;
import Entities.*;

public class Querrys {

	public ArrayList<Car> selectCars(Statement statement) {

		ArrayList<Car> result = new ArrayList<Car>();
		String querrySelect = "SELECT * FROM car WHERE car_id = 1";

		try {
			ResultSet resultSet = statement.executeQuery(querrySelect);

			for (int index = 1; resultSet.next(); index++) {

				if (resultSet.getBoolean(5) == false) {

					int car_Id = resultSet.getInt(1);
					String car_name = resultSet.getString(2);
					int brand_Id = resultSet.getInt(3);
					double price = resultSet.getDouble(4);
					boolean sold = resultSet.getBoolean(5);

					Car carX = new Car(car_Id, car_name, brand_Id, price, sold);
					result.add(carX);
				}
			}

		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value position is invalid or the datatype missmatch.");

		}

		return result;
	}

	public ArrayList<Customer> selectCustomer(Statement statement) {

		ArrayList<Customer> result = new ArrayList<Customer>();
		String querrySelect = "SELECT * FROM customer WHERE customer_id  = 1";

		try {
			ResultSet resultSet = statement.executeQuery(querrySelect);

			for (int index = 1; resultSet.next(); index++) {
				int customer_id = resultSet.getInt(1);
				String name = resultSet.getString(2);

				Customer customerX = new Customer(customer_id, name);
				result.add(customerX);
			}

		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value's position is invalid or the datatype missmatch.");

		}

		return result;
	}

	public ArrayList<SalesPerson> selectSalesPerson(Statement statement) {

		ArrayList<SalesPerson> result = new ArrayList<SalesPerson>();
		String querrySelect = "SELECT * FROM customer WHERE salesPerson_id = 1";

		try {
			ResultSet resultSet = statement.executeQuery(querrySelect);

			for (int index = 1; resultSet.next(); index++) {
				int salesPerson_id = resultSet.getInt(4);
				String name = resultSet.getString(1);
				double payment = resultSet.getDouble(3);

				SalesPerson salesPerson = new SalesPerson(salesPerson_id, name, payment);
				result.add(salesPerson);
			}

		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value's position is invalid or the datatype missmatch.");

		}

		return result;
	}
	
	//above ar all the querrys to import into object arrays
	
	public boolean removeCarFromTable(Car car, Statement statement) {
		try {
			String querry = "DELETE FROM car WHERE car_id = " + car.getCar_Id();
			
			
			
			return true;
		} catch(Exception e) {
			
			return false;
		}
	}
	
	

}
