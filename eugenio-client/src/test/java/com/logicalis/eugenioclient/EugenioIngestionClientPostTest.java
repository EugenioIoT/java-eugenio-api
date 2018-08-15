package com.logicalis.eugenioclient;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.logicalis.eugenio.client.api.ingestion.EugenioIngestionApiClient;
import com.logicalis.eugenio.client.api.ingestion.dtos.EugenioIngestionDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioIngestionClientPostTest extends EugenioClientTestBase {

	private EugenioIngestionApiClient eugenioIngestionApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);

		eugenioIngestionApiClient = eugenioClient.getIngestionClient();
	}

	@Test
	public void testIngestionSuccess() {
		List<EugenioIngestionDTO> list = new ArrayList<>();
		// vcvaleriotest
		list.add(new EugenioIngestionDTO().addField("humidity", 5d).addField("temperature", 30d));
		list.add(new EugenioIngestionDTO().addField("humidity", 3d).addField("temperature", 100d));
		list.add(new EugenioIngestionDTO().addField("humidity", 8d).addField("temperature", 50d));
		eugenioIngestionApiClient.postIngestion("vcvaleriotest", list, eugenioClient.getHeaders());
	}

}
