package APITest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.Car;
import Entities.Customer;
import Repositories.CustomersRepository;

class CustomersRepositoryCase {
	
	private static CustomersRepository cusRep = new CustomersRepository("starcartest");
	private Customer cus1;
	
	@BeforeEach
	void setUp() {
		cus1 = Customer.create("Lorenzo" , "de Souza");
		//cusRep.register(cus1);
	}
	
	@AfterEach
	void tearDown() {
		//cusRep.deleteLastCustomer();
	}
	
	@Test
	void getByIdCheckingFirstName() {
		assertEquals("David", cusRep.getById(1).getFirst_name());
	}
	
}
