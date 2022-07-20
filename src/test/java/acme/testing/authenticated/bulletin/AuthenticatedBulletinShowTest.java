package acme.testing.authenticated.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AuthenticatedBulletinShowTest extends TemporalAwareTestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String pieceOfText, 
		final String criticalFlag, final String link, final int deltaDays) {
		
		String moment;
		
		moment = super.computeDeltaMoment(deltaDays);
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Authenticated", "List recent bulletins");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 1, heading);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("instantiationMoment", moment);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("pieceOfText", pieceOfText);
		super.checkInputBoxHasValue("criticalFlag", criticalFlag);
		super.checkInputBoxHasValue("link", link);

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
		// HINT+ a)  poder ver los detalles de un anuncio que no sea de un mes de publicacion
	}

}