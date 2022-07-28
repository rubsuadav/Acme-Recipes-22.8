package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String budget) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List pimpams");
		super.checkListingExists();
		super.sortListing(0,"asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, budget);
		
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
		super.navigate("/chef/pimpam/list-pimpam");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/pimpam/list-pimpam");
		super.checkPanicExists();
		super.signOut();

	}

}