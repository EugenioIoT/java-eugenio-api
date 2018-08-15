package com.logicalis.eugenio.client.api.user.dtos;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ids" })
public class EugenioGroupsDTO {

	@JsonProperty("ids")
	private List<String> ids = null;

	@JsonProperty("ids")
	public List<String> getIds() {
		return ids;
	}

	@JsonProperty("ids")
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}