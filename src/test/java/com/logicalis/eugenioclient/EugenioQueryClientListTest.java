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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.api.query.DataQueryRequest;
import com.logicalis.eugenio.client.api.query.EugenioQueryApiClient;
import com.logicalis.eugenio.client.api.query.dtos.EugenioQueryResponseDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioQueryClientListTest extends EugenioClientTestBase {

	private EugenioQueryApiClient eugenioQueryApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);
		eugenioQueryApiClient = eugenioClient.getQueryClient();
	}

	@Test
	public void testListAllUsers() {
		DataQueryRequest dataQueryRequest = new DataQueryRequest();
		dataQueryRequest.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));
		dataQueryRequest.setQuery("SELECT a.* FROM eugenio_demo_sandbox.v_vcvaleriotestum a");
		List<EugenioQueryResponseDTO> result = eugenioQueryApiClient.get(EugenioQueryResponseDTO.class,
				dataQueryRequest);

		System.out.println(result);

		Assert.assertTrue(result.size() > 0);
	}

}
