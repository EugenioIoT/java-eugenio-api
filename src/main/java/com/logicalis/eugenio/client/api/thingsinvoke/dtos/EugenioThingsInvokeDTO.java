package com.logicalis.eugenio.client.api.thingsinvoke.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EugenioThingsInvokeDTO {

	@JsonProperty(value = "method")
	private String method;

	@JsonProperty(value = "payload")
	private JsonNode payload;

	@JsonProperty(value = "timeout")
	private int timeout;

	public EugenioThingsInvokeDTO() {
	}

	public EugenioThingsInvokeDTO(String method, JsonNode payload, int timeout) {
		super();
		this.method = method;
		this.payload = payload;
		this.timeout = timeout;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public JsonNode getPayload() {
		return payload;
	}

	public void setPayload(JsonNode payload) {
		this.payload = payload;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
