package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class AdministratorDashboardShowTest extends TestHarness{

	@Test
	public void positiveTest() {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		final BrowserDriver driver = super.getDriver();
		driver.locateMany(By.id("averageRetailPriceOfComponentsByTechnologyAndCurrency"));
		driver.locateMany(By.id("deviationRetailPriceOfComponentsByTechnologyAndCurrency"));
		driver.locateMany(By.id("minRetailPriceOfComponentsByTechnologyAndCurrency"));
		driver.locateMany(By.id("maxRetailPriceOfComponentsByTechnologyAndCurrency"));
        driver.locateMany(By.id("averageRetailPriceOfToolsByCurrency"));
        driver.locateMany(By.id("deviationRetailPriceOfToolsByCurrency"));
        driver.locateMany(By.id("minRetailPriceOfToolsByCurrency"));
        driver.locateMany(By.id("maxRetailPriceOfToolsByCurrency"));
        driver.locateMany(By.id("numberOfPatronagesByStatus"));
        driver.locateMany(By.id("averageBudgetOfPatronagesByStatus"));
        driver.locateMany(By.id("deviationBudgetOfPatronagesByStatus"));
        driver.locateMany(By.id("minBudgetOfPatronagesByStatus"));
        driver.locateMany(By.id("maxBudgetOfPatronagesByStatus"));
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
		// HINT+ a) estando logueado como patron,anonimo e inventor no poder ver el panel de indicadores de administradores

	}
	
}
