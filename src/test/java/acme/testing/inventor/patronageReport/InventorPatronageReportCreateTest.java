package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexP, final int recordIndex, final String memorandum, final String link, final String confirmation) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My patronages");
		
		super.sortListing(1, "asc");
		
		super.clickOnListingRecord(recordIndexP);
		super.checkButtonExists("Create patronage report");
		super.clickOnButton("Create patronage report");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create patronage report");

		super.clickOnMenu("Inventor", "My patronage reports");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndexP, final int recordIndex, final String memorandum, final String link, final String confirmation) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My patronages");
		
		super.clickOnListingRecord(recordIndexP);
		super.checkButtonExists("Create patronage report");
		super.clickOnButton("Create patronage report");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create patronage report");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
	}
}
