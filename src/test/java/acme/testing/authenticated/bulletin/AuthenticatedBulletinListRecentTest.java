package acme.testing.authenticated.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AuthenticatedBulletinListRecentTest extends TemporalAwareTestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final int deltaDays) {
		
		String moment;
		
		moment = super.computeDeltaMoment(deltaDays);
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Authenticated", "List recent bulletins");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, heading);

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
		super.navigate("/authenticated/bulletin/list-recent");
		super.checkPanicExists();
	}

}