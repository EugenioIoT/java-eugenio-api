/*
 * Copyright (c) 2018 Logicalis S/A.
 * 
 * This file is part of java-eugenio-api
 * (see https://github.com/EugenioIoT).
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.logicalis.eugenio.client.api.thing;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.logicalis.eugenio.client.api.auth.dtos.EugenioLoginResponseDTO;
import com.logicalis.eugenio.client.api.common.EugenioApiClient;
import com.logicalis.eugenio.client.api.common.EugenioHeaders;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioCreateThingDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioCreateThingRespDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioGetThingsRespDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioPutThingDTO;
import com.logicalis.eugenio.client.config.EugenioClientConfig;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;
import com.logicalis.eugenio.client.util.EugenioConstants;
import com.logicalis.eugenio.client.util.EugenioPathConstants;

/**
 * Main class to access Eugenio User Api.
 */
public class EugenioThingApiClient extends EugenioApiClient {

	/**
	 * Constructor using necessary fields.
	 * 
	 * @param eugenioClientConfig {@link EugenioClientConfig}.
	 * @param client              {@link Client}.
	 * @param loggedUser          {@link EugenioLoginResponseDTO}.
	 */
	public EugenioThingApiClient(String uri, Client client) {
		super(uri, client);
	}

	/**
	 * Get all things.
	 * 
	 * @return things.
	 */
	public List<EugenioGetThingsRespDTO> getAllThings(EugenioHeaders headers) {
		return getThings(null, null, headers);
	}

	/**
	 * Get things paginated.
	 * 
	 * @param limit.
	 * @param offset.
	 * @return list {@link EugenioGetThingsRespDTO}.
	 */
	public List<EugenioGetThingsRespDTO> getThings(Integer limit, Integer offset, EugenioHeaders headers) {

		List<EugenioGetThingsRespDTO> listResult = null;

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.OK.getStatusCode())
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_THINGS)).queryParam("limit", limit)
				.queryParam("offset", offset).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).get();

		validateResponse(requestBuilder, response);

		listResult = response.readEntity(new GenericType<List<EugenioGetThingsRespDTO>>() {
		});
		return listResult;
	}

	/**
	 * Get one thing.
	 * 
	 * @param id thing.
	 * @return list {@link EugenioGetThingsRespDTO}.
	 */
	public EugenioGetThingsRespDTO getThing(String idThing, EugenioHeaders headers) {

		EugenioGetThingsRespDTO thing = null;

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.OK.getStatusCode())
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_THINGS_THINGID))
				.resolveTemplate("deviceId", idThing).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).get();

		validateResponse(requestBuilder, response);

		thing = response.readEntity(EugenioGetThingsRespDTO.class);
		return thing;
	}

	/**
	 * Delete thing.
	 * 
	 * @param id thing.
	 */
	public void deleteThing(String idThing, EugenioHeaders headers) {

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder
				.expectStatusCode(Status.NO_CONTENT.getStatusCode()).build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_THINGS_THINGID))
				.resolveTemplate("deviceId", idThing).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).delete();

		this.validateResponse(requestBuilder, response);

	}

	/**
	 * Create thing.
	 * 
	 * @param thingDTO {@link EugenioCreateThingDTO}.
	 * @return {@link EugenioCreateThingRespDTO}.
	 */
	public EugenioCreateThingRespDTO createThing(EugenioCreateThingDTO thingDTO, EugenioHeaders headers) {

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder
				.expectStatusCode(Status.CREATED.getStatusCode()).messages().when(Status.CONFLICT.getStatusCode())
				.message("An attempt to create a device that already exists.").when(Status.NOT_FOUND.getStatusCode())
				.message("Could not find 'deviceId' or 'schemaName' in current organization.").next().build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_THINGS)).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.post(Entity.entity(thingDTO, MediaType.APPLICATION_JSON));

		this.validateResponse(requestBuilder, response);

		EugenioCreateThingRespDTO resultDTO = response.readEntity(EugenioCreateThingRespDTO.class);

		return resultDTO;
	}

	/**
	 * Put thing.
	 * 
	 * @param          idThing.
	 * @param thingDTO {@link EugenioPutThingDTO}.
	 * @return {@link EugenioCreateThingRespDTO}.
	 */
	public void putThing(String idThing, EugenioPutThingDTO thingDTO, EugenioHeaders headers) {

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder
				.expectStatusCode(Status.NO_CONTENT.getStatusCode()).messages().when(Status.CONFLICT.getStatusCode())
				.message("An attempt to create a device that already exists.").when(Status.NOT_FOUND.getStatusCode())
				.message("Could not find 'deviceId' or 'schemaName' in current organization.").next().build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_THINGS_THINGID))
				.resolveTemplate("deviceId", idThing).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.put(Entity.entity(thingDTO, MediaType.APPLICATION_JSON));

		this.validateResponse(requestBuilder, response);

	}

}