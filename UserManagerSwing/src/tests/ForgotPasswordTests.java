package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;


/**
 * Tests the validity of the forgotPassword method in the UserAccountManager class
 * @author Benjamin Clark
 *
 */
public class ForgotPasswordTests extends TestCase{
	
	private UserAccountManager uManager;
	private UserAccount uAccount;
	String user = "trump";
	
	protected void setUp() {
		uManager = new UserAccountManager();
		uAccount = new UserAccount();
	}
	
	public void testValidUsername() {
	String registration = uManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
			"Trump", "trump@gmail.com", "7024265734");
	
	//valid username used
	assertEquals(uManager.forgotPassword(user), uManager.NOINPUTERROR);
	assertNotNull(uManager.forgotPassword(user));
}

public void testEmptyUsername() {
	String invalid= "";
	
	//no username entered
	assertFalse(uManager.forgotPassword(invalid).equals(uAccount.isUserNameValid(invalid)));
}

public void testNumberInBeginning() {
	
	String invalid = "1shkdjf";
	
	//number in beginnning
	assertFalse(uManager.forgotPassword(invalid).equals(uAccount.isUserNameValid(invalid)));
	
}

public void testSpaceInBetween() {
	
	String invalid = "tr ump";
	
	assertFalse(uManager.forgotPassword(invalid).equals(uAccount.isUserNameValid(invalid)));
}

public void testUserNameExists() {
	
	String invalid = "jsgfsk";
	
	String registration = uManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
			"Trump", "trump@gmail.com", "7024265734");
	
	assertFalse(uManager.forgotPassword(invalid).equals(uManager.doesUserNameExist(invalid)));
}
}
