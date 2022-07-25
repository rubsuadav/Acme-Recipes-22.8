package acme.testing.chef.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefQuantityUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/quantity/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recipeRecordIndex, final int recordIndex, final String number, 
		final String name, final String code, final String description, final String retailPrice, final String link) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recipeRecordIndex);
		super.checkFormExists();

		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(2, "asc");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("number", number);
		super.clickOnSubmit("Update");

		super.checkNotErrorsExist();	
		super.clickOnListingRecord(recordIndex);
				
		super.checkInputBoxHasValue("number", number);
		super.checkInputBoxHasValue("item.name", name);
		super.checkInputBoxHasValue("item.code", code);
		super.checkInputBoxHasValue("item.description", description);
		super.checkInputBoxHasValue("item.retailPrice", retailPrice);
		super.checkInputBoxHasValue("item.link", link);
		
		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/quantity/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recipeRecordIndex, final int recordIndex, final String number) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recipeRecordIndex);
		super.checkFormExists();

		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(2, "asc");

		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("number", number);

		super.clickOnSubmit("Update");
		super.checkErrorsExist();	
		
		super.signOut();

	}

}