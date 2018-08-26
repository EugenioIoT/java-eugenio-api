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

import java.time.LocalDateTime;

import com.logicalis.eugenio.client.api.auth.dtos.EugenioLoginResponseDTO;

/**
 * Info class of eugenio login.
 */
public class EugenioSession {

	private EugenioLoginResponseDTO loggedInfo;
	private LocalDateTime loggedAt;

	/**
	 * Set info of login.
	 * 
	 * @param loggedInfo.
	 */
	public void login(EugenioLoginResponseDTO loggedInfo) {
		this.loggedInfo = loggedInfo;
		this.loggedAt = LocalDateTime.now();
	}

	/**
	 * Verify if the user is logged.
	 * 
	 * @return logged.
	 */
	public Boolean isLogged() {
		return this.loggedInfo != null && this.loggedAt != null;
	}

	/**
	 * Verify if the logged token's expired.
	 * 
	 * @return expired.
	 */
	public Boolean isExpired() {
		return !isLogged() || LocalDateTime.now().minusMinutes(loggedInfo.getExpiresIn()).isAfter(this.loggedAt);
	}

	/**
	 * Get logged user.
	 * @return {@link EugenioLoginResponseDTO}.
	 */
	public EugenioLoginResponseDTO getLoggedInfo() {
		return loggedInfo;
	}

}
