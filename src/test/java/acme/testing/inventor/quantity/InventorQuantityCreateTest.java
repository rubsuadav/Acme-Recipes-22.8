package acme.testing.inventor.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class InventorQuantityCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/quantity/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int toolkitRecordIndex, final int recordIndex, final String number, final String itemId
		,final String itemName, final String itemCode) {
		
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
		
		super.checkButtonExists("Associate items to the toolkit");
		super.clickOnButton("Associate items to the toolkit");
		
		super.fillInputBoxIn("number", number);
		
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@id=\"itemId_proxy\"]/option[" + itemId +"]")).click();
		
		super.clickOnSubmit("Associate items to the toolkit");

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
	@CsvFileSource(resources = "/inventor/quantity/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int toolkitRecordIndex,final int recordIndex, final String number, final String itemId) {

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
		
		super.checkButtonExists("Associate items to the toolkit");
		super.clickOnButton("Associate items to the toolkit");
		
		super.fillInputBoxIn("number", number);
		
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@id=\"itemId_proxy\"]/option[" + itemId +"]")).click();
		
		super.clickOnSubmit("Associate items to the toolkit");

		super.checkErrorsExist();
		super.signOut();
		
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/quantity/create");
		super.checkPanicExists();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/quantity/create");
		super.checkPanicExists();
		super.signOut();
		
	}

}