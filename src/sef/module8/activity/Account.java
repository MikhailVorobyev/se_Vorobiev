package sef.module8.activity;


/**
 * Thsi class represents a simple representation of an account encapsulating
 * a name 
 * 
 * @author John Doe
 *
 */
public class Account {

	private String accountName;

	/**
	 * Creates an Account object with the specified name.  If the account name
	 * given violates the minimum requirements, then an AccountException is thrown
	 * 
	 * @param accountName
	 * @throws AccountException
	 */
	public  Account(String accountName) throws AccountException{
		if (accountName.length() < 4) {
			throw new AccountException(AccountException.NAME_TOO_SHORT, accountName);
		}

		boolean letter = false;
		boolean digit = false;
		for (char ch : accountName.toCharArray()) {
			if (Character.isDigit(ch)) {
				digit = true;
			} else if (Character.isLetter(ch)) {
				letter = true;
			}
		}

		if (!letter || !digit) {
			throw new AccountException(AccountException.NAME_TOO_SIMPLE, accountName);

		}
		this.accountName = accountName;
	}
	
	
	/**
	 * Returns the account name
	 * 
	 * @return the account name
	 */
	public String getName(){
		return accountName;
	}
}
