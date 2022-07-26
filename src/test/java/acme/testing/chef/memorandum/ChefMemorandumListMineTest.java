package acme.testing.chef.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandumListMineTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String instantiationMoment, final String fineDishId) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 1, fineDishId);
		
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
		super.navigate("/chef/memorandum/list-mine");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/memorandum/list-mine");
		super.checkErrorsExist();
		super.signOut();
		
	}
}