package com.logicalis.eugenio.client.statefull;

public class EugenioClientStateBuilder {

	EugenioStatefullClient eugenioClient;

	EugenioClientStateBuilder(EugenioStatefullClient eugenioClient) {
		this.eugenioClient = eugenioClient;
	}

	/**
	 * Build.
	 * 
	 * @return state full client.
	 */
	public EugenioStatefullClient buildStatefull() {
		return eugenioClient;
	}

}
