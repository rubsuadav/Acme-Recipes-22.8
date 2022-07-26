package acme.testing.chef.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefItemListMineTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/item/list-mine-items.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String typeEntity, final String name, final String code, final String retailPrice) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My items");
		super.checkListingExists();
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, typeEntity);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, code);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);

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
		super.navigate("/chef/item/list-mine-items");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/item/list-mine-items");
		super.checkErrorsExist();
		super.signOut();
	}

}