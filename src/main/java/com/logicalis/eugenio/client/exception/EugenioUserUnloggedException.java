package com.logicalis.eugenio.client.exception;

/**
 * Exception raised when a method which requires the user to be logged is call before the login.
 */
public class EugenioUserUnloggedException extends RuntimeException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constant user has to be logged.
	 */
	private static final String USER_HAS_TO_BE_LOGGED = "User has to be logged to access this point.";

	/**
	 * Constructor.
	 */
	public EugenioUserUnloggedException() {
		super(USER_HAS_TO_BE_LOGGED);
	}

}
