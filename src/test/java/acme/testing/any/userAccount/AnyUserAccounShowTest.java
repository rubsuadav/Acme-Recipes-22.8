package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccounShowTest extends TestHarness{

    // Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------

    @ParameterizedTest
    @CsvFileSource(resources = "/any/user-account/show.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveTest(final int recordIndex, final String roleList, final String name, final String surname, final String email) {

        super.clickOnMenu("Anonymous", "List User Accounts");
        super.checkListingExists();
        super.sortListing(1, "asc");

        super.checkColumnHasValue(recordIndex, 0, roleList);
        super.checkColumnHasValue(recordIndex, 1, name);
        super.checkColumnHasValue(recordIndex, 2, surname);

        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("identity.name", name);
        super.checkInputBoxHasValue("identity.surname", surname);
        super.checkInputBoxHasValue("identity.email", email);

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
		// HINT+ a)  poder ver los detalles de una cuenta de usuario que sea administrador;
		// HINT+ a)  poder ver los detalles de una cuenta de usuario que sea de anonimus;
	}


}