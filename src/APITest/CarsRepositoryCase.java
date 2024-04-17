package APITest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Repositories.CarsRepository;
import Database.DbConnection;
import Entities.Car;

class CarsRepositoryCase {

	private CarsRepository carsRepository  = new CarsRepository("starcartest");
	
	@BeforeEach
	void setUp() {
		
	}
	
	@After
	void tearDown() {
		
	}
	
	@Test
	void getByIdLookingTheDataBaseTest() {//manual test: need to be tested manualy by looking in the database
		assertEquals("Civic", carsRepository.getById(1).getName());
	}
	
	@Test
	void registerNewCarTest() {
		Car car1 = Car.create("Civic Type R" , 1, 44.890);
		carsRepository.register(car1);
		
		assertEquals(66, carsRepository.getLastCarDb().getCar_Id());
	}
	

}
