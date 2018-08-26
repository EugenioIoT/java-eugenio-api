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
package com.logicalis.eugenio.client.api.ingestion;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.logicalis.eugenio.client.api.common.EugenioApiClient;
import com.logicalis.eugenio.client.api.common.EugenioHeaders;
import com.logicalis.eugenio.client.api.ingestion.dtos.EugenioIngestionDTO;
import com.logicalis.eugenio.client.exception.EugenioApiException;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;
import com.logicalis.eugenio.client.util.EugenioAssert;
import com.logicalis.eugenio.client.util.EugenioConstants;
import com.logicalis.eugenio.client.util.EugenioPathConstants;

/**
 * Main class to access Eugenio Ingestion Api.
 */
public class EugenioIngestionApiClient extends EugenioApiClient {

	/**
	 * Constructor using necessary fields.
	 * 
	 * @param uri.
	 * @param client              {@link Client}.
	 */
	public EugenioIngestionApiClient(String uri, Client client) {
		super(uri, client);
	}

	/**
	 * Post ingestion.
	 * @param listIngestionDTO {@link EugenioIngestionDTO}.
	 * @param headers {@link EugenioHeaders}.
	 * @throws EugenioApiException ex.
	 */
	public void postIngestion(String schema, List<EugenioIngestionDTO> listIngestionDTO, EugenioHeaders headers) throws EugenioApiException {

		EugenioAssert.notBlank(headers.getToken());
		EugenioAssert.notBlank(schema);
		EugenioAssert.notBlank(headers.getTenant());

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder
				.expectStatusCode(Status.ACCEPTED.getStatusCode()).orExpect(Status.CREATED.getStatusCode()).messages()
				.when(Status.BAD_REQUEST.getStatusCode())
				.message("Bad request. Missing required field, or field has invalid value.").next().build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_INGESTION)).request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.header(EugenioConstants.HEADER_SCHEMA, schema)
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.post(Entity.entity(listIngestionDTO, MediaType.APPLICATION_JSON));

		this.validateResponse(requestBuilder, response);
	}

}
