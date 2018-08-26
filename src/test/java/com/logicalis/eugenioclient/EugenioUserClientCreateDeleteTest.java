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
package com.logicalis.eugenioclient;

import java.util.Arrays;

import org.junit.Test;

import com.logicalis.eugenio.client.api.user.EugenioUserApiClient;
import com.logicalis.eugenio.client.api.user.dtos.EugenioUserToCreateDTO;
import com.logicalis.eugenio.client.api.user.dtos.EugenioUserToCreateRespDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioUserClientCreateDeleteTest extends EugenioClientTestBase {

	private EugenioUserToCreateRespDTO userCreated;
	private EugenioUserApiClient eugenioUserApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);
		eugenioUserApiClient = eugenioClient.getUserClient();
	}

	@Test
	public void testCreateDeleteUserSuccess() {
		EugenioUserToCreateDTO user = new EugenioUserToCreateDTO();
		user.setUsername("gcvalerio");
		user.setEmail("gcvalerio@artit.com.br");
		user.setFirstName("Fabio");
		user.setLastName("Valerio");
		user.setPassword("gcvalerio123");
		user.setRole("USER");
		user.setOrganizations(Arrays.asList(EugenioTestProperty.getProperty("eugenio.tenant")));

		userCreated = eugenioUserApiClient.createUser(user, eugenioClient.getHeaders());

		deleteUser();
	}

	private void deleteUser() {
		try {
			eugenioUserApiClient.deleteUser(userCreated.getId(), eugenioClient.getHeaders());
		} catch (Throwable ex) {
			System.out.println("Error when try to delete the created user");
			throw ex;
		}
	}

}
