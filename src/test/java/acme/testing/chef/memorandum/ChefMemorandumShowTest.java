package acme.testing.chef.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandumShowTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String instantiationMoment, final String fineDishId,
		final String automaticSequenceNumber, final String report, final String link) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 1, fineDishId);
		super.clickOnListingRecord(recordIndex);
		
        super.checkFormExists();
        super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequenceNumber);
        super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
        super.checkInputBoxHasValue("report", report);
        super.checkInputBoxHasValue("link", link);
		
		super.signOut();
		
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		// HINT: this is a listing, which implies that no data must be entered in any forms.
		// HINT+ Then, there are not any negative test cases for this feature.
	}

	@Test
	@Order(30)
	public void hackingTest() {
		// HINT+ a) estando logueado como chefX no poder ver los detalles de un patronage report que no sea suyo;

	}
}