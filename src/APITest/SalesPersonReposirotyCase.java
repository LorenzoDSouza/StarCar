package APITest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.SalesPerson;
import Repositories.SalesPersonRepository;

class SalesPersonReposirotyCase {

	private static SalesPersonRepository salesPersonRep = new SalesPersonRepository("starcartest");
	private SalesPerson salesPerson1;
	
	@BeforeEach
	void setUp() {
		salesPerson1 = new SalesPerson("Lorenzo", "de Souza", 1200.00);
		salesPersonRep.register(salesPerson1);
	}
	
	@AfterEach
	void tearDown() {
		salesPersonRep.deleteLastSalesPerson();
	}
	
	@Test
	void getByIdLookingTheDataBase() {
		SalesPerson salesPerson = salesPersonRep.getById(7);
		
		assertEquals(salesPerson.getFirst_name(), "James");
	}
	
	@Test
	void getByIdWithInvalidId() {
		SalesPerson salesPerson = salesPersonRep.getById(-1);
		
		assertEquals(salesPerson, null);
	}
	
	@Test
	void getLastSalesPerson( ) {
		SalesPerson salesPerson = salesPersonRep.getLastSalesPerson();
		
		assertEquals(salesPerson.getFirst_name(), "Lorenzo");
	}
	
	
	
}
