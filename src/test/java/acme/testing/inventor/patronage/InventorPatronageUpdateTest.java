
package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff,
		final String budget, final String initial, final String creation, final String end, final String link, 
		final String patronProfileFullName, final String patronProfileEmail, final String patronCompany, final String patronStatement,
		final String patronLink) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronProfileFullName", patronProfileFullName);
		super.checkInputBoxHasValue("patronProfileEmail", patronProfileEmail);
		super.checkInputBoxHasValue("patronCompany", patronCompany);
		super.checkInputBoxHasValue("patronStatement", patronStatement);
		super.checkInputBoxHasValue("patronLink", patronLink);
		super.fillInputBoxIn("status", status);
		super.clickOnSubmit("Update");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String status, final String code, final String legalStuff,
		final String budget, final String initial, final String creation, final String end, final String link, 
		final String patronProfileFullName, final String patronProfileEmail, final String patronCompany, final String patronStatement,
		final String patronLink) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronProfileFullName", patronProfileFullName);
		super.checkInputBoxHasValue("patronProfileEmail", patronProfileEmail);
		super.checkInputBoxHasValue("patronCompany", patronCompany);
		super.checkInputBoxHasValue("patronStatement", patronStatement);
		super.checkInputBoxHasValue("patronLink", patronLink);
		super.checkInputBoxHasValue("status", status);
		super.checkNotSubmitExists("Update");
		super.signOut();
	}
}
