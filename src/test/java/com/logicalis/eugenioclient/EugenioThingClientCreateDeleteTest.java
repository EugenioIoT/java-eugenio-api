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

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.api.thing.EugenioThingApiClient;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioCreateThingDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioCreateThingRespDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioTagsDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioThingClientCreateDeleteTest extends EugenioClientTestBase {

	private EugenioThingApiClient eugenioThingApiClient;
	private EugenioCreateThingRespDTO thingCreated;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);
		eugenioThingApiClient = eugenioClient.getThingClient();
	}

	@Test
	public void testCreateThingSuccess() {

		LocalDateTime now = LocalDateTime.now();

		EugenioCreateThingDTO thing = new EugenioCreateThingDTO();
		thing.setDeviceId(new StringBuilder("astvjuit").append(now.getHour()).append(now.getMinute()).toString());
		thing.setThumbprint("0123456789012345678901234567890123456789");
		thing.setSchemaName("vcvaleriotest");
		thing.setTags(new EugenioTagsDTO());
		thing.getTags().setDeviceName("AstVJunt");
		thing.getTags().setDeviceDescription("Device test");
		thingCreated = eugenioThingApiClient.createThing(thing, eugenioClient.getHeaders());
		System.out.println(thingCreated);
		Assert.assertNotNull(thingCreated);
		deleteThingSuccess();
	}

	private void deleteThingSuccess() {
		eugenioThingApiClient.deleteThing(thingCreated.getDeviceId(), eugenioClient.getHeaders());
	}

}
