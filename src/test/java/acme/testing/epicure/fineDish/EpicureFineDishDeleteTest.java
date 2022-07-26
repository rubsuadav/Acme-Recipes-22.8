package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status,final String code, final String request, 
		final String budget, final String initial, final String end, final String link) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("initial", initial);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Delete");
		
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkNotListingEmpty();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String status,final String code, final String request, 
		final String budget, final String initial, final String end, final String link) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("link", link);
		super.checkNotSubmitExists("Delete");
		
		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		//Como chef no poder borrar platillos
		
	}
	
}