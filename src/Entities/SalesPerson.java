package Entities;

import java.util.*;

public class SalesPerson {
	private int salesPerson_id;
	private String name;
	private Double payment;

	public SalesPerson(int salesPerson_id, String name, Double payment) {
		this.salesPerson_id = salesPerson_id;
		this.name = name;
		this.payment = payment;
	}
}
