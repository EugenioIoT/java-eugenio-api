package com.logicalis.eugenio.client.exception;

/**
 * Exception raised when there is any error before call the Eugenio api.
 */
public class EugenioClientException extends Exception {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public EugenioClientException() {
	}

	/**
	 * Constructor.
	 * @param message.
	 */
	public EugenioClientException(String message) {
		super(message);
	}

}
