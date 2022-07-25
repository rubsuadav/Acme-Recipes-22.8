package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipePublishTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, 
		final String link) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
		
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, 
		final String link) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);
		super.clickOnSubmit("Publish");
		super.checkNotPanicExists();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/chef/recipe/publish");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/recipe/publish");
		super.checkPanicExists();
		super.signOut();
		
	}

}