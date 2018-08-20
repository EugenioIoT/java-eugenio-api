package com.logicalis.eugenio.client.api.thing.dtos;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "schemaName", "tags" })
public class EugenioPutThingDTO {

	@JsonProperty("schemaName")
	private String schemaName;
	@JsonProperty("tags")
	private EugenioTagsDTO tags;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}