package Entities;

public class Car {

	int car_Id; // primary key
	int brand_Id; // foreign key
	String name;
	double price;
	boolean sold;

	public Car(int car_Id, String name, int brand_Id, double price, boolean sold) {
		this.brand_Id = brand_Id;
		this.car_Id = car_Id;
		this.name = name;
		this.price = price;
		this.sold = sold;
	}

	public void setCar_Id(int car_Id) {
		this.car_Id = car_Id;
	}

	public void setBrand_Id(int brand_Id) {
		this.brand_Id = brand_Id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCar_Id() {
		return car_Id;
	}

	public int getBrand_Id() {
		return brand_Id;
	}

	public String getName() {
		return name;
	}

	public boolean getSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

}
