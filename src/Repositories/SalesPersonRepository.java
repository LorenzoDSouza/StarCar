package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.SalesPerson;
import Exceptions.AppException;

public class SalesPersonRepository {

	private DbConnection connection;
	private ArrayList<SalesPerson> staff;
	
	// getAllStaff
	// register
	// getById
	// deleteById
	// updateFirstName
	// updateLaststName
	//updatePayment
	// getLastId
	// getlastSalesPerson
	// deleteLastSalesPerson
	// isValidId
	
	public SalesPersonRepository(String database) {
		connection = new DbConnection(database);
		//staff = getAllStaff();
				
	}
	
	public SalesPerson createSalestPersonLogic(ResultSet resultSet) {
		
		try {
			int salesPerson_id = resultSet.getInt("salesPerson_id");
			String first_name = resultSet.getString("first_name");
			String last_name = resultSet.getString("last_name");
			Double payment = resultSet.getDouble("payment");
			
			SalesPerson salesPerson = SalesPerson.create(salesPerson_id, first_name, last_name, payment);
			
			return salesPerson;
		} catch (Exception e) {
			System.out.println("Couldnt return the Sales Person: " + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<SalesPerson> getAllStaff() {
		ArrayList<SalesPerson> staff = new ArrayList<SalesPerson>();
		String querrySelect = 
	}
}
