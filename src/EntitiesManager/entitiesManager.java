package EntitiesManager;

import java.util.*;

import Entities.Car;
import Entities.Customer;
import Entities.Sale;
import Entities.SalesPerson;
import DataBase.*;

 
public class entitiesManager { //manage the tables from the db and put into arrays for manag
	//ement in the api, this class also makes the connection with the database 
	
	public ArrayList<Car> stock;
	public ArrayList<Customer> customers; 
	public ArrayList<SalesPerson> staff;
	public ArrayList<Sale> sales;
	
	public boolean removeCarStock(Car car) {
		boolean valid;
		try {
			stock.remove(car);
			valid = true;
		} catch (Exception e) {
			System.out.println("Couldn't remove the car (Id: " + car.getCar_Id() + ") from stock!");
			valid = false;
		}
		return valid;
	}
	
	
	public boolean addCarStock(Car car) {
		boolean valid;
		try {
			stock.add(car);
			//querry to add the car
			valid = true;
		} catch (Exception e) {
			System.out.println("Couldn't add the car (Id: " + car.getCar_Id() + ") to stock!");
			valid = false;
		}
		return valid;
	}

	public boolean removeCustomer(Customer customer) {
		boolean valid;
		try {
			customers.remove(customer);
			//querry to remove the car
			valid = true;
		} catch (Exception e) {
			System.out.println("Couldn't remove the customer (Id: " + customer.getCustomer_id() + ") from the register!");
			valid = false;
		}
		return valid;
	}
	
	
	public boolean addCustomer(Customer customer) {
		boolean valid;
		try {
			customers.add(customer);
			//querry to add the car
			valid = true;
		} catch (Exception e) {
			System.out.println("Couldn't register the customer (Id: " + customer.getCustomer_id() + ")!");
			valid = false;
		}
		return valid; 
	}
}
