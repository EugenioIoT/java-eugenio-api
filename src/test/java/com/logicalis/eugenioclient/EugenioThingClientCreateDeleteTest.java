package com.logicalis.eugenioclient;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.api.thing.EugenioThingApiClient;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioCreateThingDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioCreateThingRespDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioTagsDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioThingClientCreateDeleteTest extends EugenioClientTestBase {

	private EugenioThingApiClient eugenioThingApiClient;
	private EugenioCreateThingRespDTO thingCreated;

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
	public void testCreateThingSuccess() {

		LocalDateTime now = LocalDateTime.now();

		EugenioCreateThingDTO thing = new EugenioCreateThingDTO();
		thing.setDeviceId(new StringBuilder("astvjuit").append(now.getHour()).append(now.getMinute()).toString());
		thing.setThumbprint("0123456789012345678901234567890123456789");
		thing.setSchemaName("vcvaleriotest");
		thing.setTags(new EugenioTagsDTO());
		thing.getTags().setDeviceName("AstVJunt");
		thing.getTags().setDeviceDescription("Device test");
		thingCreated = eugenioThingApiClient.createThing(thing, eugenioClient.getHeaders());
		System.out.println(thingCreated);
		Assert.assertNotNull(thingCreated);
		deleteThingSuccess();
	}

	private void deleteThingSuccess() {
		eugenioThingApiClient.deleteThing(thingCreated.getDeviceId(), eugenioClient.getHeaders());
	}

}
