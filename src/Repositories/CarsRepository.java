package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.Car;
import Exceptions.AppException;

public class CarsRepository {

	// get all cars <
	// register car <
	// get car by id <
	// update car by id !!
	// delete car by id <

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

			stock.add(carParam);
			return car;

		} catch (SQLException e) {
			return null;
		}

	}

	public Car getById(int id) {// maybe could do it with consult at the stock
								// to verify by returning, should make a doublw verification in th database and
								// in the stock!!! (very importannt)
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
		String deleteByIdQuerry = "DELETE * FROM car WHERE car_id = " + id;
		boolean removed = false;

		try {
			ResultSet resultSet = connection.statement.executeQuery(deleteByIdQuerry);

			for (int index = 0; index < stock.size() || removed == true; index++) {
				if (stock.get(index).getCar_Id() == id) {
					stock.remove(index);
					removed = true;
				}
			}

			return true;
		} catch (Exception e) {
			System.out.println("Couldn't delete the car in the database.");
			return false;
		}
	}

	public Car updatePrice(double new_price, Car car) {
		try {
			if (car != null) {
				throw new AppException("The car is invalid!");
			}
			if (new_price < 0) {
				throw new AppException("The price is invalid!");
			}

			int car_id = car.getCar_Id();
			String updatePriceQuerry = "UPDATE car SET price = " + new_price + " WHERE car_id = " + car_id;
			ResultSet resultSetUpdatePrice = connection.statement.executeQuery(updatePriceQuerry);

			stock.get(stock.indexOf(car)).setPrice(new_price);

			if (getById(car_id).getPrice() == new_price && stock.get(stock.indexOf(car)).getPrice() == new_price) {
				return stock.get(stock.indexOf(car));
			} else {
				throw new AppException("Couldnt update the price of the car.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (SQLException e) {
			System.err.println("Coulnd't execute the Querry. There was a problem with the Database!");
			return null;
		} catch (RuntimeException e) {
			System.out.println("Couldnt update price!");
			return null;
		}
	}

	public boolean getSoldValueByDatabase(Car car) {
		
		try {
		if (car != null) {
			throw new AppException("The car is invalid!");
			}
		
		int car_id = car.getCar_Id();
		String checkSoldQuerry = "SELECT sold FROM car WHERE car_id = " + car_id;
		
		ResultSet resultSet = connection.statement.executeQuery(checkSoldQuerry);
		boolean sold = resultSet.getBoolean("sold");
		
		return sold;
		} catch (AppException e){
			System.out.println(e.getMessage());
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was a problem with the querry in the database.");
			return false;
		}
	}

	public Car updateToSold(Car car) {
		try {
			if (car != null) {
				throw new AppException("The car is invalid!");
			}

			if (stock.contains(car) != true) {
				throw new AppException("The car isn't in the stock!");
			}
			int car_id = car.getCar_Id();
			String updatePriceQuerry = "UPDATE car SET sold = true WHERE car_id = " + car_id;
			ResultSet resultSetUpdatePrice = connection.statement.executeQuery(updatePriceQuerry);
			
			stock.remove(stock.indexOf(car));
			
			car.setSold(true);
			
			if(stock.contains(car) == true && getSoldValueByDatabase(car) == true) {
				return car;
			} else {
				throw new AppException("There was a problem to set the car to sold!");
			}

		} catch (AppException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (SQLException e) {
			System.err.println("Coulnd't execute the Querry. There was a problem with the Database!");
			return null;
		} catch (RuntimeException e) {
			System.out.println("Couldnt update to sold.");
			return null;
		}
	}
}
