package com.logicalis.eugenio.client.api.thing.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "deviceId", "tenantId", "CACertificate", "status", "mqtt", "apiKey" })
public class EugenioCreateThingRespDTO {

	@JsonProperty("deviceId")
	private String deviceId;
	@JsonProperty("tenantId")
	private String tenantId;
	@JsonProperty("CACertificate")
	private String cACertificate;
	@JsonProperty("status")
	private String status;
	@JsonProperty("mqtt")
	private EugenioMqttDTO mqtt;
	@JsonProperty("apiKey")
	private String apiKey;

	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}

	@JsonProperty("deviceId")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@JsonProperty("tenantId")
	public String getTenantId() {
		return tenantId;
	}

	@JsonProperty("tenantId")
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@JsonProperty("CACertificate")
	public String getCACertificate() {
		return cACertificate;
	}

	@JsonProperty("CACertificate")
	public void setCACertificate(String cACertificate) {
		this.cACertificate = cACertificate;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("mqtt")
	public EugenioMqttDTO getMqtt() {
		return mqtt;
	}

	@JsonProperty("mqtt")
	public void setMqtt(EugenioMqttDTO mqtt) {
		this.mqtt = mqtt;
	}

	@JsonProperty("apiKey")
	public String getApiKey() {
		return apiKey;
	}

	@JsonProperty("apiKey")
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}