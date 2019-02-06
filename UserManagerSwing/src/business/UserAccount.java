package business;

import java.util.regex.Pattern;
import java.util.Date;

public class UserAccount {
	
	private String userName;
	private String password;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date registrationDate;
	private Date lastUpdateDate;
	
	public UserAccount() {	
	}
	
	public static String checkInputError(String userName, String password, String firstName, String lastName, String email, String phone){
		String errorMessage ="";
		if (!isUserNameValid(userName))
				errorMessage += "Invalid user name.\n";
		if (!isPasswordValid(password))
				errorMessage += "Invalid password.\n";	
		if (!isFirstNameValid(firstName))
			errorMessage += "Invalid first name.\n";
		if (!isLastNameValid(lastName))
			errorMessage += "Invalid last name.\n";
		if (!isEmailValid(email))
			errorMessage += "Invalid email.\n";
		if (!isPhoneNumberValid(phone))
			errorMessage += "Invalid phone number.\n";
		return errorMessage;
	}
	
	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

    /**
     * Checks to see if the username is valid
     * @param userName to verify 
     * @return the result
     */
    public static boolean isUserNameValid(String userName)
    {
    	boolean result = false;

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
    	
		if(userName.length() > 0 && Pattern.matches("^(?![0-9])(?!\\s)[a-zA-Z0-9]+$", userName))
    	{
	    	result = true;
    	}
    	    	
    	return result;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public static boolean isPasswordValid(String password){
		// check if password is valid
		if(Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$^+=!*()@%&]).{6,}$", password)) {
			return true;
		}
		return false;
	}

    public boolean isValidCredential(String userName, String password) {
         return matchUserName(userName) && matchPassword(password);
    }
    
    public boolean matchUserName(String userName) {
         return userName != null && userName.equals(this.userName);
    }
    
    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
   }

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

    /**
     * Checks to see if the first name meets requirements
     * @param firstName to verify 
     * @return the result of the validation
     */
    public static boolean isFirstNameValid(String firstName)
    {
    	boolean result = false;

/*    	^[a-zA-Z]+$
    	 |______||
    	     |   allow dups of any allowed characters
    	     |
    	     allowed characters
*/
    	
		if(firstName.length() > 0 && Pattern.matches("^[a-zA-Z]+$", firstName))
    	{
	    	result = true;
    	}
    	    	
    	return result;
    }
	
	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}


	
    /**
     * Checks to see if the last name is valid
     * @param lastName to verify
     * @return the result of the validation
     */
    public static boolean isLastNameValid(String lastName)
    {
    	boolean result = false;

/*    	^[a-zA-Z]+$
    	 |______||
    	     |   allow dups of any allowed characters
    	     |
    	     allowed characters
*/
    	
		if(lastName.length() > 0 && Pattern.matches("^[a-zA-Z]+$", lastName))
    	{
	    	result = true;
    	}
    	    	
    	return result;
    }

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

    /**
     * Checks to see if the email address is valid
     * @param email to validate
     * @return the result of validation
     */
    public static boolean isEmailValid(String email)
    {
    	boolean result = false;

    	//Pattern is from emailregex.com's Java regex section
		if(email.length() > 0 && Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", email))
    	{
	    	result = true;
    	}
    	    	
    	return result;
    }

	public String getPhoneNumber(){
		return phone;
	}

	public void setPhoneNumber(String phone){
		this.phone = phone;
	}

	
	/**
	 * verifies if the phone number is valid
	 * @param phone phone number to verify
	 * @return the result of validation
	 */
	public static boolean isPhoneNumberValid(String phone)
	{
		// check if phone (number) is valid
		boolean result = false;
		
		if(phone.length() > 0 && Pattern.matches("^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$", phone))
		{
			result = true;
		}
		
		return result;
	}

	public Date getRegistrationDate(){
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate){
		this.registrationDate = registrationDate;
	}

	public Date getLastUpdateDate(){
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate){
		this.lastUpdateDate = lastUpdateDate;
	}

}
