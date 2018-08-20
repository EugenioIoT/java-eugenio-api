package com.logicalis.eugenio.client.api.thing.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "$lastUpdated" })
public class EugenioMetadataDTO {

	@JsonProperty("$lastUpdated")
	private String lastUpdated;

	@JsonProperty("$lastUpdated")
	public String getLastUpdated() {
		return lastUpdated;
	}

	@JsonProperty("$lastUpdated")
	public void setLastUpdated(String $lastUpdated) {
		this.lastUpdated = $lastUpdated;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
