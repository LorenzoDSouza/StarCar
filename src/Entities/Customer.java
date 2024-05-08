package Entities;

import java.util.*;

public class Customer {

	private String first_name;
	private String last_name;
	private int customer_id;

	public Customer(int customer_id, String first_name, String last_name) {
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public Customer (String first_name, String last_name) {
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public static Customer create(int customer_id, String first_name, String last_name) {
		return new Customer(customer_id, first_name, last_name);
	}
	
	public static Customer create (String first_name, String last_name) {
		return new Customer(first_name, last_name);
	}


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

}
