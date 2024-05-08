package Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DbConnection;
import Entities.Car;
import Entities.Customer;

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
}
