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
package com.logicalis.eugenio.client.statefull;

import java.io.Serializable;

import javax.ws.rs.client.Client;

import com.logicalis.eugenio.client.api.auth.EugenioAuthApiClient;
import com.logicalis.eugenio.client.api.common.EugenioHeaders;
import com.logicalis.eugenio.client.api.ingestion.EugenioIngestionApiClient;
import com.logicalis.eugenio.client.api.query.EugenioQueryApiClient;
import com.logicalis.eugenio.client.api.schema.EugenioSchemaApiClient;
import com.logicalis.eugenio.client.api.thing.EugenioThingApiClient;
import com.logicalis.eugenio.client.api.user.EugenioUserApiClient;
import com.logicalis.eugenio.client.config.EugenioClientConfig;
import com.logicalis.eugenio.client.exception.EugenioApiException;
import com.logicalis.eugenio.client.exception.EugenioUserUnloggedException;
import com.logicalis.eugenio.client.util.EugenioAssert;
import com.logicalis.eugenio.client.util.EugenioSession;
import com.logicalis.eugenio.client.util.LoginDtoToEugenioLoginDto;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;

/**
 * Main class to access Eugenio Api.
 */
public class EugenioStatefullClient implements Serializable {

	private static final long serialVersionUID = 1L;

	private InfoLoginDTO infoLogin;
	private EugenioSession eugenioSession;
	String uri;
	Client client;
	private EugenioClientConfig eugenioClientConfig;
	private EugenioAuthApiClient eugenioAuthClient;
	private EugenioUserApiClient eugenioUserClient;
	private EugenioSchemaApiClient eugenioSchemaClient;
	private EugenioThingApiClient eugenioThingClient;
	private EugenioIngestionApiClient eugenioIngestionClient;
	private EugenioQueryApiClient eugenioQueryClient;

	/**
	 * Default constructor forces to use the builder.
	 * 
	 * @see Builder.
	 */
	public EugenioStatefullClient() {
	}

	public void login(InfoLoginDTO loginDTO) throws EugenioApiException {
		EugenioAssert.notNull(loginDTO);
		EugenioAssert.notBlank(loginDTO.getUsername());
		EugenioAssert.notBlank(loginDTO.getPassword());
		EugenioAssert.notBlank(loginDTO.getTenant());
		EugenioAssert.notBlank(loginDTO.getApiKey());
		this.infoLogin = loginDTO;

		this.eugenioClientConfig = EugenioClientConfig.withApiKey(loginDTO.getApiKey()).ofTenant(loginDTO.getTenant())
				.build();

		this.eugenioSession = new EugenioSession();
		this.eugenioSession.login(this.getAuthClient().login(LoginDtoToEugenioLoginDto.convert(this.infoLogin)));
	}

	public EugenioSession getEugenioSession() {
		return eugenioSession;
	}

	public EugenioAuthApiClient getAuthClient() {
		EugenioAssert.notNull(this.eugenioClientConfig);
		if (eugenioAuthClient == null) {
			this.eugenioAuthClient = new EugenioAuthApiClient(uri, client);
		}
		return this.eugenioAuthClient;
	}

	public EugenioUserApiClient getUserClient() throws EugenioUserUnloggedException {
		needsLoggedUser();
		if (this.eugenioUserClient == null) {
			this.eugenioUserClient = new EugenioUserApiClient(uri, client);
		}
		return this.eugenioUserClient;
	}

	public EugenioSchemaApiClient getSchemaClient() throws EugenioUserUnloggedException {
		needsLoggedUser();
		if (this.eugenioSchemaClient == null) {
			this.eugenioSchemaClient = new EugenioSchemaApiClient(uri, client);
		}
		return this.eugenioSchemaClient;
	}

	public EugenioIngestionApiClient getIngestionClient() throws EugenioUserUnloggedException {
		needsLoggedUser();
		if (this.eugenioIngestionClient == null) {
			this.eugenioIngestionClient = new EugenioIngestionApiClient(uri, client);
		}
		return this.eugenioIngestionClient;
	}

	public EugenioThingApiClient getThingClient() throws EugenioUserUnloggedException {
		needsLoggedUser();
		if (this.eugenioThingClient == null) {
			this.eugenioThingClient = new EugenioThingApiClient(uri, client);
		}

		return this.eugenioThingClient;
	}

	public EugenioQueryApiClient getQueryClient() throws EugenioUserUnloggedException {
		needsLoggedUser();
		if (this.eugenioQueryClient == null) {
			this.eugenioQueryClient = new EugenioQueryApiClient(uri, client);
		}
		return this.eugenioQueryClient;
	}

	/**
	 * Verify if the client needs login.
	 * 
	 * @throws EugenioUserUnloggedException ex.
	 */
	private void needsLoggedUser() throws EugenioUserUnloggedException {
		if (!eugenioSession.isLogged()) {
			throw new EugenioUserUnloggedException();
		} else if (eugenioSession.isExpired()) {
			this.login(this.infoLogin);
		}
	}

	public EugenioHeaders getHeaders() {
		needsLoggedUser();
		EugenioHeaders eugenioHeaders = new EugenioHeaders();
		eugenioHeaders.setTenant(this.infoLogin.getTenant());
		eugenioHeaders.setToken(this.eugenioSession.getLoggedInfo().getAccessToken());
		return eugenioHeaders;
	}

}
