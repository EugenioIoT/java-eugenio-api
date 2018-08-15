package com.logicalis.eugenioclient;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.api.user.EugenioUserApiClient;
import com.logicalis.eugenio.client.api.user.dtos.EugenioGetUserRespDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioUserClientListTest extends EugenioClientTestBase {

	private EugenioUserApiClient eugenioUserApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);
		eugenioUserApiClient = eugenioClient.getUserClient();
	}

	@Test
	public void testListAllUsers() {
		List<EugenioGetUserRespDTO> result = eugenioUserApiClient.getAllUsers(eugenioClient.getHeaders());

		System.out.println(result);

		Assert.assertTrue(result.size() > 0);
	}

	@Test
	public void testListUsersLimitOffset() {
		List<EugenioGetUserRespDTO> result = eugenioUserApiClient.getUsers(1, 1, eugenioClient.getHeaders());
		Assert.assertTrue(result.size() == 1);
	}

}
