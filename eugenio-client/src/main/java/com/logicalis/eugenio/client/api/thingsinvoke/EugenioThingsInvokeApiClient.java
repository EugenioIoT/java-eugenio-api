package com.logicalis.eugenio.client.api.thingsinvoke;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.logicalis.eugenio.client.api.common.EugenioApiClient;
import com.logicalis.eugenio.client.api.common.EugenioHeaders;
import com.logicalis.eugenio.client.api.thingsinvoke.dtos.EugenioThingsInvokeDTO;
import com.logicalis.eugenio.client.exception.EugenioApiException;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;
import com.logicalis.eugenio.client.util.EugenioAssert;
import com.logicalis.eugenio.client.util.EugenioConstants;
import com.logicalis.eugenio.client.util.EugenioPathConstants;

/**
 * Main class to access Eugenio things invoke Api.
 */
public class EugenioThingsInvokeApiClient extends EugenioApiClient {

	/**
	 * Constructor using necessary fields.
	 * 
	 * @param        uri.
	 * @param client {@link Client}.
	 */
	public EugenioThingsInvokeApiClient(String uri, Client client) {
		super(uri, client);
	}

	/**
	 * Post things invoke.
	 * 
	 * @param listIngestionDTO {@link EugenioThingsInvokeDTO}.
	 * @param headers          {@link EugenioHeaders}.
	 * @throws EugenioApiException ex.
	 */
	public void postInvoke(String deviceId, EugenioThingsInvokeDTO eugenioThingsInvokeDTO, EugenioHeaders headers)
			throws EugenioApiException {

		EugenioAssert.notBlank(headers.getToken());
		EugenioAssert.notBlank(headers.getApiKey());
		EugenioAssert.notBlank(headers.getTenant());

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.OK.getStatusCode())
				.messages().when(Status.BAD_REQUEST.getStatusCode())
				.message("Bad request. Missing required field, or field has invalid value.").next().build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_THINGS_INVOKE))
				.resolveTemplate("deviceId", deviceId).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.header(EugenioConstants.APIKEY, headers.getApiKey())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.post(Entity.entity(eugenioThingsInvokeDTO, MediaType.APPLICATION_JSON));

		this.validateResponse(requestBuilder, response);
	}

}
