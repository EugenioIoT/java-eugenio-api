package com.logicalis.eugenioclient;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.api.thing.EugenioThingApiClient;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioGetThingsRespDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioThingClientListTest extends EugenioClientTestBase {

	private EugenioThingApiClient eugenioThingApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);
		eugenioThingApiClient = eugenioClient.getThingClient();
	}

	@Test
	public void testListAllThings() {
		List<EugenioGetThingsRespDTO> result = eugenioThingApiClient.getAllThings(eugenioClient.getHeaders());

		System.out.println(result);

		Assert.assertTrue(result.size() > 0);
	}

	@Test
	public void testListLimitOffsetThings() {
		List<EugenioGetThingsRespDTO> result = eugenioThingApiClient.getThings(1, 1, eugenioClient.getHeaders());

		System.out.println(result);

		Assert.assertTrue(result.size() > 0);
	}

	@Test
	public void testGetThing() {
		EugenioGetThingsRespDTO thing = eugenioThingApiClient.getThing("deviceutest1", eugenioClient.getHeaders());

		System.out.println(thing);

		Assert.assertNotNull(thing);
	}

}
