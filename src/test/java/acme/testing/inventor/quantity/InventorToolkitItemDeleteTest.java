package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitItemDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int toolkitRecordIndex, final int recordIndex, final String number, 
		final String name, final String code, final String technology, final String description, final String retailPrice, final String link) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(toolkitRecordIndex);
		super.checkFormExists();

		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		super.checkListingExists();
		super.sortListing(2, "asc");

		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("number", number);
		super.checkInputBoxHasValue("item.name", name);
		super.checkInputBoxHasValue("item.code", code);
		super.checkInputBoxHasValue("item.technology", technology);
		super.checkInputBoxHasValue("item.description", description);
		super.checkInputBoxHasValue("item.retailPrice", retailPrice);
		super.checkInputBoxHasValue("item.link", link);
		super.clickOnSubmit("Delete");

		super.checkNotErrorsExist();
		super.checkListingExists();
		
		super.signOut();

	}
	
}