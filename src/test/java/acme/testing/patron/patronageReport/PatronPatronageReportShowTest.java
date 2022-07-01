package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String creation, final String patronageId, final String automaticSequenceNumber, final String memorandum, final String link) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "My patronage reports");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, creation);
		super.checkColumnHasValue(recordIndex, 1, patronageId);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequenceNumber);
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}
}
