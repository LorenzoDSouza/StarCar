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

	private CarsRepository carsRepository = new CarsRepository("starcartest");
	private Car car1;
	
	@BeforeEach
	void setUp() {
		carsRepository  = new CarsRepository("starcartest");
		car1 = Car.create("Civic Type R" , 1, 44.890);
		carsRepository.register(car1);
	}
	
	@AfterEach
	void tearDown() {
		carsRepository.deleteLastCar();
		
	}
	
	@Test
	void getByIdLookingTheDataBaseTest() {//check if 
		assertEquals("Civic", carsRepository.getById(1).getName());
	}
	
	@Test
	void registerNewCarTest() {//check if the car in the setUp method(BeforeEach) was registered succesfully
		assertEquals("Civic Type R", carsRepository.getLastCarDb().getName());
	}
	
	@Test
	void updateCarsPrice() {//updates the car's price that was added in the setUp method (BeforeEach)
		carsRepository.updatePrice(2, carsRepository.getLastCarDb().getCar_Id());
		assertEquals(2, carsRepository.getLastCarDb().getPrice());
	}
	
	@Test
	void carIDValidator() {//validates an car_id checked manually in the database
		assertEquals(true, carsRepository.stockContainsID(1));
	}
	
	@Test
	void deleteById() {//delete a car by its id
		Car car = Car.create("HRV" , 1, 25.000);
		carsRepository.register(car);
		assertEquals("HRV", carsRepository.getLastCarDb().getName());
		carsRepository.deleteById(carsRepository.getLastCarDb().getCar_Id());
		assertEquals("Civic Type R", carsRepository.getLastCarDb().getName());
		
	}
	
	@Test
	void updateCarName() {//check the update of the name in the last car added by the setUp method (BeforeEach)
		carsRepository.updateName("Civic TSI", carsRepository.getLastCarDb().getCar_Id());
		assertEquals("Civic TSI" , carsRepository.getLastCarDb().getName());
	}
	
	
	
	@Test
	void checkSoldValueForNewCar() {
		int lastId = carsRepository.getLastId();
		System.out.println(lastId);
		assertEquals(false, carsRepository.getSoldValueByDatabase(lastId));
	}
	
	@Test
	void updateAvaliableCarToSold() {
		
	}
	
	
	
	

}
