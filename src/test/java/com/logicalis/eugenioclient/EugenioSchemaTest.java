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

import org.junit.Test;

import com.logicalis.eugenio.client.api.schema.EugenioSchemaApiClient;
import com.logicalis.eugenio.client.api.schema.dtos.EugenioSchemaDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioSchemaTest extends EugenioClientTestBase {

	EugenioSchemaApiClient eugenioSchemaApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);
		eugenioSchemaApiClient = eugenioClient.getSchemaClient();
	}

//	@Test
//	public void testCreateSchema() throws Exception {
//		EugenioSchemaDTO createSchemaDTO = new EugenioSchemaDTO();
//		createSchemaDTO.setName("vcvaleriotestdue");
//		createSchemaDTO.setFields(new HashMap<>());
//		createSchemaDTO.getFields().put("temperature", "double");
//		createSchemaDTO.getFields().put("humidity", "double");
//		eugenioClient.getSchemaClient().createSchema(createSchemaDTO);
//	}

	@Test
	public void testGetAllSchemas() throws Exception {
		List<EugenioSchemaDTO> list = eugenioSchemaApiClient.getAllSchemas(eugenioClient.getHeaders());
		System.out.println(list);
	}

	@Test
	public void testGetSchemasLimitOffset() throws Exception {
		List<EugenioSchemaDTO> list = eugenioSchemaApiClient.getSchemas(1, 1, eugenioClient.getHeaders());
		System.out.println(list);
	}

	@Test
	public void testGetSchemaByName() throws Exception {
		EugenioSchemaDTO schemaDTO = eugenioSchemaApiClient.getSchema("vcvaleriotest", eugenioClient.getHeaders());
		System.out.println(schemaDTO);
	}

}
