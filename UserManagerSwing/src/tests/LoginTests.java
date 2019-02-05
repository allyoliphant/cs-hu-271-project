package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;

/**
 * 
 * @author James Brooks
 *
 */
public class LoginTests extends TestCase{
	
	private UserAccountManager userManager;
	private UserAccount user;
	private String username = "admin";
	private String password = "@BSUccp251";
	
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new UserAccount();
		userManager = new UserAccountManager();
		userManager.registerNewUser(username, password, password, "Dianxiang", "Xu", "dianxiangxu@boisestate.edu", "2084265734");
	}
	
	public void testValidLogin() {
		user = userManager.login(username, password);
		assertNotNull(user);
	}

	public void testInvalidUsernameLogin() {
		user = userManager.login("invalid", password);
		assertNull(user);
	}
	
	public void testInvalidPasswordLogin() {
		user = userManager.login(username, "invalid");
		assertNull(user);
	}
}
