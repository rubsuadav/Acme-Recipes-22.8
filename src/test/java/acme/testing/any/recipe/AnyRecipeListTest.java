package acme.testing.any.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyRecipeListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/recipe/list-recipes.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String heading) {
		super.clickOnMenu("Anonymous", "List published recipes");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);
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
		//No tiene puesto que el role es any y todo el mundo puede ver toolkits publicados
		
	}

}
