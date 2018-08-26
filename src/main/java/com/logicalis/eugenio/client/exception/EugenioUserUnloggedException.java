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

/**
 * Exception raised when a method which requires the user to be logged is call before the login.
 */
public class EugenioUserUnloggedException extends RuntimeException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constant user has to be logged.
	 */
	private static final String USER_HAS_TO_BE_LOGGED = "User has to be logged to access this point.";

	/**
	 * Constructor.
	 */
	public EugenioUserUnloggedException() {
		super(USER_HAS_TO_BE_LOGGED);
	}

}
