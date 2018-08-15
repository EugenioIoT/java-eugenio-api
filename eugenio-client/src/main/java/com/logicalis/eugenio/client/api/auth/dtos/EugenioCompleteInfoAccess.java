package com.logicalis.eugenio.client.api.auth.dtos;

import com.logicalis.eugenio.client.api.auth.dtos.EugenioLoginDTO;

public class EugenioCompleteInfoAccess {

	private String apiKey;
	private EugenioLoginDTO login;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public EugenioLoginDTO getLogin() {
		return login;
	}

	public void setLogin(EugenioLoginDTO login) {
		this.login = login;
	}

}
