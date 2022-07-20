package acme.testing.epicure.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class EpicureDashboardShowTest extends TestHarness{

	@Test
	public void positiveTest() {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "Dashboard");
		final BrowserDriver driver = super.getDriver();
		driver.executeScript("createDashboard",
			"numberOfFineDishesByStatus","averageNumberOfBudgetsByCurrencyAndStatus",
			"deviationOfBudgetsByCurrencyAndStatus","minBudgetByCurrencyAndStatus","maxBudgetByCurrencyAndStatus");

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
		super.navigate("/epicure/epicure-dashboard/show");
		super.checkPanicExists();

		super.signIn("chef1", "chef1");
		super.navigate("/epicure/epicure-dashboard/show");
		super.checkPanicExists();
		super.signOut();

	}

}