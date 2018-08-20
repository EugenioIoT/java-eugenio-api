package com.logicalis.eugenio.client.statefull;

import com.logicalis.eugenio.client.util.EugenioAssert;

public class EugenioClientBuilder {

	public static EugenioClientJerseyBuilder withUri(String uri) {
		EugenioAssert.notNull(uri);
		EugenioStatefullClient eugenioClient = new EugenioStatefullClient();
		eugenioClient.uri = uri;
		return new EugenioClientJerseyBuilder(eugenioClient);
	}

}
