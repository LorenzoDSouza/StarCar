package Entities;

public class Sale {

	private int sale_id;
	private int car_id;
	private int salesPerson_id;
	private int customer_id;
	
	public Sale(int sale_id, int car_id, int salesPerson_id, int customer_id) {
		this.sale_id = sale_id;
		this.car_id = car_id;
		this.salesPerson_id = salesPerson_id;
		this.customer_id = customer_id;
	}
	
	public Sale(int car_id, int salesPerson_id, int customer_id) {
		this.car_id = car_id;
		this.salesPerson_id = salesPerson_id;
		this.customer_id = customer_id;
	}
	
	public static Sale create(int sale_id, int car_id, int salesPerson_id, int customer_id) {
		return new Sale(sale_id, car_id, salesPerson_id, customer_id);
	}
	
	public static Sale create(int car_id, int salesPerson_id, int customer_id) {
		return new Sale(car_id, salesPerson_id, customer_id);
	}
	
}
