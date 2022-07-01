package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-positive.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String author, final String body, 
		final String email, final String confirmation) {
		
		super.clickOnMenu("Anonymous","List recent chirps");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-negative.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String title, final String author, final String body, 
		final String email, final String confirmation) {
		
		super.clickOnMenu("Anonymous","List recent chirps");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		//No tiene puesto que el role es any--> todo el mundo puede crear chirridos
		
	}
	
}