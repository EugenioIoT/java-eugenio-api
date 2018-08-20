package com.logicalis.eugenio.client.api.common;

public class EugenioHeaders {

	private String token;
	private String tenant;
	private String apiKey;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = new StringBuilder("Bearer ").append(token).toString();
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
