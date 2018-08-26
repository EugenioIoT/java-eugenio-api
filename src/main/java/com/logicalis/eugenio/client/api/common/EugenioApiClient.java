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
