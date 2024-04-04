package Database;

import java.util.*;
import java.util.ArrayList;
import java.sql.*;
import Entities.*;

public class Querrys {

	private DbConnection connection;

	public Querrys() {
		connection = new DbConnection();
	}

	public ArrayList<Car> selectCars() {

		ArrayList<Car> result = new ArrayList<Car>();
		String querrySelect = "SELECT * FROM car WHERE car_id = 1";

		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);

			while (resultSet.next()) {

				if (resultSet.getBoolean(5) == false) {

					int car_Id = resultSet.getInt(1);
					String car_name = resultSet.getString(2);
					int brand_Id = resultSet.getInt(3);
					double price = resultSet.getDouble(4);
					boolean sold = resultSet.getBoolean(5);

					Car car = new Car(car_Id, car_name, brand_Id, price, sold);
					result.add(car);
				}
			}

		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value position is invalid or the datatype missmatch.");

		}

		return result;
	}

	public ArrayList<Customer> selectCustomer() {

		ArrayList<Customer> result = new ArrayList<Customer>();
		String querrySelect = "SELECT * FROM customer WHERE customer_id  = 1";

		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);

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

	public ArrayList<SalesPerson> selectSalesPerson() {

		ArrayList<SalesPerson> result = new ArrayList<SalesPerson>();
		String querrySelect = "SELECT * FROM customer WHERE salesPerson_id = 1";

		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);

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

	// above ar all the querrys to import into object arrays

	public boolean removeCarFromTable(Car car) {
		try {
			String querryDelete = "DELETE FROM car WHERE car_id = " + car.getCar_Id();
			ResultSet resultSet = connection.statement.executeQuery(querryDelete);
			
			return true;
		} catch (Exception e) {
			System.out.println("Couldn't remove the car from the table! chec");
			
			return false;
		}
	}

}
