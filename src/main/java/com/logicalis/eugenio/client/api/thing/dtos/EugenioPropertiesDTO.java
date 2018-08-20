package com.logicalis.eugenio.client.api.thing.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "desired", "reported" })
public class EugenioPropertiesDTO {

	@JsonProperty("desired")
	private EugenioDesiredDTO desired;
	@JsonProperty("reported")
	private EugenioReportedDTO reported;

	@JsonProperty("desired")
	public EugenioDesiredDTO getDesired() {
		return desired;
	}

	@JsonProperty("desired")
	public void setDesired(EugenioDesiredDTO desired) {
		this.desired = desired;
	}

	@JsonProperty("reported")
	public EugenioReportedDTO getReported() {
		return reported;
	}

	@JsonProperty("reported")
	public void setReported(EugenioReportedDTO reported) {
		this.reported = reported;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}