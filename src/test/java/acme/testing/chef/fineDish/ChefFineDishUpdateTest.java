
package acme.testing.chef.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefFineDishUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String request,
		final String budget, final String initial, final String creation, final String end, final String link, 
		final String epicureProfileFullName, final String epicureProfileEmail, final String epicureCompany, final String epicureStatement,
		final String epicureLink) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("epicureProfileFullName", epicureProfileFullName);
		super.checkInputBoxHasValue("epicureProfileEmail", epicureProfileEmail);
		super.checkInputBoxHasValue("epicureCompany", epicureCompany);
		super.checkInputBoxHasValue("epicureStatement", epicureStatement);
		super.checkInputBoxHasValue("epicureLink", epicureLink);
		
		super.fillInputBoxIn("status", status);
		super.clickOnSubmit("Update");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String status, final String code, final String request,
		final String budget, final String initial, final String creation, final String end, final String link, 
		final String epicureProfileFullName, final String epicureProfileEmail, final String epicureCompany, final String epicureStatement,
		final String epicureLink) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("epicureProfileFullName", epicureProfileFullName);
		super.checkInputBoxHasValue("epicureProfileEmail", epicureProfileEmail);
		super.checkInputBoxHasValue("epicureCompany", epicureCompany);
		super.checkInputBoxHasValue("epicureStatement", epicureStatement);
		super.checkInputBoxHasValue("epicureLink", epicureLink);
		super.checkInputBoxHasValue("status", status);
		
		super.checkNotSubmitExists("Update");
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/chef/fine-dish/update");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/fine-dish/update");
		super.checkPanicExists();
		super.signOut();
	}
}