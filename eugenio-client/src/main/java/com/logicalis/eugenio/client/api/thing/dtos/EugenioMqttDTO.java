package com.logicalis.eugenio.client.api.thing.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "host", "port", "cloudToDevice", "deviceToCloud" })
public class EugenioMqttDTO {

	@JsonProperty("host")
	private String host;
	@JsonProperty("port")
	private Integer port;
	@JsonProperty("cloudToDevice")
	private String cloudToDevice;
	@JsonProperty("deviceToCloud")
	private String deviceToCloud;

	@JsonProperty("host")
	public String getHost() {
		return host;
	}

	@JsonProperty("host")
	public void setHost(String host) {
		this.host = host;
	}

	@JsonProperty("port")
	public Integer getPort() {
		return port;
	}

	@JsonProperty("port")
	public void setPort(Integer port) {
		this.port = port;
	}

	@JsonProperty("cloudToDevice")
	public String getCloudToDevice() {
		return cloudToDevice;
	}

	@JsonProperty("cloudToDevice")
	public void setCloudToDevice(String cloudToDevice) {
		this.cloudToDevice = cloudToDevice;
	}

	@JsonProperty("deviceToCloud")
	public String getDeviceToCloud() {
		return deviceToCloud;
	}

	@JsonProperty("deviceToCloud")
	public void setDeviceToCloud(String deviceToCloud) {
		this.deviceToCloud = deviceToCloud;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}