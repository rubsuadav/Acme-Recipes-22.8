package acme.testing.chef.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandumCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexP, final int recordIndex, final String report, final String link, final String confirmation) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My fine dishes");
		
		super.sortListing(1, "asc");
		
		super.clickOnListingRecord(recordIndexP);
		super.checkButtonExists("Create memorandum");
		super.clickOnButton("Create memorandum");
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Chef", "My memorandums");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndexP, final int recordIndex, final String report, final String link, final String confirmation) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My fine dishes");
		
		super.clickOnListingRecord(recordIndexP);
		super.checkButtonExists("Create memorandum");
		super.clickOnButton("Create memorandum");
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/chef/memorandum/create");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/memorandum/create");
		super.checkPanicExists();
		super.signOut();
	}
}