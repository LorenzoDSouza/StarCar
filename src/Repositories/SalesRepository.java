package Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;

import Database.DbConnection;
import Repositories.*;
import Entities.*;

public class SalesRepository {
	
	private DbConnection connection;
	private ArrayList<Sale> sales;
	
	// getAllSales
	// register (need to have check if the car ialready related to a sale\
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
		//sales = getAllSales();
		}
	
	public Sale createSaleLogic(ResultSet resultSet) {
		
		try {
			int sale_id = resultSet.getInt("sale_id");
			int car_id = resultSet.getInt("car_id");
			int salesPerson_id = resultSet.getInt("salesPerson_id");
			int customer_id = resultSet.getInt("customer_id");
			
			
		}
		
	}

}
