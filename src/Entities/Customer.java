package Entities;

import java.util.*;

public class Customer {

	private String name;
	private int customer_id;

	public Customer(int customer_id, String name) {
		this.customer_id = customer_id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getCustomer_id() {
		return customer_id;
	}

}
