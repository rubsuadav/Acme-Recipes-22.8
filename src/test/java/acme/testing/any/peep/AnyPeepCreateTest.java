package acme.testing.any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPeepCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/create-positive.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String writer, final String pieceOfText, 
		final String email, final String confirmation) {
		
		super.clickOnMenu("Anonymous","List recent peeps");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("writer", writer);
		super.fillInputBoxIn("pieceOfText", pieceOfText);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/create-negative.csv", encoding = "utf-8",numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String heading, final String writer, final String pieceOfText, 
		final String email, final String confirmation) {
		
		super.clickOnMenu("Anonymous","List recent peeps");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("writer", writer);
		super.fillInputBoxIn("pieceOfText", pieceOfText);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		//No tiene puesto que el role es any--> todo el mundo puede crear peeps
		
	}
	
}