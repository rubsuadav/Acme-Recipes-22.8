package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class AdministratorDashboardShowTest extends TestHarness{

	@Test
	public void positiveTest() {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		final BrowserDriver driver = super.getDriver();
		driver.executeScript("createDashboard", 
			"averageRetailPriceOfKitchenUtensilsByCurrency","deviationRetailPriceOfKitchenUtensilsByCurrency",
			"minRetailPriceOfKitchenUtensilsByCurrency","maxRetailPriceOfKitchenUtensilsByCurrency",
			"averageRetailPriceOfIngredientsByCurrency","deviationRetailPriceOfIngredientsByCurrency",
			"minRetailPriceOfIngredientsByCurrency","maxRetailPriceOfIngredientsByCurrency",
			"numberOfFineDishesByStatus","averageBudgetOfFineDishesByStatus",
			"deviationBudgetOfFineDishesByStatus","minBudgetOfFineDishesByStatus","maxBudgetOfFineDishesByStatus");

        super.signOut();
        
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
		super.navigate("/administrator/administrator-dashboard/show");
		super.checkPanicExists();

		super.signIn("chef1", "chef1");
		super.navigate("/administrator/administrator-dashboard/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/administrator/administrator-dashboard/show");
		super.checkPanicExists();
		super.signOut();

	}
	
}