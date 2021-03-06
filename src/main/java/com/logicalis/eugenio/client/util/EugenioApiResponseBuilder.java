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
package com.logicalis.eugenio.client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Eugenio api request builder.
 */
public class EugenioApiResponseBuilder {

	private Map<Integer, String> statusCodeMessages = new HashMap<>();
	private List<Integer> expectedStatusCodes = new ArrayList<>();

	private EugenioApiResponseBuilder() {
	}

	/**
	 * Add expected status code.
	 * @param statusCode.
	 * @return {@link EugenioApiRespStatusExpectedCode}.
	 */
	public static EugenioApiRespStatusExpectedCode expectStatusCode(Integer statusCode) {
		EugenioAssert.notNull(statusCode);
		EugenioApiResponseBuilder apiResponseBuilder = new EugenioApiResponseBuilder();
		apiResponseBuilder.getExpectedStatusCodes().add(statusCode);
		return new EugenioApiRespStatusExpectedCode(apiResponseBuilder);
	}

	/**
	 * Builder class expected code.
	 */
	public static class EugenioApiRespStatusExpectedCode {

		private EugenioApiResponseBuilder apiResponseBuilder;

		private EugenioApiRespStatusExpectedCode(EugenioApiResponseBuilder apiResponseBuilder) {
			this.apiResponseBuilder = apiResponseBuilder;
		}

		/**
		 * Add expected status code.
		 * @param code.
		 * @return {@link EugenioApiRespStatusExpectedCode}.
		 */
		public EugenioApiRespStatusExpectedCode orExpect(Integer code) {
			this.apiResponseBuilder.getExpectedStatusCodes().add(code);
			return this;
		}

		/**
		 * Set specific messages by code.
		 * @return {@link EugenioApiRespStatusCode}.
		 */
		public EugenioApiRespStatusCode messages() {
			return new EugenioApiRespStatusCode(this.apiResponseBuilder);
		}

		/**
		 * Build.
		 * @return {@link EugenioApiResponseBuilder}.
		 */
		public EugenioApiResponseBuilder build() {
			return this.apiResponseBuilder;
		}

	}

	/**
	 * Builder status code.
	 */
	public static class EugenioApiRespStatusCode {

		private EugenioApiResponseBuilder apiResponseBuilder;

		private EugenioApiRespStatusCode(EugenioApiResponseBuilder apiResponseBuilder) {
			this.apiResponseBuilder = apiResponseBuilder;
		}

		/**
		 * Set the code.
		 * @param code.
		 * @return {@link EugenioApiRespMessage}.
		 */
		public EugenioApiRespMessage when(Integer code) {
			return new EugenioApiRespMessage(this.apiResponseBuilder, code);
		}

		/**
		 * Go to builder.
		 * @return {@link Builder}.
		 */
		public Builder next() {
			return new Builder(apiResponseBuilder);
		}
	}

	/**
	 * Builder response message.
	 */
	public static class EugenioApiRespMessage {

		private EugenioApiResponseBuilder apiResponseBuilder;
		private Integer code;

		private EugenioApiRespMessage(EugenioApiResponseBuilder apiResponseBuilder, Integer code) {
			this.apiResponseBuilder = apiResponseBuilder;
			this.code = code;
		}

		/**
		 * Set the message.
		 * @param message.
		 * @return {@link EugenioApiRespStatusCode}.
		 */
		public EugenioApiRespStatusCode message(String message) {
			this.apiResponseBuilder.getStatusCodeMessages().put(this.code, message);
			return new EugenioApiRespStatusCode(this.apiResponseBuilder);
		}

		/**
		 * Go to builder.
		 * @return {@link Builder}.
		 */
		public Builder next() {
			return new Builder(apiResponseBuilder);
		}
	}

	/**
	 * Builder class.
	 */
	public static class Builder {

		private EugenioApiResponseBuilder apiResponseBuilder;

		private Builder(EugenioApiResponseBuilder apiResponseBuilder) {
			this.apiResponseBuilder = apiResponseBuilder;
		}

		/**
		 * Build.
		 * @return {@link EugenioApiResponseBuilder}.
		 */
		public EugenioApiResponseBuilder build() {
			return this.apiResponseBuilder;
		}
	}

	/**
	 * Get message by status code.
	 * @param status.
	 * @return message.
	 */
	public String getMessageByStatus(Integer status) {
		if (this.statusCodeMessages.containsKey(status)) {
			return this.statusCodeMessages.get(status);
		} else {
			return null;
		}
	}

	/**
	 * Get status code messages.
	 * @return status code messages.
	 */
	public Map<Integer, String> getStatusCodeMessages() {
		return statusCodeMessages;
	}

	/**
	 * Get expected codes.
	 * @return codes.
	 */
	public List<Integer> getExpectedStatusCodes() {
		return expectedStatusCodes;
	}

}
