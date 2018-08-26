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
@JsonPropertyOrder({ "deviceName", "deviceDescription", "tenantId", "schemaId", "type", "created", "thumbprint" })
public class EugenioTagsDTO {

	@JsonProperty("deviceName")
	private String deviceName;
	@JsonProperty("deviceDescription")
	private String deviceDescription;
	@JsonProperty("tenantId")
	private String tenantId;
	@JsonProperty("schemaId")
	private String schemaId;
	@JsonProperty("type")
	private String type;
	@JsonProperty("created")
	private String created;
	@JsonProperty("thumbprint")
	private String thumbprint;

	@JsonProperty("deviceName")
	public String getDeviceName() {
		return deviceName;
	}

	@JsonProperty("deviceName")
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@JsonProperty("deviceDescription")
	public String getDeviceDescription() {
		return deviceDescription;
	}

	@JsonProperty("deviceDescription")
	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}

	@JsonProperty("tenantId")
	public String getTenantId() {
		return tenantId;
	}

	@JsonProperty("tenantId")
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@JsonProperty("schemaId")
	public String getSchemaId() {
		return schemaId;
	}

	@JsonProperty("schemaId")
	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("created")
	public String getCreated() {
		return created;
	}

	@JsonProperty("created")
	public void setCreated(String created) {
		this.created = created;
	}

	@JsonProperty("thumbprint")
	public String getThumbprint() {
		return thumbprint;
	}

	@JsonProperty("thumbprint")
	public void setThumbprint(String thumbprint) {
		this.thumbprint = thumbprint;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}