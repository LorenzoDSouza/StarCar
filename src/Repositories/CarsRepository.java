package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.Car;

public class CarsRepository {

	// get all cars <
	// register car <
	// get car by id <
	// update car by id 
	// delete car by id

	private DbConnection connection;
	private ArrayList<Car> stock;

	public CarsRepository() {
		connection = new DbConnection();
		stock = getAllCars();
	}

	public Car createCarLogic(ResultSet resultSet) {

		try {
			int car_Id = resultSet.getInt(1);
			String car_name = resultSet.getString(2);
			int brand_Id = resultSet.getInt(3);
			double price = resultSet.getDouble(4);

			Car car = Car.create(car_Id, car_name, brand_Id, price);

			return car;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Car> getAllCars() {
		ArrayList<Car> allCars = new ArrayList<Car>();
		String querrySelect = "SELECT * FROM car WHERE car_id = 1";

		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);

			while (resultSet.next()) {

				if (resultSet.getBoolean(5) == false) {
					Car car = createCarLogic(resultSet);
					allCars.add(car);
				}
			}
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry! The value position is invalid or the datatype missmatch.");
		}
		return allCars;
	}

	public Car register(Car carParam) {
		String addValueQuerry = "INSERT INTO car (car_name, brand_id, price) VALUES (" + carParam.getName() + ", "
				+ carParam.getBrand_Id() + ", " + carParam.getPrice() + ")";
		String getLastCarQuerry = "SELECT * FROM car ORDER BY car_id DESC LIMIT 1";
		
		try {
			stock.add(carParam);
			// querry to add the car
			ResultSet resultSetAddValue = connection.statement.executeQuery(addValueQuerry);
			
			ResultSet resultSetGetLastValue = connection.statement.executeQuery(getLastCarQuerry);
			
			Car car = createCarLogic(resultSetGetLastValue);
			return car;
			
		} catch (SQLException e) {
			return null;
		}

	}

	public Car getById(int id) {
		try {
			String getByIdQuerry = "SELECT * FROM car WHERE car_id = " + id;
			ResultSet resultSet = connection.statement.executeQuery(getByIdQuerry);
			
			Car car = createCarLogic(resultSet);
			
			return car;			
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean deleteById(int id) {
		try {
			String deleteByIdQuerry = "DELETE * FROM car WHERE car_id = " + id;
			ResultSet resultSet = connection.statement.executeQuery(deleteByIdQuerry);
			
			return true;			
		} catch (Exception e) {
			return false;
		}
	}

}
