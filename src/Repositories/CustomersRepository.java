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

	// getAllCustomers -
	// register -
	// getById -
	// deletebyI -
	// updateFirstName <
	// updateLastName
	// getLastId -
	// getLastCustomer
	// deleteLastCustomer -
	// customersTableHasId(boolean) -

	public CustomersRepository(String database) {
		connection = new DbConnection(database);
		customers = getAllCustomers();
	}

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
		String insertValueQuerry = "INSERT INTO customer (first_name, last_name) VALUES ('"
				+ customerParam.getFirst_name() + "', '" + customerParam.getLast_name() + "')";

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

			if (resultSet.next()) {
				Customer customer = createCustomerLogic(resultSet);
				return customer;
			} else {
				return null;// change by a future exception, after discover how the return of the next()
							// method works
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

		String getByIdsQuerry = "SELECT customer_id FROM customer WHERE customer_id = " + customer_id;
		try {
			ResultSet resultSet = connection.statement.executeQuery(getByIdsQuerry);

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

	public boolean deleteById(int customer_id) {
		String deleteByIdQuerry = "DELETE FROM customer WHERE customer_id = " + customer_id;
		boolean deleted = false;

		try {
			connection.statement.executeUpdate(deleteByIdQuerry);

			for (Customer cus : customers) {
				if (cus.getCustomer_id() == customer_id) {
					customers.remove(cus);
					deleted = true;
					break;
				}
			}
			
			return isValidId(customer_id) && deleted;

		} catch (SQLException e) {
			System.out.println("Couldn't delete the car in the database (SQLException): " + e.getMessage());
			return false;
		} catch (RuntimeException e) {
			System.out.println("Couldn't delete the car in the database (RuntimeException): " + e.getMessage());
			return false;
		}
	}
	
	public boolean deleteLastCustomer() {
		String deleteLastCar = "DELETE FROM customer ORDER BY customer_id DESC LIMIT 1";

		try {
			connection.statement.executeUpdate(deleteLastCar);

			return true;
		} catch (SQLException e) {
			System.out.println("Couldn't delete the customer in the database (SQLException): " + e.getMessage());
			return false;
		} catch (RuntimeException e) {
			System.out.println("Couldn't delete the customer (RuntimeLException): " + e.getMessage());
			return false;
		}
	}
	
	public int getLastId() {
		String getLastId = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1";
		try {
			ResultSet resultSet = connection.statement.executeQuery(getLastId);

			if (resultSet.next()) {
				return resultSet.getInt("customer_id");
			} else {
				throw new AppException("Couldnt execute the querry. None row was returned");
			}

		} catch (SQLException e) {
			System.out.println("Couldn't execute the querry (SQLException): " + e.getMessage());
			return 0;
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return 0;
		} 

	}
	
	public Customer updateFirstName(int customer_id, String newFirstName) {
		String udapteFirstNameQuerry = "UPDATE customer SET first_name: " + newFirstName + " WHERE customer_id = " + customer_id;
		int indexOfUpdate = -1;
		
		try {
			if (isValidId(customer_id) != true) {
				throw new AppException("The customer id is invalid!");
			}
			if (newFirstName == null) {
				throw new AppException("The first name is invalid!");
			}
			
			int rowsAffected = connection.statement.executeUpdate(udapteFirstNameQuerry);
			
			for (int index = 0; index < customers.size(); index++) {
				if (customers.get(index).getCustomer_id() == customer_id) {
					customers.get(index).setFirst_name(newFirstName);;
					indexOfUpdate = index;
					break;
				}
			}
			
			if (indexOfUpdate == -1) {
				throw new AppException("There was a problem to udpate the customer in the customers list (API)");
			}
			
			if (getById(customer_id).getFirst_name() == newFirstName && customers.get(indexOfUpdate).getFirst_name() == newFirstName) {
				return customers.get(indexOfUpdate);
			} else {
				throw new AppException("Couldnt update the first name of the customer.");
			}
			
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (SQLException e) {
			System.err.println("Coulnd't execute the Querry. There was a problem with the Database (SQLException)!");
			return null;
		} catch (RuntimeException e) {
			System.out.println("Couldnt update first name (RuntimeException): " + e.getMessage());
			return null;
		}
	}
}
