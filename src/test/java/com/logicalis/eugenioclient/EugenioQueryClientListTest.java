package com.logicalis.eugenioclient;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.api.query.DataQueryRequest;
import com.logicalis.eugenio.client.api.query.EugenioQueryApiClient;
import com.logicalis.eugenio.client.api.query.dtos.EugenioQueryResponseDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;
import com.logicalis.eugenioclient.util.EugenioTestProperty;

public class EugenioQueryClientListTest extends EugenioClientTestBase {

	private EugenioQueryApiClient eugenioQueryApiClient;

	public void setup() {
		InfoLoginDTO eugenioLogin = new InfoLoginDTO();
		eugenioLogin.setUsername(EugenioTestProperty.getProperty("eugenio.username"));
		eugenioLogin.setPassword(EugenioTestProperty.getProperty("eugenio.password"));
		eugenioLogin.setTenant(EugenioTestProperty.getProperty("eugenio.tenant"));
		eugenioLogin.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));

		eugenioClient.login(eugenioLogin);
		eugenioQueryApiClient = eugenioClient.getQueryClient();
	}

	@Test
	public void testListAllUsers() {
		DataQueryRequest dataQueryRequest = new DataQueryRequest();
		dataQueryRequest.setApiKey(EugenioTestProperty.getProperty("eugenio.apikey"));
		dataQueryRequest.setQuery("SELECT a.* FROM eugenio_demo_sandbox.v_vcvaleriotestum a");
		List<EugenioQueryResponseDTO> result = eugenioQueryApiClient.get(EugenioQueryResponseDTO.class,
				dataQueryRequest);

		System.out.println(result);

		Assert.assertTrue(result.size() > 0);
	}

}
