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
package com.logicalis.eugenio.client.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.logicalis.eugenio.client.api.common.dtos.EugenioErrorResponse;

/**
 * Exception raised when eugenio api returned any error.
 */
public class EugenioApiException extends WebApplicationException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 1L;
	private EugenioErrorResponse errorMessage;

	/**
	 * Error message constructor.
	 * @param message.
	 * @param response.
	 * @param errorResponse.
	 */
	public EugenioApiException(String message, Response response, EugenioErrorResponse errorMessage) {
		super(message, response);
		this.errorMessage = errorMessage;
	}

	/**
	 * Constructor.
	 * @param response with status code error.
	 */
	public EugenioApiException(Response response) {
		super(response);
	}

	/**
	 * Constructor.
	 * @param message description.
	 * @param response with status code error.
	 */
	public EugenioApiException(String message, Response response) {
		super(message, response);
	}

	/**
	 * Get message error returned from Eugenio.
	 * @return {@link EugenioErrorResponse}.
	 */
	public EugenioErrorResponse getErrorMessage() {
		return this.errorMessage;
	}

}
