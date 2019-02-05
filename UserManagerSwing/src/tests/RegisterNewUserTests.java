package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;

/**
 * @author James Brooks, Benjamin Clark
 *
 */
public class RegisterNewUserTests extends TestCase {

	private UserAccountManager userAccountManager;

	protected void setUp() {
		userAccountManager = new UserAccountManager();
	}

	public void testSuccessfulRegistration() {
		String registrationResult = userAccountManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		assertEquals(registrationResult, UserAccountManager.NOINPUTERROR);
		UserAccount userAccount = userAccountManager.login("trump", "@White0House"); // valid login
		assertNotNull(userAccount); // valid account
		userAccount.getUserName().equalsIgnoreCase("trump"); // valid user name
		userAccount.getEmail().equalsIgnoreCase("trump@gmail.com"); // valid email
	}
	
	public void testInvalidUserName() {
		String registrationResult = userAccountManager.registerNewUser("1trump", "@White0House", "@White0House", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		assertFalse(registrationResult.equals(UserAccountManager.NOINPUTERROR));
		//check to make sure account was not created
		UserAccount userAccount = userAccountManager.login("1trump", "@White0House"); // invalid login
		assertNull(userAccount); // account does not exist
	}
	
	public void testExistingUserName() {
		//register user
		String preRegistrationResult = userAccountManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		assertEquals(preRegistrationResult, UserAccountManager.NOINPUTERROR);
		
		//attempt to register with existing username
		String registrationResult = userAccountManager.registerNewUser("trump", "@House0White", "@House0White", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		assertFalse(registrationResult.equals(UserAccountManager.NOINPUTERROR));
		//check to make sure account was not created
		UserAccount userAccount = userAccountManager.login("trump", "@House0White"); // invalid login
		assertNull(userAccount); // account does not exist
	}
	
	public void testInvalidPassword() {
		String registrationResult = userAccountManager.registerNewUser("trump", "house", "house", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		assertFalse(registrationResult.equals(UserAccountManager.NOINPUTERROR));
		//check to make sure account was not created
		UserAccount userAccount = userAccountManager.login("trump", "house"); // invalid login
		assertNull(userAccount); // account does not exist
	}
	
	public void testNonMatchingPassword() {
		String registrationResult = userAccountManager.registerNewUser("trump", "@House0White", "@White0House", "Don",
				"Trump", "trump@gmail.com", "7024265734");
		assertFalse(registrationResult.equals(UserAccountManager.NOINPUTERROR));
		//check to make sure account was not created
		UserAccount userAccount = userAccountManager.login("trump", "@White0House"); // invalid login
		assertNull(userAccount); // account does not exist
	}
	
	public void testInvalidEmail() {
		String registrationResult = userAccountManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
				"Trump", "trumpgmail.com", "7024265734");
		assertFalse(registrationResult.equals(UserAccountManager.NOINPUTERROR));
		//check to make sure account was not created
		UserAccount userAccount = userAccountManager.login("trump", "@White0House"); // invalid login
		assertNull(userAccount); // account does not exist
	}

	public void testInvalidPhoneNumber() {
		String registrationResult = userAccountManager.registerNewUser("trump", "@White0House", "@White0House", "Don",
				"Trump", "trump@gmail.com", "702426");
		assertFalse(registrationResult.equals(UserAccountManager.NOINPUTERROR));
		//check to make sure account was not created
		UserAccount userAccount = userAccountManager.login("trump", "@White0House"); // invalid login
		assertNull(userAccount); // account does not exist
	}
	

	/**
	 * Tests if a user name has invalid parameters
	 */
	public void testIsUserNameValid() {
		assertFalse(UserAccount.isUserNameValid("b a")); //space in between
		assertFalse(UserAccount.isUserNameValid("")); //empty
		assertFalse(UserAccount.isUserNameValid("1hskdjfhsk")); //number in beginning	
	}
	
	/**
	 * Tests if a password has invalid parameters
	 */
	public void testIsPasswordValid(){
		assertFalse(UserAccount.isPasswordValid("")); //empty password
		assertFalse(UserAccount.isPasswordValid("sdhgsklhdgfhs")); //all lower case 
		assertFalse(UserAccount.isPasswordValid("g!7Gf")); //too short
		assertFalse(UserAccount.isPasswordValid("HelloO654")); //no special characters
		assertFalse(UserAccount.isPasswordValid("ghj123!")); //no capital letter
		assertFalse(UserAccount.isPasswordValid("GHJL123!!")); //no lower case letter
		assertFalse(UserAccount.isPasswordValid("Hello!!!@@@")); //no digits
		assertTrue(UserAccount.isPasswordValid("@BSUccp251"));
	}
}
