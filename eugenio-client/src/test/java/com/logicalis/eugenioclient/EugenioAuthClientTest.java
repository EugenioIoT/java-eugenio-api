package com.logicalis.eugenioclient;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.exception.EugenioApiException;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioAuthClientTest extends EugenioClientTestBase {

	@Test
	public void testAuthClientLoginSuccess() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);

		Assert.assertTrue(eugenioClient.getEugenioSession() != null);
	}

	@Test(expected = EugenioApiException.class)
	public void testAuthClientLoginWrongUser() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername("vcosta1");
		eugenioLogin.setPassword("vcosta123");
		eugenioLogin.setTenant("eugenio_demo");
		eugenioLogin.setApiKey("apikey");

		eugenioClient.login(eugenioLogin);
	}

	@Test(expected = EugenioApiException.class)
	public void testAuthClientLoginWrongPass() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername("vcosta");
		eugenioLogin.setPassword("wrongpass");
		eugenioLogin.setTenant("eugenio_demo");
		eugenioLogin.setApiKey("apikey");

		eugenioClient.login(eugenioLogin);
	}

	@Test(expected = EugenioApiException.class)
	public void testAuthClientLoginWrongTenant() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername("vcosta");
		eugenioLogin.setPassword("vcosta123");
		eugenioLogin.setTenant("wrongtenant");
		eugenioLogin.setApiKey("apikey");

		eugenioClient.login(eugenioLogin);
	}

}
