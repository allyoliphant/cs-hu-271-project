package business;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author Tom Hess
 *
 */
public class UserAccountManager {
	
	public static final String NOINPUTERROR ="";
	
    private ArrayList<UserAccount> userAccounts;
    
    public UserAccountManager() {
        userAccounts = new ArrayList<UserAccount>();
    }
        
    // You need to complete this method
    public String registerNewUser(String userName, String password, String reenteredPassword, 
    		String firstName, String lastName, String email, String phone){
    		// check if userName, password, firstName, lastName, email, or phone is invalid 
    		// if invalid, return error message
    		// if reenteredPassword does not match password
    		// return an error message;
    		// if userName already exists
    		// return an error message;
    		StringBuilder successIndicator = new StringBuilder();
    		
    		successIndicator.append(isUserNameValid(userName));
    		//successIndicator.append(isPasswordValid(password,reenteredPassword));
    		successIndicator.append(isFirstNameValid(firstName));
    		successIndicator.append(isLastNameValid(lastName));
    		successIndicator.append(isEmailValid(email));
    		//successIndicator.append(isPhoneNumberValid(phone));
    		
    		//no errors added to successIndicator
    		if(successIndicator.length() == 0)
    		{
        		UserAccount newAccount = new UserAccount();
        		setAccountProfile(newAccount, userName.toLowerCase(), password, firstName, lastName, email, phone);
        		newAccount.setRegistrationDate(new Date());
        		userAccounts.add(newAccount);
    		}

    	    return successIndicator.toString();
    }
    
    /**
     * Checks to see if the entered username meets requirements
     * @param validateMe the username to validate
     * @return an empty string indicating success, or the error message
     */
    private String isUserNameValid(String validateMe)
    {
    	StringBuilder userNameIssues = new StringBuilder(NOINPUTERROR);
    	
    	if(validateMe.length() == 0)
    	{
    		userNameIssues.append("User name not entered\r\n");
    	}

/*    	^(?![0-9])(?!\s)[a-zA-Z0-9]+$
    	 |_______||____||_________||
    	     |      |        |     allow dups of any allowed characters
    	     |      |        |
    	     |      |        allowed characters
    	     |      |
    	     |      no spaces allowed inside
    	     |
    	     no 0-9 at the beginning
*/
    	
		if(validateMe.length() > 0 && !Pattern.matches("^(?![0-9])(?!\\s)[a-zA-Z0-9]+$", validateMe))
    	{
	    	userNameIssues.append("User name invalid, check requirements\r\n");
    	}
    	    	
    	return userNameIssues.toString();
    }
 
    /**
     * Checks to see if the first name meets requirements
     * @param validateMe the string to be validated
     * @return an empty string indicating success, or the error message
     */
    private String isFirstNameValid(String validateMe)
    {
    	StringBuilder firstNameIssues = new StringBuilder(NOINPUTERROR);
    	
    	if(validateMe.length() == 0)
    	{
    		firstNameIssues.append("First name not entered\r\n");
    	}

/*    	^[a-zA-Z]+$
    	 |______||
    	     |   allow dups of any allowed characters
    	     |
    	     allowed characters
*/
    	
		if(validateMe.length() > 0 && !Pattern.matches("^[a-zA-Z]+$", validateMe))
    	{
	    	firstNameIssues.append("First name invalid, it may contain only letters\r\n");
    	}
    	    	
    	return firstNameIssues.toString();
    }

    /**
     * Checks to see if the last name meets requirements
     * @param validateMe the string to be validated
     * @return an empty string indicating success, or the error message
     */
    private String isLastNameValid(String validateMe)
    {
    	StringBuilder lastNameIssues = new StringBuilder(NOINPUTERROR);
    	
    	if(validateMe.length() == 0)
    	{
    		lastNameIssues.append("Last name not entered\r\n");
    	}

/*    	^[a-zA-Z]+$
    	 |______||
    	     |   allow dups of any allowed characters
    	     |
    	     allowed characters
*/
    	
		if(validateMe.length() > 0 && !Pattern.matches("^[a-zA-Z]+$", validateMe))
    	{
	    	lastNameIssues.append("Last name invalid, it may contain only letters\r\n");
    	}
    	    	
    	return lastNameIssues.toString();
    }

    /**
     * Checks to see if the email address is valid
     * @param validateMe the string to be validated
     * @return and empty string indicating success, or the error message
     */
    private String isEmailValid(String validateMe)
    {
    	StringBuilder emailIssues = new StringBuilder(NOINPUTERROR);
    	
    	if(validateMe.length() == 0)
    	{
    		emailIssues.append("Email address not entered\r\n");
    	}

    	//Pattern is from emailregex.com's Java regex section
		if(validateMe.length() > 0 && !Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", validateMe))
    	{
	    	emailIssues.append("Invalid email adress entered\r\n");
    	}
    	    	
    	return emailIssues.toString();
    }
    
    // You need to complete this method
    public String updateAccountProfile(UserAccount existingAccount, 
    		String userName, String password, String reenteredPassword, 
    		String firstName, String lastName, String email, String phone){
		// check if userName, password, firstName, lastName, email, or phone is invalid 
		// if invalid, return error message
		// if reenteredPassword does not match password
		// return an error message;
    		// if there is no profile change (refer to method hasProfileChanges below), return a message 
		// if userName is changed and the new userName already exists
		// return an error message;
  		setAccountProfile(existingAccount, userName, password, firstName, lastName, email, phone);
  		existingAccount.setLastUpdateDate(new Date());
	    return NOINPUTERROR;
    }
    
    // You need to complete this method. 
    // It should be called in method updateAccountProfile
    private boolean hasProfileChanges(UserAccount existingAccount, String userName, String password, 
    		String firstName, String lastName, String email, String phone){
    		// check profile change 
		return true; // you may change this statement if necessary
    }

    private void setAccountProfile(UserAccount userAccount, String userName, String password, String firstName, String lastName, String email, String phone){
		userAccount.setUserName(userName);
		userAccount.setPassword(password);
		userAccount.setFirstName(firstName);
		userAccount.setLastName(lastName);
		userAccount.setEmail(email);
		userAccount.setPhoneNumber(phone);
    }
    
    // return the user account if the given userName and password are correct
    // otherwise null
    public UserAccount login(String userName, String password) {
    		for (UserAccount userAccount: userAccounts)
    			if(userAccount.isValidCredential(userName, password))    
    				return userAccount;   
    		return null;
    }
    
    public boolean doesUserNameExist(String userName){
    		for (UserAccount userAccount: userAccounts)
    			if(userAccount.matchUserName(userName))   
    				return true;   
    		return false;
    }

	// you need to complete this method
 	public String forgotUserName(String email){
		// Check if the given email is valid
		// if not, return an error message
		// otherwise check if there is an account that matches the given email
		// if found, send the user name to the email address
		// otherwise return an error message
		return NOINPUTERROR; // you may change this statement if necessary
   	
    }

	// you need to complete this method
    public String forgotPassword(String userName){
		// Check if the given user name is valid
		// if not, return an error message
		// check if there is an account that matches the given user name
		// if found, send the password to the email address
		// otherwise return an error message
	    return NOINPUTERROR; // you may change this statement if necessary
    }

    
}
