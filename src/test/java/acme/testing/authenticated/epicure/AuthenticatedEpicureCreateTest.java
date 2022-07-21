package acme.testing.authenticated.epicure;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedEpicureCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/epicure/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String organisation, final String assertion, final String link) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Account","Become an epicure");
		super.checkFormExists();
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Register");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/epicure/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String organisation, final String assertion, final String link) {
		super.signIn("chef2", "chef2");
		super.clickOnMenu("Account","Become an epicure");
		super.checkFormExists();
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Register");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/authenticated/epicure/create");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/authenticated/epicure/create");
		super.checkPanicExists();
		super.signOut();
		
	}
}