package Entities;

import java.util.*;

public class SalesPerson {
	private int salesPerson_id;
	private String first_name;
	private String last_name;
	private Double payment;

	public SalesPerson(int salesPerson_id, String first_name, String last_name, Double payment) {
		this.salesPerson_id = salesPerson_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.payment = payment;
	}
	
	public SalesPerson(String first_name, String last_name, Double payment) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.payment = payment;
	}
	
	public static SalesPerson Create (int salesPerson_id, String first_name, String last_name, Double payment) {
		return new SalesPerson(salesPerson_id, first_name, last_name, payment);
	}
	
	public static SalesPerson Create (String first_name, String last_name, Double payment) {
		return new SalesPerson(first_name, last_name, payment);
	}

	public int getSalesPerson_id() {
		return salesPerson_id;
	}

	public void setSalesPerson_id(int salesPerson_id) {
		this.salesPerson_id = salesPerson_id;
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

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	
}
