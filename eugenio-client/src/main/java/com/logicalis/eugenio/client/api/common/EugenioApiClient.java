package com.logicalis.eugenio.client.api.common;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import com.logicalis.eugenio.client.api.common.dtos.EugenioErrorResponse;
import com.logicalis.eugenio.client.exception.EugenioApiException;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;

/**
 * Class responsible to connect with eugenio api.
 */
public abstract class EugenioApiClient {

	private String uri;
	protected Client client;

	/**
	 * Constructor to use when you don't need to log before call the service: only on login.
	 * @param infoLoginDTO.
	 * @param client {@link Client}.
	 */
	public EugenioApiClient(String uri, Client client) {
		this.uri = uri;
		this.client = client;
	}


	/**
	 * Validate response status. Throws an exception if it's not valid.
	 * 
	 * @param response Response.
	 */
	protected void validateResponse(EugenioApiResponseBuilder eugenioRequestBuilder, Response response) {

		EugenioErrorResponse errorResponse = null;

		if (!eugenioRequestBuilder.getExpectedStatusCodes().contains(response.getStatus())) {
			if (response.hasEntity()) {
				errorResponse = response.readEntity(EugenioErrorResponse.class);
			}
			throw new EugenioApiException(eugenioRequestBuilder.getMessageByStatus(response.getStatus()), response, errorResponse);
		}
	}

	/**
	 * Get uri.
	 * @param path
	 * @return
	 */
	protected String getUri(String path) {
		return new StringBuilder(this.uri).append(path).toString();
	}

}
