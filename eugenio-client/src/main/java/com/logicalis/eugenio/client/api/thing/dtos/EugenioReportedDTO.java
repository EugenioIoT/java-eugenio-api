package com.logicalis.eugenio.client.api.thing.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "$metadata", "$version" })
public class EugenioReportedDTO {

	@JsonProperty("$metadata")
	private EugenioMetadataDTO metadata;
	@JsonProperty("$version")
	private Integer version;

	@JsonProperty("$metadata")
	public EugenioMetadataDTO getMetadata() {
		return metadata;
	}

	@JsonProperty("$metadata")
	public void setMetadata(EugenioMetadataDTO $metadata) {
		this.metadata = $metadata;
	}

	@JsonProperty("$version")
	public Integer getVersion() {
		return version;
	}

	@JsonProperty("$version")
	public void setVersion(Integer $version) {
		this.version = $version;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
