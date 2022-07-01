package acme.testing.patron.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class PatronDashboardShowTest extends TestHarness{

	
	@Test
	public void positiveTest() {
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Patron", "Dashboard");
		final BrowserDriver driver = super.getDriver();
		driver.locateMany(By.id("patronagesByStatus"));
		driver.locateMany(By.id("averageNumberOfBudgetsByCurrencyAndStatus"));
		driver.locateMany(By.id("deviationOfBudgetsByCurrencyAndStatus"));
		driver.locateMany(By.id("minBudgetByCurrencyAndStatus"));
        driver.locateMany(By.id("maxBudgetByCurrencyAndStatus"));
        super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		// HINT: this is a listing, which implies that no data must be entered in any forms.
		// HINT+ Then, there are not any negative test cases for this feature.
	}
}