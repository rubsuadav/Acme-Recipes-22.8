package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamDeleteTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String desciption
		,final String initial, final String end, final String budget, final String link) {
		
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List pimpams");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, budget);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("desciption", desciption);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String title, final String desciption
		,final String initial, final String end, final String budget, final String link) {
		
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List pimpams");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, budget);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("desciption", desciption);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		
		super.checkNotSubmitExists("Delete");

		super.signOut();
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/chef/pimpam/delete");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/pimpam/delete");
		super.checkPanicExists();
		super.signOut();
		
	}

}