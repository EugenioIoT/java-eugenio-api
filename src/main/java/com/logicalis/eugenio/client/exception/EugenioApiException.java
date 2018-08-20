package com.logicalis.eugenio.client.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.logicalis.eugenio.client.api.common.dtos.EugenioErrorResponse;

/**
 * Exception raised when eugenio api returned any error.
 */
public class EugenioApiException extends WebApplicationException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 1L;
	private EugenioErrorResponse errorMessage;

	/**
	 * Error message constructor.
	 * @param message.
	 * @param response.
	 * @param errorResponse.
	 */
	public EugenioApiException(String message, Response response, EugenioErrorResponse errorMessage) {
		super(message, response);
		this.errorMessage = errorMessage;
	}

	/**
	 * Constructor.
	 * @param response with status code error.
	 */
	public EugenioApiException(Response response) {
		super(response);
	}

	/**
	 * Constructor.
	 * @param message description.
	 * @param response with status code error.
	 */
	public EugenioApiException(String message, Response response) {
		super(message, response);
	}

	/**
	 * Get message error returned from Eugenio.
	 * @return {@link EugenioErrorResponse}.
	 */
	public EugenioErrorResponse getErrorMessage() {
		return this.errorMessage;
	}

}
