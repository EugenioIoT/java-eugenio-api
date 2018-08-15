package com.logicalis.eugenio.client.api.schema;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.logicalis.eugenio.client.api.common.EugenioApiClient;
import com.logicalis.eugenio.client.api.common.EugenioHeaders;
import com.logicalis.eugenio.client.api.schema.dtos.EugenioSchemaDTO;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;
import com.logicalis.eugenio.client.util.EugenioAssert;
import com.logicalis.eugenio.client.util.EugenioConstants;
import com.logicalis.eugenio.client.util.EugenioPathConstants;

/**
 * Main class to access Eugenio Schema Api.
 */
public class EugenioSchemaApiClient extends EugenioApiClient {

	/**
	 * Constructor using necessary fields.
	 * 
	 * @param uri.
	 * @param client              {@link Client}.
	 */
	public EugenioSchemaApiClient(String uri, Client client) {
		super(uri, client);
	}

	/**
	 * Create schema.
	 * 
	 * @param createSchemaDTO {@link EugenioSchemaDTO}.
	 */
	public void createSchema(EugenioSchemaDTO createSchemaDTO, EugenioHeaders headers) {

		EugenioAssert.notNull(headers);
		EugenioAssert.notBlank(headers.getToken());
		EugenioAssert.notBlank(headers.getTenant());

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.OK.getStatusCode())
				.orExpect(Status.CREATED.getStatusCode()).messages().when(Status.BAD_REQUEST.getStatusCode())
				.message("Bad request. Missing required field, or field has invalid value.")
				.when(Status.CONFLICT.getStatusCode()).message("An attempt to create a schema that already exists.")
				.when(Status.BAD_GATEWAY.getStatusCode()).message("Schema database service is unavailable.").next()
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_SCHEMAS)).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.post(Entity.entity(createSchemaDTO, MediaType.APPLICATION_JSON));

		this.validateResponse(requestBuilder, response);
	}

	/**
	 * Get all schemas.
	 * 
	 * @return {@link List<EugenioSchemaDTO>}.
	 */
	public List<EugenioSchemaDTO> getAllSchemas(EugenioHeaders headers) {
		return getSchemas(null, null, headers);
	}

	/**
	 * Get schemas.
	 * 
	 * @param limit.
	 * @param offset.
	 * @return {@link List<EugenioSchemaDTO>}.
	 */
	public List<EugenioSchemaDTO> getSchemas(Integer limit, Integer offset, EugenioHeaders headers) {

		EugenioAssert.notNull(headers);
		EugenioAssert.notBlank(headers.getToken());
		EugenioAssert.notBlank(headers.getTenant());

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.OK.getStatusCode())
				.messages().when(Status.BAD_GATEWAY.getStatusCode()).message("Schema database service is unavailable.")
				.next().build();

		List<EugenioSchemaDTO> listResult = null;

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_SCHEMAS)).queryParam("limit", limit)
				.queryParam("offset", offset).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).get();

		this.validateResponse(requestBuilder, response);

		listResult = response.readEntity(new GenericType<List<EugenioSchemaDTO>>() {
		});
		return listResult;
	}

	/**
	 * Get schema.
	 * 
	 * @param schema name.
	 * @return {@link EugenioSchemaDTO}.
	 */
	public EugenioSchemaDTO getSchema(String schemaName, EugenioHeaders headers) {

		EugenioAssert.notNull(headers);
		EugenioAssert.notBlank(headers.getToken());
		EugenioAssert.notBlank(headers.getTenant());

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.OK.getStatusCode())
				.messages().when(Status.NOT_FOUND.getStatusCode())
				.message("Could not find schemaName in the current organization.")
				.when(Status.BAD_GATEWAY.getStatusCode()).message("Schema database service is unavailable.").next()
				.build();

		EugenioSchemaDTO schemaResponse = null;

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_SCHEMAS_BY_NAME))
				.resolveTemplate("schemaName", schemaName).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).get();

		validateResponse(requestBuilder, response);

		schemaResponse = response.readEntity(EugenioSchemaDTO.class);
		return schemaResponse;
	}

}
