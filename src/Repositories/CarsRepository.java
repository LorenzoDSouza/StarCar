package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.Car;

public class CarsRepository {

	// get all cars <
	// register car
	// get car by id
	// update car by id
	// delete car by id

	private DbConnection connection;
	private ArrayList<Car> stock;

	public CarsRepository() {
		connection = new DbConnection();
		stock = getAllCars();
	}

	public ArrayList<Car> getAllCars() {
		ArrayList<Car> allCars = new ArrayList<Car>();
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
					allCars.add(car);
				}
			}
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value position is invalid or the datatype missmatch.");
		}
		return allCars;
	}
	
	public Car register(Car carParam) {
		try {
			
			stock.add(carParam);
			//querry to add the car
			
			
			return car;
		} catch (Exception e) {
			System.out.println("Couldn't add the car (Id: " + car.getCar_Id() + ") to stock!");
			valid = false;
		}
	}

}
