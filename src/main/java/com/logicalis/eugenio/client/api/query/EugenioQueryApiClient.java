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
package com.logicalis.eugenio.client.api.query;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.logicalis.eugenio.client.api.common.EugenioApiClient;
import com.logicalis.eugenio.client.api.query.dtos.EugenioQueryResponseDTO;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;
import com.logicalis.eugenio.client.util.EugenioAssert;
import com.logicalis.eugenio.client.util.EugenioConstants;
import com.logicalis.eugenio.client.util.EugenioPathConstants;

/**
 * Main class to access Eugenio Query Api.
 */
public class EugenioQueryApiClient extends EugenioApiClient {

	/**
	 * Constructor using necessary fields.
	 * 
	 * @param        uri.
	 * @param client {@link Client}.
	 */
	public EugenioQueryApiClient(String uri, Client client) {
		super(uri, client);
	}

	/**
	 * Query.
	 * 
	 * @param      query.
	 * @param type response.
	 * @return list {@link EugenioQueryResponseDTO}.
	 */
	public <TResponse> List<TResponse> get(Class<TResponse> type, DataQueryRequest dataQueryRequest) {

		EugenioAssert.notNull(dataQueryRequest);
		EugenioAssert.notBlank(dataQueryRequest.getQuery());
		EugenioAssert.notBlank(dataQueryRequest.getApiKey());

		List<TResponse> listResult = null;

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder
				.expectStatusCode(Status.NO_CONTENT.getStatusCode()).orExpect(Status.OK.getStatusCode()).messages()
				.when(Status.BAD_REQUEST.getStatusCode()).message("Bad request. E.g., SQL syntax error.").next()
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_QUERY))
				.queryParam(EugenioConstants.APIKEY, dataQueryRequest.getApiKey())
				.queryParam("sql", dataQueryRequest.getQuery()).request()
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).get();

		this.validateResponse(requestBuilder, response);

		listResult = response.readEntity(new GenericType<List<TResponse>>() {
		});
		return listResult;
	}

}
