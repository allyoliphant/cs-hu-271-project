package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;

/**
 * Tests update profile method in the UserAccountManager class
 * @author Benjamin Clark
 *
 */
public class UpdateProfileTests extends TestCase{
	
	private UserAccountManager uManager;
	private UserAccount uAccount;
	private String validUsername = "goro";
	private String validFirstName = "Goro";
	private String validLastName = "Grom";
	private String validPhoneNumber = "2085551111";
	private String validEmail = "goro123@gmail.com";
	private String validPassword = "gfdsj!GFH123";
	
	protected void setUp() {
		uManager = new UserAccountManager();
		uAccount = new UserAccount();
	}
	
	/**
	 * Tests valid update of profile
	 */
	public void testValidUpdate() {
	
	    String user = uManager.registerNewUser(validUsername, validPassword, validPassword, validFirstName, validLastName, validEmail, validPhoneNumber);
	    assertEquals(user, uManager.NOINPUTERROR);
	    UserAccount account = uManager.login(validUsername, validPassword);
		assertNotNull(account);
		assertNotNull(uManager.updateAccountProfile(uAccount, "jumo", "dghsjHJ123!", "dghsjHJ123!", "Jumo", "Jones", "Jumo@gmail.com", "2081112222"));
	}
	
	/**
	 * tests invalid user name
	 */
	public void testInvalidUserName() {
		 String user = uManager.registerNewUser(validUsername, validPassword, validPassword, validFirstName, validLastName, validEmail, validPhoneNumber);
		 String newUser = uManager.updateAccountProfile(uAccount, "1", "shdgfs!46G" , "shdgfs!46G", "John", "Jones", "John@gmail.com", "2084672347");
		 assertFalse(newUser.equals(uManager.NOINPUTERROR));
		 UserAccount account = uManager.login("1", "shdgfs!46G");
		 assertNull(account);
		 
		// assertTrue(uManager.updateAccountProfile(uAccount, "", "sdghfjHSgdJF!46", "sdghfjHSgdJF!46", "John", "Jones", "John@gmail.com", "2084561234").equals(null));
	}
	
	/**
	 * tests invalid phone number
	 */
	public void testInvalidPhoneNumber() {
		String newUser = uManager.updateAccountProfile(uAccount, "john", "shdgfs!46G" , "shdgfs!46G", "John", "Jones", "John@gmail.com", "20846");
		 assertFalse(newUser.equals(uManager.NOINPUTERROR));
		 UserAccount account = uManager.login("john", "shdgfs!46G");
		 assertFalse(uAccount.isPhoneNumberValid("20846"));
		 assertNull(account);
	}
	
	/**
	 * tests invalid password
	 */
	public void testInvalidPassword() {
		String newUser = uManager.updateAccountProfile(uAccount, "john", "shdgf" , "shdgf", "John", "Jones", "John@gmail.com", "2084672347");
		 assertFalse(newUser.equals(uManager.NOINPUTERROR));
		 assertFalse(uAccount.isPasswordValid("shdgf"));
		 UserAccount account = uManager.login("john", "shdgf");
		 assertNull(account);
	}
	
	/**
	 * tests invalid re-entered password
	 */
	public void testsInvalidPasswordRentered() {
		String newUser = uManager.updateAccountProfile(uAccount, "john", "shdgfs!46G" , "shdg", "John", "Jones", "John@gmail.com", "2084672347");
		 assertFalse(newUser.equals(uManager.NOINPUTERROR));
		 UserAccount account = uManager.login("john", "shdgfs!46G");
		 assertNull(account);
	}
	
	/**
	 * tests invalid first name
	 */
	public void testInvalidFirstName() {
		String newUser = uManager.updateAccountProfile(uAccount, "john", "shdgfs!46G" , "shdgfs!46G", "", "Jones", "John@gmail.com", "2084672347");
		assertFalse(uAccount.isFirstNameValid(""));
		assertFalse(newUser.equals(uManager.NOINPUTERROR));
		UserAccount account = uManager.login("john", "shdgfs!46G");
		assertNull(account);
	}
	
	/**
	 * tests invalid last name
	 */
	public void testInvalidLastName() {
		String newUser = uManager.updateAccountProfile(uAccount, "john", "shdgfs!46G" , "shdgfs!46G", "John", "", "John@gmail.com", "2084672347");
		assertFalse(uAccount.isLastNameValid(""));
		assertFalse(newUser.equals(uManager.NOINPUTERROR));
		UserAccount account = uManager.login("john", "shdgfs!46G");
		assertNull(account);
	}
	
	/**
	 * tests invalid email
	 */
	public void testInvalidEmail() {
		String newUser = uManager.updateAccountProfile(uAccount, "john", "shdgfs!46G" , "shdgfs!46G", "John", "Jones", "Johngmail.com", "2084672347");
		assertFalse(uAccount.isEmailValid("Johngmail.com"));
		assertFalse(newUser.equals(uManager.NOINPUTERROR));
		UserAccount account = uManager.login("john", "shdgfs!46G");
		assertNull(account);
	}
}
