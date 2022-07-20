package acme.testing.any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AnyPeepListRecentTest extends TemporalAwareTestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String writer, final String pieceOfText, final String email, final int deltaDays) {
		
		String moment;
		moment = super.computeDeltaMoment(deltaDays);

		super.clickOnMenu("Anonymous", "List recent peeps");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, writer);
		super.checkColumnHasValue(recordIndex, 3, pieceOfText);
		super.checkColumnHasValue(recordIndex, 4, email);

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
		//No tiene puesto que el role es any--> todo el mundo puede ver los peeps recientes
		
	}

}