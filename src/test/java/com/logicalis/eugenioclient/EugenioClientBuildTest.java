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

import com.logicalis.eugenio.client.statefull.EugenioClientBuilder;
import com.logicalis.eugenio.client.statefull.EugenioStatefullClient;

/**
 * Eugenio client build test.
 */
public class EugenioClientBuildTest {

	private String uri = "test";

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowAnExceptionForNullConfig() {
		EugenioClientBuilder.withUri(null).byDefaultClient().buildStatefull();
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowAnExceptionForNullJerseyClient() {
		EugenioClientBuilder.withUri(this.uri).byClient(null).buildStatefull();
	}

	@Test
	public void shouldCreateEugenioClient() {
		EugenioStatefullClient eugenioClient = EugenioClientBuilder.withUri(uri).byDefaultClient().buildStatefull();
		Assert.assertTrue(eugenioClient != null);
	}

}
