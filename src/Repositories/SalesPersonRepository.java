package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.Customer;
import Entities.SalesPerson;
import Exceptions.AppException;

public class SalesPersonRepository {

	private DbConnection connection;
	private ArrayList<SalesPerson> staff;
	
	// getAllStaff -
	// register -
	// getById
	// deleteById 
	// updateFirstName
	// updateLaststName
	//updatePayment
	// getLastId -
	// getlastSalesPerson -
	// deleteLastSalesPerson
	// isValidId
	
	public SalesPersonRepository(String database) {
		connection = new DbConnection(database);
		//staff = getAllStaff();
				
	}
	
	public SalesPerson createSalesPersonLogic(ResultSet resultSet) {
		
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
				SalesPerson salesPerson = createSalesPersonLogic(resultSet);
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
			
			SalesPerson salesPerson = getLastSalesPerson();
			staff.add(salesPerson);
			
			return salesPerson;
		} catch (SQLException e) {
			System.out.println("There was a problem to execute the querry (SQLException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to register the sales person (RuntimeException): " + e.getMessage());
			return null;
		}
	}
	
	public SalesPerson getById(int salesPerson_Id) {
		String selectByIdQuerry = "SELECT * FROM salesperson WHERE salesPerson_id = " + salesPerson_Id;
		
		try {
			if(!isValidId(salesPerson_Id)) {
				throw new AppException("The salesPerson_id is invalid!");
			}
			
			ResultSet resultSet = connection.statement.executeQuery(selectByIdQuerry);
			
			if (resultSet.next()) {
				SalesPerson salesPerson = createSalesPersonLogic(resultSet);
				return salesPerson;
			} else {
				throw new AppException("Couldnt execute the querry. None row was returned");		
			}
			
		} catch (SQLException e) {
			System.out.println("There was a problem to select the Sales Person by the id (SQLException): " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to select the Sales Person by the id (AppException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to select the Sales Person by the id (RuntimeException): " + e.getMessage());
			return null;
		}
	}
	
	public SalesPerson getLastSalesPerson() {
		try {
			String getLastSalesPersonQuerry = "SELECT * FROM salesperson ORDER BY salesPerson_id DESC LIMIT 1;";

			ResultSet resultSet = connection.statement.executeQuery(getLastSalesPersonQuerry);

			if (resultSet.next()) {
				SalesPerson salesPerson = createSalesPersonLogic(resultSet);
				return salesPerson;
			} else {
				throw new AppException("Couldnt execute the querry. None row was returned");
			}

		} catch (SQLException e) {
			System.out.println("There was a problem to get the last sales person (SQLException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get the last sales person (RuntimeException): " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to get the last sales person (AppException): " + e.getMessage());
			return null;
		}
	}
	
	public int getLastId() {
		String getLastSalesPersonQuerry = "SELECT salesPerson_id FROM salesperson ORDER BY salesPerson_id DESC LIMIT 1;";
		
		try {
			ResultSet resultSet = connection.statement.executeQuery(getLastSalesPersonQuerry);

			if (resultSet.next()) {
				int lastId = resultSet.getInt("salesPerson_id");
				return lastId; 
			} else {
				throw new AppException("Couldnt execute the querry. None row was returned");
			}

		} catch (SQLException e) {
			System.out.println("There was a problem to get the last sales person id (SQLException): " + e.getMessage());
			return 0;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get the last sales person id (RuntimeException): " + e.getMessage());
			return 0;
		} catch (AppException e) {
			System.out.println("There was a problem to get the last sales person id (AppException): " + e.getMessage());
			return 0;
		}
	}
	
	public boolean deleteLastSalesPerson() {
		String deleteLastSalesPersonQuerry = "DELETE FROM salesperson ORDER BY salesPerson_id DESC LIMIT 1"
				
				try {
					connection.statement.executeUpdate(deleteLastSalesPersonQuerry);
					
					return true;
				} catch (SQLException e) {
					System.out.println("Couldn't delete the customer in the database (SQLException): " + e.getMessage());
					return false;
				} catch (RuntimeException e) {
					System.out.println("Couldn't delete the customer (RuntimeLException): " + e.getMessage());
					return false;
				}
	}
	
	
	public boolean isValidId(int salesPerson_id) {
		boolean staffListBoolean = false;
		boolean databaseBoolean = false;

		String getByIdsQuerry = "SELECT salesPerson_id FROM salesperson WHERE salesPerson_id = " + salesPerson_id;
		try {
			ResultSet resultSet = connection.statement.executeQuery(getByIdsQuerry);

			if (resultSet.next()) {
				databaseBoolean = true;
			}

			for (SalesPerson salesPerson : staff) {
				if (salesPerson_id == salesPerson.getSalesPerson_id()) {
					staffListBoolean = true;
					break;
				}
			}

			return staffListBoolean && databaseBoolean;

		} catch (SQLException e) {
			System.out.println("There was a problem to get the id (SQLException): " + e.getMessage());
			return false;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get the id (RuntimeException): " + e.getMessage());
			return false;
		}
	
	}
	
	
	
}
