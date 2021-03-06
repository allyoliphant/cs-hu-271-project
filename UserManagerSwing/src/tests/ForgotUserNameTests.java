package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;

/**
 * Tests the validity of the forgotUserName method in the UserAccountManager class
 * @author Benjamin Clark
 *
 */
public class ForgotUserNameTests extends TestCase{
	
	private UserAccountManager uManager;
	private UserAccount uAccount;
	
	protected void setUp() {
		uManager = new UserAccountManager();
		uAccount = new UserAccount();
	}
	
	public void testValidEmail() {
		String email = "trump@gmail.com";
		String registration = uManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		
		//valid email used
		assertEquals(uManager.forgotUserName(email), uManager.NOINPUTERROR);
		assertNotNull(uManager.forgotUserName(email));
	}
	
	public void testEmptyEmail() {
		String invalidEmpty= "";
		
		//no email entered
		assertFalse(uManager.forgotUserName(invalidEmpty).equals(uManager.NOINPUTERROR));
	}
	
	public void testInvalidEmailInput() {
		String registration = uManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		
		String invalid = "trump";
		String invalid2 = "@gmail.com";
		String invalid3 = "trumpgmail.com";
		String invalid4 = "trump @gmail.com";
		String invalid5 = " trump@gmail.com";
		String invalid6 = "trump@gmail.com ";
		
		assertFalse(uManager.forgotUserName(invalid).equals(UserAccount.isEmailValid(invalid)));
		assertFalse(uManager.forgotUserName(invalid2).equals(UserAccount.isEmailValid(invalid2)));
		assertFalse(uManager.forgotUserName(invalid3).equals(UserAccount.isEmailValid(invalid3)));
		assertFalse(uManager.forgotUserName(invalid4).equals(UserAccount.isEmailValid(invalid4)));
		assertFalse(uManager.forgotUserName(invalid5).equals(UserAccount.isEmailValid(invalid5)));
		assertFalse(uManager.forgotUserName(invalid6).equals(UserAccount.isEmailValid(invalid6)));
	}
}
