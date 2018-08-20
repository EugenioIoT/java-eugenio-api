package com.logicalis.eugenio.client.api.thing.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "deviceId", "thumbprint", "schemaName", "tags" })
public class EugenioCreateThingDTO {

	@JsonProperty("deviceId")
	private String deviceId;
	@JsonProperty("thumbprint")
	private String thumbprint;
	@JsonProperty("schemaName")
	private String schemaName;
	@JsonProperty("tags")
	private EugenioTagsDTO tags;

	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}

	@JsonProperty("deviceId")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@JsonProperty("thumbprint")
	public String getThumbprint() {
		return thumbprint;
	}

	@JsonProperty("thumbprint")
	public void setThumbprint(String thumbprint) {
		this.thumbprint = thumbprint;
	}

	@JsonProperty("schemaName")
	public String getSchemaName() {
		return schemaName;
	}

	@JsonProperty("schemaName")
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@JsonProperty("tags")
	public EugenioTagsDTO getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(EugenioTagsDTO tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

