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
	void getById() {
		
	}
	
	

	
	
}
