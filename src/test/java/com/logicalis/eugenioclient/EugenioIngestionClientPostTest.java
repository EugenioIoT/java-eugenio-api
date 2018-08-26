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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.logicalis.eugenio.client.api.ingestion.EugenioIngestionApiClient;
import com.logicalis.eugenio.client.api.ingestion.dtos.EugenioIngestionDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioIngestionClientPostTest extends EugenioClientTestBase {

	private EugenioIngestionApiClient eugenioIngestionApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);

		eugenioIngestionApiClient = eugenioClient.getIngestionClient();
	}

	@Test
	public void testIngestionSuccess() {
		List<EugenioIngestionDTO> list = new ArrayList<>();
		// vcvaleriotest
		list.add(new EugenioIngestionDTO().addField("humidity", 5d).addField("temperature", 30d));
		list.add(new EugenioIngestionDTO().addField("humidity", 3d).addField("temperature", 100d));
		list.add(new EugenioIngestionDTO().addField("humidity", 8d).addField("temperature", 50d));
		eugenioIngestionApiClient.postIngestion("vcvaleriotest", list, eugenioClient.getHeaders());
	}

}
