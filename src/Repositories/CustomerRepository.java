package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.Car;
import Entities.Customer;
import Exceptions.AppException;

public class CustomerRepository {

	private DbConnection connection;
	private ArrayList<Customer> customers;

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

	public CustomerRepository(String database) {
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
	
	
}
