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
	// register (need to have check if the car is already related to a sale)
	// getById
	// deleteById
	// getLastSale
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
	
	public ArrayList<Sale> getSales(){
		return sales;
	}

	public Sale register(Sale sale, CarsRepository carsRep) {
		int car_id = sale.getCar_id();
		boolean isCarSold = carsRep.getById(car_id).getSoldValue();
		
		String 
		
		try {
		if(isCarSold == true) {
			throw new AppException("The car is already sold!");
			
			}
		
		
		}
		
	}

}
