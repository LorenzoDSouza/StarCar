package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.Car;
import Entities.Customer;
import Exceptions.AppException;

public class CustomersRepository {

	private DbConnection connection;
	private ArrayList<Customer> customers;
	
	//getAllCustomers -
	//register -
	//getById <
	//deletebyId
	//updateFirstName
	//deleteLastName
	//getLastId
	//getLastCustomer
	//deleteLastCustomer
	//customersTableHasId(boolean)
	
	public Customer createCustomerLogic(ResultSet resultSet) {
		
		try {
			int customer_id = resultSet.getInt("customer_id");
			String first_name = resultSet.getString("first_name");
			String last_name = resultSet.getString("last_name");

			Customer customer = Customer.create(customer_id, first_name, last_name);

			return customer;

		} catch (Exception e) {
			return null;
		}
	}

	public CustomersRepository(String database) {
		connection = new DbConnection(database);

	}

	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		String querrySelect = "SELECT * FROM customer";

		try {
			ResultSet resultSet = connection.statement.executeQuery(querrySelect);

			while (resultSet.next()) {
				Customer customer = createCustomerLogic(resultSet);
				allCustomers.add(customer);
			}
			return allCustomers;
		} catch (SQLException e) {
			System.out.println("Couldn't execute querry!");
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get all the customers");
			return null;
		}
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	} 
	
	public Customer register(Customer customerParam) {
		String insertValueQuerry = "INSERT INTO customer (first_name, last_name) VALUES ('" + customerParam.getFirst_name() + "', '" + 
									customerParam.getLast_name() + "')";
		
		try {
			connection.statement.executeUpdate(insertValueQuerry);
			
			Customer customer = getLastCustomerDb();
			customers.add(customer);
			
			return customer;
		} catch (SQLException e) {
			System.out.println("There was a problem to execute the querry: " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to register the customer: " + e.getMessage());
			return null;
		}
	}
	
	public Customer getById(int customer_id) {
		String selectByIdQuerry = "SELECT * FROM customer WHERE customer_id = " + customer_id;
		
		try {
			if (!isValidId(customer_id)) {
				throw new AppException("The customer_id is invalid!");
			}
			
			ResultSet resultSet = connection.statement.executeQuery(selectByIdQuerry);
			
			if(resultSet.next()) {
				Customer customer = createCustomerLogic(resultSet);
				return customer;
			} else {
				return null;//change by a future exception, after discover how the return of the next() method works 
			}
			
		} catch (SQLException e) {
			System.out.println("There was a problem to select the Customer (SQLException): " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to select the Customer (AppException): " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to select the Customer (RuntimeException): " + e.getMessage());
			return null;
		}
	}
	
	public Customer getLastCustomerDb() {// method for during using implementation
		try {
			String getLastCustomerQuerry = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1;";

			ResultSet resultSet = connection.statement.executeQuery(getLastCustomerQuerry);

			if (resultSet.next()) {
				Customer lastCustomer = createCustomerLogic(resultSet);
				return lastCustomer;
			} else {
				throw new AppException("The table is empty");
			}

		} catch (SQLException e) {
			System.out.println("There was a problem to execute the querry: " + e.getMessage());
			return null;
		} catch (RuntimeException e) {
			System.out.println("There was a problem to get the customer: " + e.getMessage());
			return null;
		} catch (AppException e) {
			System.out.println("There was a problem to get the customer: " + e.getMessage());
			return null;
		}
	}
	
	public boolean isValidId(int customer_id) {
		boolean stockBoolean = false;
		boolean databaseBoolean = false;

		String getIdsQuerry = "SELECT customer_id FROM customer WHERE customer_id = " + customer_id;
		try {
			ResultSet resultSet = connection.statement.executeQuery(getIdsQuerry);

			if (resultSet.next()) {
				databaseBoolean = true;
			}

			for (Customer customer : customers) {
				if (customer_id == customer.getCustomer_id()) {
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
	
	
	
	
}
