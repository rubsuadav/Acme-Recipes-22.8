package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitItemShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/show.csv", encoding = "utf-8", numLinesToSkip = 1)
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
		// HINT+ a) estando logueado como inventorX no poder ver los detalles de los items asociados a una toolkit que no sea suya;

	}
}