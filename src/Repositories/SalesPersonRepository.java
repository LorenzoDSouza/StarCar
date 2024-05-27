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
		String querrySelect = "SELECT * FROM salesperson";
		
		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);
			
			while (resultSet.next()) {
				SalesPerson salesPerson = createSalestPersonLogic(resultSet);
				staff.add(salesPerson);
			}
			return staff;
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry!");
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get all the sales person");
			return null;
		}
	}
	
	public ArrayList<SalesPerson> getStaff(){
		return staff;
	}
	
	public SalesPerson register(SalesPerson salesPersonParam) {
		String insertValueQuerry = "INSERT INTO salesperson (first_name, last_name, payment) VALUES ('"
				+ salesPersonParam.getFirst_name() + "', '" + salesPersonParam.getLast_name() + "', " 
				+ salesPersonParam.getPayment() + ")";
		
		try {
			connection.statement.executeUpdate(insertValueQuerry);
			
			//SalesPerson salesPerson = getLastSalesPerson();
		}
	}
	
	
	
	
}
