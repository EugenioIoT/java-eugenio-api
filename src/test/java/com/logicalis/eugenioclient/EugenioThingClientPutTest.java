package com.logicalis.eugenioclient;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.api.thing.EugenioThingApiClient;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioGetThingsRespDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioPutThingDTO;
import com.logicalis.eugenio.client.api.thing.dtos.EugenioTagsDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioThingClientPutTest extends EugenioClientTestBase {

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
	public void testPutThingSuccess() {
		EugenioPutThingDTO thing = new EugenioPutThingDTO();
		thing.setSchemaName("vcvaleriotest");
		thing.setTags(new EugenioTagsDTO());
		thing.getTags().setDeviceName("Device UTest alt 11");
		thing.getTags().setDeviceDescription("Device test alt 11");
		eugenioThingApiClient.putThing("deviceutest1", thing, eugenioClient.getHeaders());

		EugenioGetThingsRespDTO thingFound = eugenioThingApiClient.getThing("deviceutest1", eugenioClient.getHeaders());
		Assert.assertEquals(thing.getTags().getDeviceName(), thingFound.getTags().getDeviceName());
	}

}
