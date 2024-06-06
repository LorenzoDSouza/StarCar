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
	void getByIdLookingTheDataBase() {// checks if the getById is working by looking some random value in the database
										// and comparing
		SalesPerson salesPerson = salesPersonRep.getById(7);

		assertEquals(salesPerson.getFirst_name(), "James");
	}

	@Test
	void getByIdWithInvalidId() {// checks if the getById will return null if a invalid id is used as a
									// parametter
		SalesPerson salesPerson = salesPersonRep.getById(-1);

		assertNull(salesPerson);
	}

	@Test
	void getLastSalesPerson() {// check manually if the getLastSalesPerson method works
		SalesPerson salesPerson = salesPersonRep.getLastSalesPerson();

		assertEquals(salesPerson.getFirst_name(), "Lorenzo");
	}

	@Test
	void deleteById() {// check if the deleteById method works
		SalesPerson salesPerson = new SalesPerson("Andre", "Giuliano", 800.00);
		salesPersonRep.register(salesPerson);

		int lastId = salesPersonRep.getLastId();

		assertEquals(salesPersonRep.getLastSalesPerson().getLast_name(), "Giuliano");

		salesPersonRep.deleteById(lastId);

		assertNotEquals(salesPersonRep.getLastSalesPerson().getLast_name(), "Giuliano");
	}

	@Test
	void deleteByIdWithInvalidId() {// checks if the method deleteById will return false if it could not delete a
									// value because of a invalid id
		boolean deleted = salesPersonRep.deleteById(-1);

		assertFalse(deleted);
	}

	@Test
	void updateFirstName() {// checks if the first name will be updated correctly
		int lastId = salesPersonRep.getLastId();

		salesPersonRep.updateFirstName(lastId, "Lourenço");

		assertEquals(salesPersonRep.getById(lastId).getFirst_name(), "Lourenço");
	}

	@Test
	void updateFirstNameWithInvalidStringReturningNull() {// checks if passing a invalid string, the method will return
															// null
		int lastId = salesPersonRep.getLastId();

		assertNull(salesPersonRep.updateFirstName(lastId, null));
	}

	@Test
	void updateFirstNameWithInvalidStringCheckingValue() {// checks if passing a invalid string, the value will not be
															// updated
		int lastId = salesPersonRep.getLastId();

		salesPersonRep.updateFirstName(lastId, null);

		assertNotNull(salesPersonRep.getById(lastId).getFirst_name());
	}

	@Test
	void updateLastName() {//checks if the last name will be updated correctly
		int lastId = salesPersonRep.getLastId();

		salesPersonRep.updateLastName(lastId, "Kirst");

		assertEquals(salesPersonRep.getById(lastId).getLast_name(), "Kirst");
	}
	
	@Test
	void updateLastNameWithInvalidStringReturningNull() {// checks if passing a invalid string, the method will return
															// null
		int lastId = salesPersonRep.getLastId();

		assertNull(salesPersonRep.updateLastName(lastId, null));
	}

	@Test
	void updateLastNameWithInvalidStringCheckingValue() {// checks if passing a invalid string, the value will not be
															// updated
		int lastId = salesPersonRep.getLastId();

		salesPersonRep.updateLastName(lastId, null);

		assertNotNull(salesPersonRep.getById(lastId).getLast_name());
	}
	
	@Test
	void updatePayment() {
		int lastId = salesPersonRep.getLastId();
		
		salesPersonRep.updatePayment(lastId, 900.50);
		
		assertEquals(salesPersonRep.getLastSalesPerson().getPayment(), 900.50);
	}
	
	@Test
	void updatePaymentWithInvalidDouble() {
		int lastId = salesPersonRep.getLastId();
		
		salesPersonRep.updatePayment(lastId, -1000.30);
		
		assertNotEquals(salesPersonRep.getLastSalesPerson().getPayment(), -1000.30);
	}
	

}
