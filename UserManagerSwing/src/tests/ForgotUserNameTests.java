package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;

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
}
