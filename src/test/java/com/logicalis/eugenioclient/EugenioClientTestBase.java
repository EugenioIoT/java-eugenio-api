package com.logicalis.eugenioclient;

import org.junit.Before;

import com.logicalis.eugenio.client.statefull.EugenioStatefullClient;
import com.logicalis.eugenioclient.util.EugenioClientTestUtil;

/**
 * Common test eugenio client.
 */
public abstract class EugenioClientTestBase {

	EugenioStatefullClient eugenioClient;

	@Before
	public void processSetup() {
		eugenioClient = EugenioClientTestUtil.createEugenioClientTest();
		setup();
	}

	/**
	 * Specific setup.
	 */
	public void setup() {
	}

}
