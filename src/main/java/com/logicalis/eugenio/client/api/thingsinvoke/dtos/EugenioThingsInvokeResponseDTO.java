package com.logicalis.eugenio.client.api.thingsinvoke.dtos;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EugenioThingsInvokeResponseDTO {

	@JsonProperty(value = "status")
	private int status;

	@JsonProperty(value = "payload")
	private Map<String, Object> payload;

	public EugenioThingsInvokeResponseDTO() {
		super();
	}

	public EugenioThingsInvokeResponseDTO(int status, Map<String, Object> payload) {
		super();
		this.status = status;
		this.payload = payload;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
