package com.logicalis.eugenio.client.statefull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.logicalis.eugenio.client.util.EugenioAssert;

/**
 * Eugenio client jersey builder.
 */
public class EugenioClientJerseyBuilder {

	private EugenioStatefullClient eugenioClient;

	EugenioClientJerseyBuilder(EugenioStatefullClient eugenioClient) {
		this.eugenioClient = eugenioClient;
	}

	/**
	 * Set jersey client.
	 * 
	 * @param client.
	 * @return {@link EugenioClientStateBuilder}.
	 */
	public EugenioClientStateBuilder byDefaultClient() {
		this.eugenioClient.client = ClientBuilder.newClient();
		return new EugenioClientStateBuilder(this.eugenioClient);
	}

	/**
	 * Set jersey client.
	 * 
	 * @param client.
	 * @return {@link EugenioClientStateBuilder}.
	 */
	public EugenioClientStateBuilder byClient(Client client) {
		EugenioAssert.notNull(client);
		this.eugenioClient.client = client;
		return new EugenioClientStateBuilder(this.eugenioClient);
	}


}
