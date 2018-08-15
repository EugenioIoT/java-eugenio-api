package com.logicalis.eugenioclient.util;

import com.logicalis.eugenio.client.statefull.EugenioClientBuilder;
import com.logicalis.eugenio.client.statefull.EugenioStatefullClient;

public class EugenioClientTestUtil {

	public static EugenioStatefullClient createEugenioClientTest() {
		EugenioStatefullClient eugenioClient = EugenioClientBuilder.withUri(EugenioTestProperty.getProperty("eugenio.uri"))
				.byDefaultClient().buildStatefull();

		return eugenioClient;
	}

}
