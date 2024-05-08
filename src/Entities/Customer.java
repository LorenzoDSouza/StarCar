package Entities;

import java.util.*;

public class Customer {

	private String name;
	private int customer_id;

	public Customer(int customer_id, String name) {
		this.customer_id = customer_id;
		this.name = name;
	}
	
	public Customer (String name) {
		this.name = name;
	}
	
	public static Customer create(int customer_id, String name) {
		return new Customer(customer_id, name);
	}
	
	public static Customer create (String name) {
		return new Customer(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String toString() {
		return "Name: " + name + " | Customer Id: " + customer_id;
		
	}

	public String getName() {
		return name;
	}

	public int getCustomer_id() {
		return customer_id;
	}

}
