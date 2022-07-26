package acme.testing.chef.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefItemDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/item/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String typeEntity, final String name, final String code, 
		final String description, final String retailPrice, final String link) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My items");
		
		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("typeEntity", typeEntity);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.clickOnSubmit("Delete");
		
		super.checkListingExists();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/item/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String typeEntity, final String name, final String code, 
		final String description, final String retailPrice, final String link) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My items");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, typeEntity);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, code);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("typeEntity", typeEntity);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkNotSubmitExists("Delete");
		
		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		//Como epicure no poder borrar items
	}
	
}