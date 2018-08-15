package com.logicalis.eugenio.client.api.auth.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EugenioLoginResponseDTO {

	@JsonProperty(value = "access_token")
	private String accessToken;
	
	@JsonProperty(value = "token_type")
	private String tokenType;
	
	@JsonProperty(value = "session_state")
	private String sessionState;

	@JsonProperty(value = "expires_in")
	private long expiresIn;

	@JsonProperty(value = "not-before-policy")
	private long notBeforePolicy;

	@JsonProperty(value = "refresh_token")
	private String refreshToken;

	@JsonProperty(value = "refresh_expires_in")
	private long refreshExpiresIn;

	public EugenioLoginResponseDTO() { }
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getSessionState() {
		return sessionState;
	}

	public void setSessionState(String sessionState) {
		this.sessionState = sessionState;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getNotBeforePolicy() {
		return notBeforePolicy;
	}

	public void setNotBeforePolicy(long notBeforePolicy) {
		this.notBeforePolicy = notBeforePolicy;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getRefreshExpiresIn() {
		return refreshExpiresIn;
	}

	public void setRefreshExpiresIn(long refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
