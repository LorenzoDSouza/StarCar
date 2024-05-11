package Repositories;

import java.sql.PreparedStatement;
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

	public CarsRepository(String database) {
		connection = new DbConnection(database);
		stock = getAllCars();
	}

	public Car createCarLogic(ResultSet resultSet) {

		try {
			int car_id = resultSet.getInt("car_id");
			String car_name = resultSet.getString("car_name");
			int brand_id = resultSet.getInt("brand_id");
			double price = resultSet.getDouble("price");
			boolean sold = resultSet.getBoolean("sold");

			Car car = Car.create(car_id, car_name, brand_id, price, sold);

			return car;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Car> getAllCars() {
		ArrayList<Car> allCars = new ArrayList<Car>();
		String querrySelect = "SELECT * FROM car";

		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);

			while (resultSet.next()) {
				Car car = createCarLogic(resultSet);
				allCars.add(car);
			}
			return allCars;
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry!");
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get all the cars");
			return null;
		}

	}

	public ArrayList<Car> getStock() {
		return stock;
	}

	public Car register(Car carParam) {
		String insertValueQuerry = "INSERT INTO car (car_name, brand_id, price, sold) VALUES ('" + carParam.getName()
				+ "', " + carParam.getBrand_Id() + ", " + carParam.getPrice() + ", false)";

		try {
			connection.statement.executeUpdate(insertValueQuerry);

			Car car = getLastCarDb();
			stock.add(car);

			return car;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Car getById(int id) {
		String selectByIdQuerry = "SELECT * FROM car WHERE car_id = " + id;		
								
		try {
			if (!isValidId(id)) {
				throw new AppException("The car_id is invalid!");
			}

			ResultSet resultSet = connection.statement.executeQuery(selectByIdQuerry);

			if (resultSet.next()) {
				Car car = createCarLogic(resultSet);
				return car;
			} else {
				return null;//change by a future exception, after discover how the return of the next() method works
			}
		} catch (SQLException e) {
			System.out.println("Error while getting the car by ID (SQLException): " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to select the Car (AppException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to select the Car (RuntimeException): " + e.getMessage());
			return null;
		}
	}

	public boolean deleteById(int id) {
		String deleteByIdQuerry = "DELETE FROM car WHERE car_id = " + id;
		boolean deleted = false;

		try {
			connection.statement.executeUpdate(deleteByIdQuerry);

			for (Car car : stock) {
				if (car.getCar_Id() == id) {
					stock.remove(car);
					deleted = true;
					break;
				}
			}

			return true;
		} catch (SQLException e) {
			System.out.println("Couldn't delete the car in the database: " + e.getMessage());
			return false;
		}
	}

	public boolean deleteLastCar() {
		String deleteLastCar = "DELETE FROM car ORDER BY car_id DESC LIMIT 1";

		try {
			connection.statement.executeUpdate(deleteLastCar);

			return true;
		} catch (Exception e) {
			System.out.println("Couldn't delete the car in the database.");
			return false;
		}
	}

	public Car updatePrice(double new_price, int car_id) {// this method might be remade, cause its not working. need to
		String updatePriceQuerry = "UPDATE car SET price = " + new_price + " WHERE car_id = " + car_id;

		try {
			if (isValidId(car_id) != true) {
				throw new AppException("The car_id is invalid!");
			}
			if (new_price < 0) {
				throw new AppException("The price is invalid!");
			}

			int rowsAffected = connection.statement.executeUpdate(updatePriceQuerry);

			int indexOfUpdate = 0;

			for (int index = 0; index < stock.size(); index++) {
				if (stock.get(index).getCar_Id() == car_id) {
					stock.get(index).setPrice(new_price);
					indexOfUpdate = index;
					break;
				}
			}

			if (getById(car_id).getPrice() == new_price && stock.get(indexOfUpdate).getPrice() == new_price) {
				return stock.get(indexOfUpdate);
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
			System.out.println("Couldnt update price: " + e.getMessage());
			return null;
		}

		/*
		 * int rowsAffected = connection.statement.executeUpdate(updatePriceQuery);
		 * 
		 * if (rowsAffected > 0) { for (Car car : stock) { if (car.getCar_Id() ==
		 * car_id) { car.setPrice(new_price); return car; } } throw new
		 * AppException("Car with car_id " + car_id + " not found in stock."); } else {
		 * throw new AppException("Could not update the price of the car."); }
		 * 
		 * FOUND THIS DIFFERENT LOGIC THAT CAN BE USED AND IS OPTIMIZED!
		 */
	}

	public boolean getSoldValueByDatabase(int car_id) {

		String checkSoldQuerry = "SELECT sold FROM car WHERE car_id = " + car_id;

		try {
			if (isValidId(car_id) == false) {
				throw new AppException("The car_id is invalid!");
			}

			ResultSet resultSet = connection.statement.executeQuery(checkSoldQuerry);

			if (resultSet.next()) {
				boolean sold = resultSet.getBoolean("sold");

				return sold;
			} else {
				throw new AppException("Couldnt get the sold value of the car.");
			}

		} catch (AppException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was a problem with the querry in the database.");
			return false;
		}
	}

	public Car updateToSold(int car_id) {
		int indexOfUpdate = -1;
		String updatePriceQuerry = "UPDATE car SET sold = true WHERE car_id = " + car_id;

		try {

			if (isValidId(car_id) == false) {
				throw new AppException("The car_id is invalid!");
			}

			connection.statement.executeUpdate(updatePriceQuerry);

			for (Car car : stock) {
				if (car.getCar_Id() == car_id) {
					car.setSold(true);
					indexOfUpdate = stock.indexOf(car);
					break;
				}
			}

			if (stock.get(indexOfUpdate).getSoldValue() == true && getSoldValueByDatabase(car_id) == true
					&& indexOfUpdate >= 0) {
				return stock.get(indexOfUpdate);
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

	public Car updateName(String new_name, int car_id) {
		String updateNameQuerry = "UPDATE car SET car_name = '" + new_name + "' WHERE car_id = " + car_id; 
		int indexOfUpdate = -1;
		Car car;

		try {
			if (isValidId(car_id) != true) {
				throw new AppException("The car_id is invalid!");
			}
			if (new_name == null) {
				throw new AppException("The name is invalid!");
			}

			connection.statement.executeUpdate(updateNameQuerry);

			for (int index = 0; index < stock.size(); index++) {
				if (stock.get(index).getCar_Id() == car_id) {
					stock.get(index).setName(new_name);
					indexOfUpdate = index;
					break;
				}
			}

			if (getById(car_id).getName().equals(new_name) && stock.get(indexOfUpdate).getName().equals(new_name)) {
				return stock.get(indexOfUpdate);
			} else {
				throw new AppException("Couldnt update the car name.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (SQLException e) {
			System.err.println("Coulnd't execute the Querry. There was a problem with the Database: " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("Couldnt update the car name!");
			return null;
		}
	}

	public Car getLastCarDb() {// method for during using implementation
		try {
			String getLastCarQuerry = "SELECT * FROM car ORDER BY car_id DESC LIMIT 1;";

			ResultSet resultSet = connection.statement.executeQuery(getLastCarQuerry);

			if (resultSet.next()) {
				Car lastCar = createCarLogic(resultSet);
				return lastCar;
			} else {
				throw new AppException("The table is empty");
			}

		} catch (SQLException e) {
			System.out.println("There was a problem to execute the querry: " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get the car: " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to get the car: " + e.getMessage());
			return null;
		}
	}

	public int getLastId() {// method for database and during use implemenmtation
		String getLastId = "SELECT car_id FROM car ORDER BY car_id DESC LIMIT 1";
		try {
			ResultSet resultSet = connection.statement.executeQuery(getLastId);

			if (resultSet.next()) {
				return resultSet.getInt("car_id");
			} else {
				throw new AppException("Couldnt execute the querry. None row was returned");
			}

		} catch (SQLException e) {
			System.out.println("Couldn't execute the querry: " + e.getMessage());
			return 0;
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return 0;
		}

	}

	public boolean updateStock() {
		try {
			stock = getAllCars();
			return true;
		} catch (RuntimeException e) {
			System.out.println("Coulnd't update the stock: " + e.getMessage());
			return false;
		}
	}

	public boolean isValidId(int car_id) {
		boolean stockBoolean = false;
		boolean databaseBoolean = false;

		String getIdsQuerry = "SELECT car_id FROM car WHERE car_id = " + car_id;
		try {
			ResultSet resultSet = connection.statement.executeQuery(getIdsQuerry);

			if (resultSet.next()) {
				databaseBoolean = true;
			}

			for (Car car : stock) {
				if (car_id == car.getCar_Id()) {
					stockBoolean = true;
					break;
				}
			}

			return stockBoolean && databaseBoolean;

		} catch (SQLException e) {
			System.out.println("There was a problem with the querry: " + e.getMessage());
			return false;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to validate the id: " + e.getMessage());
			return false;
		}

	}

	/*
	 * public boolean carRelatedToSale() {
	 * 
	 * }
	 */

}
