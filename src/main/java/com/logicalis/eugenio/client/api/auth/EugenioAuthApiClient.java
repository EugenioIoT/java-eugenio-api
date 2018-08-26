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
package com.logicalis.eugenio.client.api.auth;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.logicalis.eugenio.client.api.auth.dtos.EugenioLoginDTO;
import com.logicalis.eugenio.client.api.auth.dtos.EugenioLoginResponseDTO;
import com.logicalis.eugenio.client.api.common.EugenioApiClient;
import com.logicalis.eugenio.client.config.EugenioClientConfig;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;
import com.logicalis.eugenio.client.util.EugenioPathConstants;

/**
 * Main class to access Eugenio Auth Api.
 */
public class EugenioAuthApiClient extends EugenioApiClient {

	/**
	 * Constructor using necessary fields.
	 * 
	 * @param eugenioClientConfig {@link EugenioClientConfig}.
	 * @param client              {@link Client}.
	 */
	public EugenioAuthApiClient(String uri, Client client) {
		super(uri, client);
	}

	/**
	 * Login eugenio application.
	 * 
	 * @param eugenioLogin {@link EugenioLoginDTO}.
	 * @return {@link EugenioLoginResponseDTO}.
	 */
	public EugenioLoginResponseDTO login(EugenioLoginDTO eugenioLogin) {

		EugenioApiResponseBuilder responseBuilder = EugenioApiResponseBuilder
				.expectStatusCode(Status.OK.getStatusCode()).build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_AUTH)).request()
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.post(Entity.entity(eugenioLogin, MediaType.APPLICATION_JSON));

		this.validateResponse(responseBuilder, response);

		EugenioLoginResponseDTO ret = response.readEntity(EugenioLoginResponseDTO.class);

		return ret;
	}

}
