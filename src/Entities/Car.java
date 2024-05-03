package Entities;

public class Car {

	int car_Id; // primary key
	int brand_Id; // foreign key
	String name;
	double price;
	boolean sold;

	private Car(int car_Id, String name, int brand_Id, double price) {
		this.brand_Id = brand_Id;
		this.car_Id = car_Id;
		this.name = name;
		this.price = price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private Car(String name, int brand_Id, double price) {
		this.brand_Id = brand_Id;
		this.name = name;
		this.price = price;
	}

	public static Car create(int car_Id, String name, int brand_Id, double price) {
		return new Car(car_Id, name, brand_Id, price);
	}

	public static Car create(String name, int brand_Id, double price) {
		return new Car(name, brand_Id, price);
	}

	public String toString() {
		if(sold = false) {
		return "Name: " + name + " | Car Id: " + car_Id + " | Brand Id: " + brand_Id + " | Price: $" + price + " | Avaliable";
		} else {
		return "Name: " + name + " | Car Id: " + car_Id + " | Brand Id: " + brand_Id + " | Price: $" + price + " | Not avaliable";
		}
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

	public boolean getSoldValue() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public double getPrice() {
		return price;
	}

}
