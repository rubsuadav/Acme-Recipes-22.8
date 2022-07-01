package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/system-configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String acceptedCurrencies, final String systemCurrency,
		final String source, final String currencyTarget) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "System Configuration");
		super.checkFormExists();
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.clickOnButton("Money exchange");
		
		super.checkFormExists();
		super.fillInputBoxIn("source", source);
		super.fillInputBoxIn("currencyTarget", currencyTarget);
		super.clickOnSubmit("Perform exchange");
		super.signOut();
	}

}