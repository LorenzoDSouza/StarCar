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
			String customer_name = resultSet.getString("first_name");
			
			Customer customer = Customer.create(customer_id, customer_name);

			return customer;
		} catch (Exception e) {
			return null;
		}
	}

	
	
}
