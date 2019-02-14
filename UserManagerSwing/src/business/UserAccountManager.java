package business;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Tom Hess, James Brooks, Benjamin Clark, Ally Oliphant
 *
 */
public class UserAccountManager {
	
	public static final String NOINPUTERROR ="";
	
    private ArrayList<UserAccount> userAccounts;
    
    public UserAccountManager() {
        userAccounts = new ArrayList<UserAccount>();
    }
          
    /**
     * check if userName, password, firstName, lastName, email, or phone is invalid
     * @param userName the username to verify
     * @param password the password  to verify
     * @param reenteredPassword the password to match against the entered password
     * @param firstName the first name  to verify
     * @param lastName the last name  to verify
     * @param email the email  to verify
     * @param phone the phone  to verify
     * @return an error indicating any problems or complete success
     */
    public String registerNewUser(String userName, String password, String reenteredPassword, 
    		String firstName, String lastName, String email, String phone){

    	StringBuilder successIndicator = new StringBuilder();
    		
			UserAccount newAccount = new UserAccount();
				
    		successIndicator.append(UserAccount.isUserNameValid(userName) ? "" : "Username invalid\r\n");
    		successIndicator.append(!doesUserNameExist(userName) ? "" : "Username already exists\r\n");
    		successIndicator.append(UserAccount.isPasswordValid(password) ? "" : "Password invalid\r\n");
    		successIndicator.append(password.equals(reenteredPassword) ? "" : "Passwords do not match\r\n");
    		successIndicator.append(UserAccount.isFirstNameValid(firstName) ? "" : "First name invalid\r\n");
    		successIndicator.append(UserAccount.isLastNameValid(lastName) ? "" : "Last name invalid\r\n");
    		successIndicator.append(UserAccount.isEmailValid(email) ? "" : "Email invalid\r\n");
    		successIndicator.append(UserAccount.isPhoneNumberValid(phone) ? "" : "Phone number invalid\r\n");
    		
    		//no errors added to successIndicator
    		//process the registration
    		if(successIndicator.length() == 0)
    		{
        		setAccountProfile(newAccount, userName.toLowerCase(), password, firstName, lastName, email, phone);
        		newAccount.setRegistrationDate(new Date());
        		userAccounts.add(newAccount);
    		}

    	    return successIndicator.toString();
    }
    
    /**
     * Updates the account profile when requested
     * @param existingAccount users current account
     * @param userName new user name
     * @param password new password
     * @param reenteredPassword confirm password
     * @param firstName new first name
     * @param lastName new last name
     * @param email new email
     * @param phone new phone
     * @return errors or success
     */
    public String updateAccountProfile(UserAccount existingAccount, 
    		String userName, String password, String reenteredPassword, 
    		String firstName, String lastName, String email, String phone){
   	
    	StringBuilder successIndicator = new StringBuilder();
    	
    	//ignore empty strings
    	successIndicator.append(userName != "" && !UserAccount.isUserNameValid(userName) ? "" : "New username invalid.\r\n");
    	successIndicator.append(password != "" && !UserAccount.isPasswordValid(password) ? "" : "Password invalid.\r\n");
    	successIndicator.append(firstName != "" && !UserAccount.isFirstNameValid(firstName) ? "" : "First name invalid.\r\n");
    	successIndicator.append(lastName != "" && !UserAccount.isLastNameValid(lastName) ? "" : "Last name invalid.\r\n");
    	successIndicator.append(email != "" && !UserAccount.isEmailValid(email) ? "" : "Email invalid.\r\n");
    	successIndicator.append(phone != "" && !UserAccount.isPhoneNumberValid(phone) ? "" : "Phone number invalid.\r\n");
    	successIndicator.append(password.equals(reenteredPassword) ? "" : "Passwords don't match.\r\n");
    	successIndicator.append(userName != "" && doesUserNameExist(userName) ? "User name not available" : "");
    	
    	if(successIndicator.length() == 0 && hasProfileChanges(existingAccount, userName, password, 
    			firstName, lastName, email, phone))
    	{
        	setAccountProfile(existingAccount, userName, password, firstName, lastName, email, phone);
      		existingAccount.setLastUpdateDate(new Date());
    	}
    	else
    	{
    		successIndicator.append("No changes to account.");
    	}
    	
	    return successIndicator.toString();
    }
    
    /**
     * Return true when at least one property from the form does not match what's on file
     * @param existingAccount The account for the logged in user
     * @param userName new username
     * @param password new password
     * @param firstName new first name
     * @param lastName new last name
     * @param email new email
     * @param phone new phone
     * @return whether there's at least one change
     */
    private boolean hasProfileChanges(UserAccount existingAccount, String userName, String password, 
    		String firstName, String lastName, String email, String phone){
    	
    	boolean hasChanges = false;
    	
    	//check for problem values and make sure it's not the same as on file
    	//check the most common change first
    	if(!existingAccount.getPassword().equals(password))
    	{
    		hasChanges = true;
    	}
    	
    	//don't bother checking if the hasChanges is already true because there's at least one change
    	if(hasChanges == false && (!existingAccount.getUserName().equals(userName)))
    	{
    		hasChanges = true;
    	}
    	
    	if(hasChanges == false && (!existingAccount.getFirstName().equals(firstName)))
    	{
    		hasChanges = true;
    	}    	

    	if(hasChanges == false && (!existingAccount.getLastName().equals(lastName)))
    	{
    		hasChanges = true;
    	}    	
    	
    	if(hasChanges == false && (!existingAccount.getEmail().equals(email)))
    	{
    		hasChanges = true;
    	}
    	
    	if(hasChanges == false && (!existingAccount.getPhoneNumber().equals(phone)))
    	{
    		hasChanges = true;
    	}
    	
    	return hasChanges;
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

    private UserAccount getUserByEmail(String email){
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.getEmail().equals(email))   
    			return userAccount; 
    	return null;
    }
    
    private UserAccount getUserByUsername(String username) {
    	for (UserAccount userAccount: userAccounts)
			if(userAccount.getUserName().equals(username))   
				return userAccount;
		return null;
    }

 	public String forgotUserName(String email){
		// Check if the given email is valid
 		if(!UserAccount.isEmailValid(email)) {
 			// if not, return an error message
 			return "Email is not valid";
 		}

		// otherwise check if there is an account that matches the given email
 		UserAccount user = getUserByEmail(email);

		// if found, send the user name to the email address
 		if(user != null)
 			sendEmail(email, user.getUserName());
		// otherwise return an error message
 		else
 			return "No username associated with given email";

		return NOINPUTERROR;
    }

    public String forgotPassword(String userName){
		// Check if the given user name is valid
    	if(!UserAccount.isUserNameValid(userName)) {
 			// if not, return an error message
 			return "Username is not valid";
 		}
    	
		// check if there is an account that matches the given user name
    	UserAccount user = getUserByUsername(userName);

		// if found, send the password to the email address
    	if(user != null)
    		sendEmail(user.getEmail(), user.getPassword());
    	else
    		// otherwise return an error message
    		return "No account associated with given username";

	    return NOINPUTERROR;
    }

 	private void sendEmail(String receipant, String body) {
 		
 	}
}
