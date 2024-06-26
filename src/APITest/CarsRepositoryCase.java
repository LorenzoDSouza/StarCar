package APITest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


import Repositories.CarsRepository;
import Database.DbConnection;
import Entities.Car;

class CarsRepositoryCase {

	private static CarsRepository carsRep = new CarsRepository("starcartest");
	private Car car1;
	
	@BeforeEach
	void setUp() {
		car1 = Car.create("Civic Type R" , 1, 44890);
		carsRep.register(car1);
	}
	
	@AfterEach
	void tearDown() {
		carsRep.deleteLastCar();
	}
	
	@Test
	void getByIdLookingTheDataBase() {//check if 
		assertEquals("Civic", carsRep.getById(1).getName());
	}
	
	@Test
	void getByIdWithInvalidId() {//check if 
		assertEquals(null, carsRep.getById(-1));
	}
	
	@Test
	void registerNewCar() {//check if the car in the setUp method(BeforeEach) was registered succesfully
		assertEquals("Civic Type R", carsRep.getLastCarDb().getName());
	}
	
	@Test
	void updateCarsPrice() {//updates the car's price that was added in the setUp method (BeforeEach)
		carsRep.updatePrice(2, carsRep.getLastCarDb().getCar_Id());
		assertEquals(2, carsRep.getLastCarDb().getPrice());
	}
	
	@Test
	void carIDValidator() {//validates an car_id checked manually in the database
		assertEquals(true, carsRep.isValidId(1));
	}
	
	@Test
	void deleteById() {//delete a car by its id
		Car car = Car.create("HRV" , 1, 25.000);
		carsRep.register(car);
		assertEquals("HRV", carsRep.getLastCarDb().getName());
		carsRep.deleteById(carsRep.getLastCarDb().getCar_Id());
		assertEquals("Civic Type R", carsRep.getLastCarDb().getName());
		
	}
	
	@Test
	void updateCarName() {//check the update of the name in the last car added by the setUp method (BeforeEach)
		carsRep.updateName("Civic TSI", carsRep.getLastCarDb().getCar_Id());
		assertEquals("Civic TSI" , carsRep.getLastCarDb().getName());
	}
	
	
	@Test
	void updateCarNameWithInvalidName() {
		Car car = carsRep.updateName(null, carsRep.getLastCarDb().getCar_Id());
		assertEquals(null, car);
	}
	
	
	
	@Test
	void checkSoldValueForNewCar() {
		int lastId = carsRep.getLastId();
		assertEquals(false, carsRep.getSoldValueByDatabase(lastId));
	}
	
	@Test
	void updateAvaliableCarToSoldCheckingById() {
		int lastId = carsRep.getLastId();
		carsRep.updateToSold(lastId);
		assertEquals(true, carsRep.getSoldValueByDatabase(lastId));
	}
	
	@Test
	void updateAvaliableCarToSoldCheckingByCarReturn() {
		int lastId = carsRep.getLastId();
		carsRep.updateToSold(lastId);
		assertEquals(true, carsRep.getLastCarDb().getSoldValue());
	}
	
	@Test
	void deleteACarLookingTheDatabase() {
		carsRep.deleteById(40);
		assertEquals(null, carsRep.getById(40));
	}
	
	@Test
	void getByIdPassingInvalidId() {
		assertEquals(null, carsRep.getById(-1));
	}
	
	
	
	
	

}
