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
package com.logicalis.eugenio.client.api.user;

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
import com.logicalis.eugenio.client.api.user.dtos.EugenioGetUserRespDTO;
import com.logicalis.eugenio.client.api.user.dtos.EugenioGroupsDTO;
import com.logicalis.eugenio.client.api.user.dtos.EugenioUserToCreateDTO;
import com.logicalis.eugenio.client.api.user.dtos.EugenioUserToCreateRespDTO;
import com.logicalis.eugenio.client.config.EugenioClientConfig;
import com.logicalis.eugenio.client.util.EugenioApiResponseBuilder;
import com.logicalis.eugenio.client.util.EugenioConstants;
import com.logicalis.eugenio.client.util.EugenioPathConstants;

/**
 * Main class to access Eugenio User Api.
 */
public class EugenioUserApiClient extends EugenioApiClient {

	/**
	 * Constructor using necessary fields.
	 * @param eugenioClientConfig {@link EugenioClientConfig}.
	 * @param client {@link Client}.
	 * @param loggedUser {@link EugenioLoginResponseDTO}.
	 */
	public EugenioUserApiClient(String uri, Client client) {
		super(uri, client);
	}

	/**
	 * Get All users.
	 * @return {@link List<EugenioGetUserRespDTO>}.
	 */
	public List<EugenioGetUserRespDTO> getAllUsers(EugenioHeaders headers) {
		return getUsers(null, null, headers);
	}

	/**
	 * Get users with pagination.
	 * @param limit.
	 * @param offset.
	 * @return {@link List<EugenioGetUserRespDTO>}.
	 */
	public List<EugenioGetUserRespDTO> getUsers(Integer limit, Integer offset, EugenioHeaders headers) {

		List<EugenioGetUserRespDTO> listResult = null;

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.OK.getStatusCode()).build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_USERS))
				.queryParam("limit", limit)
				.queryParam("offset", offset)
				.request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.get();

		this.validateResponse(requestBuilder, response);

		listResult = response.readEntity(new GenericType<List<EugenioGetUserRespDTO>>() {});
		return listResult;
	}

	public EugenioUserToCreateRespDTO createUser(EugenioUserToCreateDTO userDTO, EugenioHeaders headers) {

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.CREATED.getStatusCode())
				.messages()
				.when(Status.CONFLICT.getStatusCode()).message("The 'Username' or 'email' already exist.")
				.when(Status.NOT_FOUND.getStatusCode()).message("Organization not found.")
				.next()
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_USERS))
				.request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.post(Entity.entity(userDTO, MediaType.APPLICATION_JSON));

		this.validateResponse(requestBuilder, response);

		EugenioUserToCreateRespDTO ret = response.readEntity(EugenioUserToCreateRespDTO.class);

		return ret;
	}

	/**
	 * Delete user by id.
	 * @param idUser.
	 */
	public void deleteUser(String idUser, EugenioHeaders headers) {

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.NO_CONTENT.getStatusCode())
				.messages()
				.when(Status.NOT_FOUND.getStatusCode()).message("User not found.")
				.next()
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_USERS_DELETE))
				.resolveTemplate("userId", idUser)
				.request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.delete();

		this.validateResponse(requestBuilder, response);
	}

	/**
	 * Set Associate user to tenant of logged user.
	 * @param eugenioGroupsDTO {@link EugenioGroupsDTO}.
	 */
	public void postGroups(EugenioGroupsDTO eugenioGroupsDTO, EugenioHeaders headers) {

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.NO_CONTENT.getStatusCode())
				.messages()
				.when(Status.NOT_FOUND.getStatusCode()).message("User not found.")
				.next()
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_GROUPS))
				.request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.delete();

		this.validateResponse(requestBuilder, response);
	}

	/**
	 * Edit associated users of logged user's tenant.
	 * @param eugenioGroupsDTO {@link EugenioGroupsDTO}.
	 */
	public void putGroups(EugenioGroupsDTO eugenioGroupsDTO, EugenioHeaders headers) {

		EugenioApiResponseBuilder requestBuilder = EugenioApiResponseBuilder.expectStatusCode(Status.NO_CONTENT.getStatusCode())
				.messages()
				.when(Status.NOT_FOUND.getStatusCode()).message("User not found.")
				.next()
				.build();

		Response response = client.target(this.getUri(EugenioPathConstants.PATH_GROUPS))
				.request()
				.header(EugenioConstants.HEADER_AUTHORIZATION, headers.getToken())
				.header(EugenioConstants.HEADER_X_TENANT, headers.getTenant())
				.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.put(Entity.entity(eugenioGroupsDTO, MediaType.APPLICATION_JSON));

		this.validateResponse(requestBuilder, response);	}

}