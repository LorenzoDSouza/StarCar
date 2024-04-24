package APITest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void getByIdLookingTheDataBaseTest() {
		assertEquals("Civic", carsRepository.getById(1).getName());
	}
	
	@Test
	void registerNewCarTest() {
		assertEquals("Civic Type R", carsRepository.getLastCarDb().getName());
	}
	
	@Test
	void updateCarsPrice() {
		carsRepository.updatePrice(1, carsRepository.getById(carsRepository.getLastId()));
		assertEquals(1, carsRepository.getLastCarDb().getPrice());
		
	}
	
	

}
