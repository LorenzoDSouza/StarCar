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

			Car car = Car.create(car_id, car_name, brand_id, price);

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

	public ArrayList<Car> getStock() {
		return stock;
	}

	public Car register(Car carParam) {
	    String addValueQuery = "INSERT INTO car (car_name, brand_id, price, sold) VALUES ('" + carParam.getName() + "', "
	            + carParam.getBrand_Id() + ", " + carParam.getPrice() + ", false)";

	    try {
	        // Execute the insertion query
	        connection.statement.executeUpdate(addValueQuery);

	        // Get the last inserted car
	        Car car = getLastCarDb();

	        // Add the car to the stock
	        stock.add(car);

	        return car;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public Car getById(int id) {// maybe could do it with consult at the stock
								// to verify by returning, should make a doublw verification in th database and
								// in the stock!!! (very importannt)
		try {
			String getByIdQuerry = "SELECT * FROM car WHERE car_id = " + id;
			ResultSet resultSet = connection.statement.executeQuery(getByIdQuerry);

			if (resultSet.next()) {
				Car car = createCarLogic(resultSet);
				return car;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error while getting car by ID: " + e.getMessage());
			return null;

		} catch (Exception e) {
			System.out.println("Couldnt get the car in the database: " + e.getMessage());
			return null;
		}
	}

	public boolean deleteById(int id) {
		String deleteByIdQuerry = "DELETE * FROM car WHERE car_id = " + id;
		boolean removed = false;

		try {
			connection.statement.executeUpdate(deleteByIdQuerry);

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

	public Car updatePrice(double new_price, Car car) {//this method might be remade, cause its not working
		try {
			if (car != null) {
				throw new AppException("The car is invalid!");
			}
			if (new_price < 0) {
				throw new AppException("The price is invalid!");
			}

			int car_id = car.getCar_Id();
			String updatePriceQuerry = "UPDATE car SET price = " + new_price + " WHERE car_id = " + car_id;
			connection.statement.executeUpdate(updatePriceQuerry);

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
		} catch (AppException e) {
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
			connection.statement.executeUpdate(updatePriceQuerry);

			stock.remove(stock.indexOf(car));

			car.setSold(true);

			if (stock.contains(car) == true && getSoldValueByDatabase(car) == true) {
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

	public Car updateName(String new_name, Car car) {
		try {
			if (car != null) {
				throw new AppException("The car is invalid!");
			}
			if (new_name != null) {
				throw new AppException("The name is invalid!");
			}

			int car_id = car.getCar_Id();
			String updateNameQuerry = "UPDATE car SET name = " + new_name + " WHERE car_id = " + car_id;
			ResultSet resultSetUpdatePrice = connection.statement.executeQuery(updateNameQuerry);

			stock.get(stock.indexOf(car)).setName(new_name);

			if (getById(car_id).getName() == new_name && stock.get(stock.indexOf(car)).getName() == new_name) {
				return stock.get(stock.indexOf(car));
			} else {
				throw new AppException("Couldnt update the car name.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (SQLException e) {
			System.err.println("Coulnd't execute the Querry. There was a problem with the Database!");
			return null;
		} catch (RuntimeException e) {
			System.out.println("Couldnt update the car name!");
			return null;
		}
	}

	public Car getLastCarDb() {//method for during using implementation
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
	
	public int getLastId() {//method for database and during use implemenmtation
		String getLastId = "SELECT car_id FROM car ORDER BY car_id DESC LIMIT 1";
		try {
			ResultSet resulSet = connection.statement.executeQuery(getLastId);
			return resulSet.getInt("car_id");
		} catch (SQLException e) {
			System.out.println("Couldn't execute the querry: " + e.getMessage());
			return 0;
		}
		
	}
	
	public boolean updateStock() {
		try{
			stock = getAllCars();
			return true;
		} catch(RuntimeException e) {
			System.out.println("Coulnd't update the stock: "+ e.getMessage());
			return false;
		}
	}
	

}
