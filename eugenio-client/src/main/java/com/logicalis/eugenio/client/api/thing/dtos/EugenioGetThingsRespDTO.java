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
