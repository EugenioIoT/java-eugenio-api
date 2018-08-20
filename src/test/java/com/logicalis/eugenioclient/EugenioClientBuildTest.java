package com.logicalis.eugenioclient;

import org.junit.Assert;
import org.junit.Test;

import com.logicalis.eugenio.client.statefull.EugenioClientBuilder;
import com.logicalis.eugenio.client.statefull.EugenioStatefullClient;

/**
 * Eugenio client build test.
 */
public class EugenioClientBuildTest {

	private String uri = "test";

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowAnExceptionForNullConfig() {
		EugenioClientBuilder.withUri(null).byDefaultClient().buildStatefull();
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowAnExceptionForNullJerseyClient() {
		EugenioClientBuilder.withUri(this.uri).byClient(null).buildStatefull();
	}

	@Test
	public void shouldCreateEugenioClient() {
		EugenioStatefullClient eugenioClient = EugenioClientBuilder.withUri(uri).byDefaultClient().buildStatefull();
		Assert.assertTrue(eugenioClient != null);
	}

}
