package Entities;

public class Car {
	
	int car_Id; //primary key
	int brand_Id; //foreign key
	String name;
	String brand_name;
	
	public Car(int car_Id, int brand_Id, String name) {
		this.brand_Id = brand_Id;
		this.car_Id = car_Id;
		this.name = name;
		
	}
	
	public void setBrandName(String brand_name) {
		this.brand_name = brand_name;
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

	public String getBrand_name() {
		return brand_name;
	}
	
	
}
