package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishListMineTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String budget, final String creation) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, creation);
		
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
		super.signIn("administrator", "administrator");
		super.navigate("/epicure/fine-dish/list-mine");
		super.checkListingEmpty();
		super.signOut();

		super.navigate("/epicure/fine-dish/list-mine");
		super.checkErrorsExist();
		
		super.signIn("chef1", "chef1");
		super.navigate("/epicure/fine-dish/list-mine");
		super.checkErrorsExist();
		super.signOut();
	}

}