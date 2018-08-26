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

import com.logicalis.eugenio.client.api.thing.EugenioThingApiClient;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioGetThingsRespDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioPutThingDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioTagsDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioThingClientPutTest extends EugenioClientTestBase {

	private EugenioThingApiClient eugenioThingApiClient;

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
	public void testPutThingSuccess() {
		EugenioPutThingDTO thing = new EugenioPutThingDTO();
		thing.setSchemaName("vcvaleriotest");
		thing.setTags(new EugenioTagsDTO());
		thing.getTags().setDeviceName("Device UTest alt 11");
		thing.getTags().setDeviceDescription("Device test alt 11");
		eugenioThingApiClient.putThing("deviceutest1", thing, eugenioClient.getHeaders());

		EugenioGetThingsRespDTO thingFound = eugenioThingApiClient.getThing("deviceutest1", eugenioClient.getHeaders());
		Assert.assertEquals(thing.getTags().getDeviceName(), thingFound.getTags().getDeviceName());
	}

}
