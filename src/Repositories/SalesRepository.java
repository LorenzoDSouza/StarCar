package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Repositories.*;
import Entities.*;
import Exceptions.AppException;

public class SalesRepository {

	private DbConnection connection;
	private ArrayList<Sale> sales;
	// getAllSales -
	// register (need to have check if the car is already related to a sale) --
	// getById
	// deleteById
	// getLastSale -
	// deleteByIdGettingCarBack
	// getLastId
	// deleteLastSale
	// idIsValid
	// updateSalesPersonId
	// updateCustomerId
	// updateCarId

	public SalesRepository(String database) {
		connection = new DbConnection(database);
		sales = getAllSales();
	}

	public Sale createSaleLogic(ResultSet resultSet) {

		try {
			int sale_id = resultSet.getInt("sale_id");
			int car_id = resultSet.getInt("car_id");
			int salesPerson_id = resultSet.getInt("salesPerson_id");
			int customer_id = resultSet.getInt("customer_id");

			Sale sale = Sale.create(sale_id, car_id, salesPerson_id, customer_id);

			return sale;
		} catch (Exception e) {
			System.out.println("Couldnt create the Sale logic: " + e.getMessage());
			return null;
		}
	}

	public ArrayList<Sale> getAllSales() {
		ArrayList<Sale> sales = new ArrayList<Sale>();
		String querrySelect = "SELECT * FROM sale";

		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);

			while (resultSet.next()) {
				Sale sale = createSaleLogic(resultSet);
				sales.add(sale);
			}
			return sales;
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry: " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get all the sales person: " + e.getMessage());
			return null;
		}
	}

	public ArrayList<Sale> getSales() {
		return sales;
	}

	public Sale register(Sale saleParam, CarsRepository carsRep) {
		int car_id = saleParam.getCar_id();
		boolean isCarSold = carsRep.getById(car_id).getSoldValue();
		
		String insertValueQuerry = "INSERT INTO sale (car_id, salesPerson_id, customer_id) VALUES (" + saleParam.getCar_id() + ", " + saleParam.getSalesPerson_id() + ", " + saleParam.getCustomer_id() + ")"; 
		
		try {
		if(isCarSold == true) {
			throw new AppException("The car is already sold!");
			}
		
			connection.statement.executeUpdate(insertValueQuerry);
			
			Sale sale = getLastSale();
			return sale;
		} catch (SQLException e) {
			System.out.println("There was a problem to execute the querry (SQLException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out
					.println("There was a problem to register the sale (RuntimeException): " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to register the sale (AppException): " + e.getMessage());
			return null;
		}
		
	}
	
	public Sale getById(int sale_id) {
		String selectByIdQuerry = "SELECT * FROM sale WHERE sale_id = " + sale_id;
		
		try {
			if (!isValidId(sale_id)) {
				throw new AppException("The sale id is invalid");
			}
			
			ResultSet resultSet = connection.statement.executeQuery(selectByIdQuerry);
			
			if(resultSet.next()) {
				Sale sale = createSaleLogic(resultSet);
				return sale;
			} else {
				throw new AppException("Couldnt execute the querry. None row was returned");
			}
				
		} catch (SQLException e) {
			System.out.println(
					"There was a problem to select the Sale by the id (SQLException): " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println(
					"There was a problem to select the Sale by the id (AppException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println(
					"There was a problem to select the Sale by the id (RuntimeException): " + e.getMessage());
			return null;
		}
	}

	public Sale getLastSale() {
		String getLastSaleQuerry = "SELECT * FROM sale ORDER BY sale_id DESC LIMIT 1;";

		try {
			ResultSet resultSet = connection.statement.executeQuery(getLastSaleQuerry);

			if (resultSet.next()) {
				Sale sale = createSaleLogic(resultSet);
				return sale;
			} else {
				throw new AppException("Couldnt execute the querry. None row was returned");
			}

		} catch (SQLException e) {
			System.out.println("There was a problem to get the last sales (SQLException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get the last sales (RuntimeException): " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to get the last sales (AppException): " + e.getMessage());
			return null;
		}
	}
	
	public boolean isValidId(int sale_id) {
		boolean salesListBoolean = false;
		boolean databaseBoolean = false;

		String getByIdsQuerry = "SELECT sale_id FROM sale WHERE sale_id = " + sale_id;
		try {
			ResultSet resultSet = connection.statement.executeQuery(getByIdsQuerry);

			if (resultSet.next()) {
				databaseBoolean = true;
			}

			for (Sale sale : sales) {
				if (sale_id == sale.getSale_id()) {
					salesListBoolean = true;
					break;
				}
			}

			return salesListBoolean && databaseBoolean;

		} catch (SQLException e) {
			System.out.println("There was a problem to get the id (SQLException): " + e.getMessage());
			return false;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get the id (RuntimeException): " + e.getMessage());
			return false;
		}

	}

}

