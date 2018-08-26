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

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.exception.EugenioApiException;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioAuthClientTest extends EugenioClientTestBase {

	@Test
	public void testAuthClientLoginSuccess() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);

		Assert.assertTrue(eugenioClient.getEugenioSession() != null);
	}

	@Test(expected = EugenioApiException.class)
	public void testAuthClientLoginWrongUser() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername("vcosta1");
		eugenioLogin.setPassword("vcosta123");
		eugenioLogin.setTenant("eugenio_demo");
		eugenioLogin.setApiKey("apikey");

		eugenioClient.login(eugenioLogin);
	}

	@Test(expected = EugenioApiException.class)
	public void testAuthClientLoginWrongPass() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername("vcosta");
		eugenioLogin.setPassword("wrongpass");
		eugenioLogin.setTenant("eugenio_demo");
		eugenioLogin.setApiKey("apikey");

		eugenioClient.login(eugenioLogin);
	}

	@Test(expected = EugenioApiException.class)
	public void testAuthClientLoginWrongTenant() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername("vcosta");
		eugenioLogin.setPassword("vcosta123");
		eugenioLogin.setTenant("wrongtenant");
		eugenioLogin.setApiKey("apikey");

		eugenioClient.login(eugenioLogin);
	}

}
