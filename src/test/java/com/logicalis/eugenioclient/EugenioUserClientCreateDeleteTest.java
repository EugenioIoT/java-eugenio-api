package com.logicalis.eugenioclient;

import java.util.Arrays;

import org.junit.Test;

import com.logicalis.eugenio.client.api.user.EugenioUserApiClient;
import com.logicalis.eugenio.client.api.user.dtos.EugenioUserToCreateDTO;
import com.logicalis.eugenio.client.api.user.dtos.EugenioUserToCreateRespDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioUserClientCreateDeleteTest extends EugenioClientTestBase {

	private EugenioUserToCreateRespDTO userCreated;
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
	public void testCreateDeleteUserSuccess() {
		EugenioUserToCreateDTO user = new EugenioUserToCreateDTO();
		user.setUsername("gcvalerio");
		user.setEmail("gcvalerio@artit.com.br");
		user.setFirstName("Fabio");
		user.setLastName("Valerio");
		user.setPassword("gcvalerio123");
		user.setRole("USER");
		user.setOrganizations(Arrays.asList(EugenioTestProperty.getProperty("eugenio.tenant")));

		userCreated = eugenioUserApiClient.createUser(user, eugenioClient.getHeaders());

		deleteUser();
	}

	private void deleteUser() {
		try {
			eugenioUserApiClient.deleteUser(userCreated.getId(), eugenioClient.getHeaders());
		} catch (Throwable ex) {
			System.out.println("Error when try to delete the created user");
			throw ex;
		}
	}

}
