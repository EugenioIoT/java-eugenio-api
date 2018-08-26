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
package com.logicalis.eugenio.client.config;

import com.logicalis.eugenio.client.util.EugenioAssert;

/**
 * Class to config common parameters to access eugenio api.
 */
public class EugenioClientConfig {

	private String apiKey;
	private String tenant;

	/**
	 * Default constructor.
	 */
	private EugenioClientConfig() {
	}

	/**
	 * @return Eugenio api key.
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @return tenant.
	 */
	public String getTenant() {
		return tenant;
	}

	/**
	 * Set apiKey.
	 * @param apiKey.
	 * @return {@link EugenioConfigApiKey}.
	 */
	public static EugenioConfigTenant withApiKey(String apiKey) {
		EugenioAssert.notBlank(apiKey);
		EugenioClientConfig clientConfig = new EugenioClientConfig();
		clientConfig.apiKey = apiKey;
		return new EugenioConfigTenant(clientConfig);
	}

	/**
	 * Eugenio config tenant.
	 */
	public static class EugenioConfigTenant {

		private EugenioClientConfig eugenioClientConfig;

		private EugenioConfigTenant(EugenioClientConfig eugenioClientConfig) {
			this.eugenioClientConfig = eugenioClientConfig;
		}

		/**
		 * Set tenant.
		 * @param tenant.
		 * @return {@link EugenioConfigBuilder}.
		 */
		public EugenioConfigBuilder ofTenant(String tenant) {
			EugenioAssert.notBlank(tenant);
			this.eugenioClientConfig.tenant = tenant;
			return new EugenioConfigBuilder(this.eugenioClientConfig);
		}
	}

	/**
	 * Eugenio config builder.
	 */
	public static class EugenioConfigBuilder {

		private EugenioClientConfig eugenioClientConfig;

		private EugenioConfigBuilder(EugenioClientConfig eugenioClientConfig) {
			this.eugenioClientConfig = eugenioClientConfig;
		}

		/**
		 * Build.
		 * @return {@link EugenioClientConfig}.
		 */
		public EugenioClientConfig build() {
			return this.eugenioClientConfig;
		}
	}

}
