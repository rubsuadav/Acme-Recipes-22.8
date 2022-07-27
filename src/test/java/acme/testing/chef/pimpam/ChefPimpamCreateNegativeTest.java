package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamCreateNegativeTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int itemRecordIndex, final int recordIndex, final String code, final String title, final String desciption
		,final String initial, final String end, final String budget, final String link) {
		
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "My items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(itemRecordIndex);
		super.clickOnButton("Pimpam");
		
		super.checkButtonExists("Associate pimpam");
		super.clickOnButton("Associate pimpam");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("desciption", desciption);
		super.fillInputBoxIn("initial", initial);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Associate pimpam");
		super.checkErrorsExist();

		super.signOut();
		
	}

}