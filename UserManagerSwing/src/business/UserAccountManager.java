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
    		
			UserAccount newAccount = new UserAccount();
				
    		successIndicator.append(newAccount.isUserNameValid(userName) ? "" : "Username invalid\r\n");
    		//successIndicator.append(newAccount.isPasswordValid(password,reenteredPassword) ? "" : "Password invalid\r\n");
    		successIndicator.append(newAccount.isFirstNameValid(firstName) ? "" : "First name invalid\r\n");
    		successIndicator.append(newAccount.isLastNameValid(lastName) ? "" : "Last name invalid\r\n");
    		successIndicator.append(newAccount.isEmailValid(email) ? "" : "Email invalid\r\n");
    		//successIndicator.append(newAccount.isPhoneNumberValid(phone) ? "" : "Email invalid\r\n");
    		
    		//no errors added to successIndicator
    		if(successIndicator.length() == 0)
    		{
        		setAccountProfile(newAccount, userName.toLowerCase(), password, firstName, lastName, email, phone);
        		newAccount.setRegistrationDate(new Date());
        		userAccounts.add(newAccount);
    		}

    	    return successIndicator.toString();
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
