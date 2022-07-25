package acme.testing.chef.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class ChefQuantityCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/quantity/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recipeRecordIndex, final int recordIndex, final String number, final String itemId
		,final String itemName, final String itemCode) {
		
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
		
		super.checkButtonExists("Associate items to the recipe");
		super.clickOnButton("Associate items to the recipe");
		
		super.fillInputBoxIn("number", number);
		
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@id=\"itemId_proxy\"]/option[" + itemId +"]")).click();
		
		super.clickOnSubmit("Associate items to the recipe");

		super.checkNotErrorsExist();
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, number);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("item.name", itemName);
		super.checkInputBoxHasValue("item.code", itemCode);
		super.signOut();
		
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/quantity/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recipeRecordIndex,final int recordIndex, final String number, final String itemId) {

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
		
		super.checkButtonExists("Associate items to the recipe");
		super.clickOnButton("Associate items to the recipe");
		
		super.fillInputBoxIn("number", number);
		
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@id=\"itemId_proxy\"]/option[" + itemId +"]")).click();
		
		super.clickOnSubmit("Associate items to the recipe");

		super.checkErrorsExist();
		super.signOut();
		
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/chef/quantity/create");
		super.checkPanicExists();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/quantity/create");
		super.checkPanicExists();
		super.signOut();
		
	}

}