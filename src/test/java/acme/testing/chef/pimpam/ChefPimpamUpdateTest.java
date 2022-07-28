package acme.testing.chef.pimpam;

import java.time.LocalDate;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String desciption
		,final String initial, final String end, final String budget, final String link) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "List pimpams");
		
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		final LocalDate date = LocalDate.now();

		final int dayValue = date.getDayOfMonth();
		final String day = String.format("%02d", dayValue);

		final int monthValue = date.getMonthValue();
		final String month = String.format("%02d", monthValue);

		final int yearValue = date.getYear();
		final String year = String.format("%02d", yearValue).substring(2);

		final String correctlyDate = String.format("%s%s%s", day,month,year);

		super.fillInputBoxIn("code", code + correctlyDate);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("desciption", desciption);
		super.fillInputBoxIn("initial", initial);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Update");
		
		super.checkNotErrorsExist();

		super.checkColumnHasValue(recordIndex, 0, code + correctlyDate);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, budget);
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("code", code + correctlyDate);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("desciption", desciption);
		super.checkInputBoxHasValue("initial", initial);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);

		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String title, final String desciption
		,final String initial, final String end, final String budget, final String link) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "List pimpams");
		
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("desciption", desciption);
		super.fillInputBoxIn("initial", initial);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Update");
		super.checkErrorsExist();

		super.signOut();

	}

}