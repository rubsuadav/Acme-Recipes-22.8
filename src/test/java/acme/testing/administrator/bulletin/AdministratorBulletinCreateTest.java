package acme.testing.administrator.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorBulletinCreateTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String pieceOfText, final String criticalFlag,
		final String link, final String confirmation) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create bulletin");

		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("pieceOfText", pieceOfText);
		super.fillInputBoxIn("criticalFlag", criticalFlag);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);		
		super.clickOnSubmit("Create");

		super.clickOnMenu("Authenticated","List recent bulletins");
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 1, heading);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("pieceOfText", pieceOfText);
		super.checkInputBoxHasValue("link", link);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String heading, final String pieceOfText, final String criticalFlag,
		final String link, final String confirmation) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create bulletin");

		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("pieceOfText", pieceOfText);
		super.fillInputBoxIn("criticalFlag", criticalFlag);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", confirmation);

		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();

	}

	@Test
	@Order(30)
	public void hackingTest() {

		super.navigate("/administrator/bulletin/create");
		super.checkPanicExists();

		super.signIn("chef1", "chef1");
		super.navigate("/administrator/bulletin/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/administrator/bulletin/create");
		super.checkPanicExists();
		super.signOut();

	}

}