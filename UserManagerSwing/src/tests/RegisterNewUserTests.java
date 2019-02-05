package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;


public class RegisterNewUserTests extends TestCase{
	
	private UserAccountManager userAccountManager;
	
	protected void setUp() {
	userAccountManager = new UserAccountManager();
	
	}
	
	public void testSuscessfulRegistration(){
	String registrationResult = userAccountManager.registerNewUser("trump", "@White0House", "@White0House", "Don", "Trump", "trump@gmail.com", "7024265734");
	assertEquals(registrationResult, UserAccountManager.NOINPUTERROR);
	UserAccount userAccount = userAccountManager.login("trump", "@White0House"); //valid login
	assertNotNull(userAccount); //valid account
	userAccount.getUserName().equalsIgnoreCase("trump"); //valid user name
	userAccount.getEmail().equalsIgnoreCase("trump@gmail.com"); //valid email
	}
	}
