package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;

/**
 * 
 * @author Benjamin Clark
 *
 */
public class LoginTests extends TestCase{
	
	private UserAccountManager userManager;
	private UserAccount user;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new UserAccount();
		userManager = new UserAccountManager();
		userManager.registerNewUser("admin", "@BSUccp251", "@BSUccp251", "Dianxiang", "Xu", "dianxiangxu@boisestate.edu", "2084265734");
	}
	
	/**
	 * Tests if a user name has invalid parameters
	 */
	public void testUserNameValid() {
		assertFalse(user.isUserNameValid("b a")); //space in between
		assertFalse(user.isUserNameValid("")); //empty
		assertFalse(user.isUserNameValid("1hskdjfhsk")); //number in beginning	
	}
	
	/**
	 * Tests if a password has invalid parameters
	 */
	public void testPasswordValid(){
		assertFalse(user.isPasswordValid("")); //empty password
		assertFalse(user.isPasswordValid("sdhgsklhdgfhs")); //all lower case 
		assertFalse(user.isPasswordValid("g!7Gf")); //too short
		assertFalse(user.isPasswordValid("HelloO654")); //no special characters
		assertFalse(user.isPasswordValid("ghj123!")); //no capital letter
		assertFalse(user.isPasswordValid("GHJL123!!")); //no lower case letter
		assertFalse(user.isPasswordValid("Hello!!!@@@")); //no digits
		assertTrue(user.isPasswordValid("@BSUccp251"));
	}
}
