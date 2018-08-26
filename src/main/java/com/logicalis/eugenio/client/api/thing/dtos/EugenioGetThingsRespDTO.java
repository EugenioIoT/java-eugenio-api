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
package com.logicalis.eugenio.client.api.thing.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "deviceId", "tags", "properties" })
public class EugenioGetThingsRespDTO {

	@JsonProperty("deviceId")
	private String deviceId;
	@JsonProperty("tags")
	private EugenioTagsDTO tags;
	@JsonProperty("properties")
	private EugenioPropertiesDTO properties;

	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}

	@JsonProperty("deviceId")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@JsonProperty("tags")
	public EugenioTagsDTO getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(EugenioTagsDTO tags) {
		this.tags = tags;
	}

	@JsonProperty("properties")
	public EugenioPropertiesDTO getProperties() {
		return properties;
	}

	@JsonProperty("properties")
	public void setProperties(EugenioPropertiesDTO properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
