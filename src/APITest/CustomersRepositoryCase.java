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
	private Customer cus2;
	
	@BeforeEach
	void setUp() {
		cus1 = Customer.create("Lorenzo" , "de Souza");
		cus2 = Customer.create("Arthur" , "Kellermann");
		cusRep.register(cus1);
	}
	
	@AfterEach
	void tearDown() {
		cusRep.deleteLastCustomer();
	}
	
	@Test
	void getByIdCheckingFirstName() {
		assertEquals("David", cusRep.getById(1).getFirst_name());
	}
	
	@Test
	void deleteById() {
		cusRep.register(cus2);
		int lastCustomerId = cusRep.getLastId();
		
		assertEquals(cus2.getFirst_name(), cusRep.getLastCustomerDb().getFirst_name());
		
		cusRep.deleteById(lastCustomerId);
		
		assertEquals(false, cusRep.isValidId(lastCustomerId));
	}
	
	@Test
	void deleteLastCustomer() {
		cusRep.register(cus2);
		int lastCustomerId = cusRep.getLastId();
		
		cusRep.deleteLastCustomer();
		
		assertEquals(false, cusRep.isValidId(lastCustomerId));
	}
	
	@Test 
	void updateFirstName() {
		cusRep.updateFirstName(cusRep.getLastId(), "Andre");
		assertEquals("Andre", cusRep.getLastCustomerDb().getFirst_name());
	}
	
	@Test
	void updateFirstNameWithInvalidString() {
		assertEquals(cusRep.updateFirstName(cusRep.getLastId(), null), null);
	}
	
	@Test
	void updateFirstNameWithInvalidCustomerId() {
		assertEquals(cusRep.updateFirstName(-1, null), null);
	}
	
	@Test 
	void updateLastName() {
		cusRep.updateLastName(cusRep.getLastId(), "Santos");
		assertEquals("Santos", cusRep.getLastCustomerDb().getLast_name());
	}
	
	@Test
	void updateLastNameWithInvalidString() {
		assertEquals(cusRep.updateLastName(cusRep.getLastId(), null), null);
	}
	
	@Test
	void updateLastNameWithInvalidCustomerId() {
		assertEquals(cusRep.updateLastName(-1, null), null);
	}
}
