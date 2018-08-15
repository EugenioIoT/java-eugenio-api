package com.logicalis.eugenio.client.config;

import com.logicalis.eugenio.client.util.EugenioAssert;

/**
 * Class to config common parameters to access eugenio api.
 */
public class EugenioClientConfig {

	private String apiKey;
	private String tenant;

	/**
	 * Default constructor.
	 */
	private EugenioClientConfig() {
	}

	/**
	 * @return Eugenio api key.
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @return tenant.
	 */
	public String getTenant() {
		return tenant;
	}

	/**
	 * Set apiKey.
	 * @param apiKey.
	 * @return {@link EugenioConfigApiKey}.
	 */
	public static EugenioConfigTenant withApiKey(String apiKey) {
		EugenioAssert.notBlank(apiKey);
		EugenioClientConfig clientConfig = new EugenioClientConfig();
		clientConfig.apiKey = apiKey;
		return new EugenioConfigTenant(clientConfig);
	}

	/**
	 * Eugenio config tenant.
	 */
	public static class EugenioConfigTenant {

		private EugenioClientConfig eugenioClientConfig;

		private EugenioConfigTenant(EugenioClientConfig eugenioClientConfig) {
			this.eugenioClientConfig = eugenioClientConfig;
		}

		/**
		 * Set tenant.
		 * @param tenant.
		 * @return {@link EugenioConfigBuilder}.
		 */
		public EugenioConfigBuilder ofTenant(String tenant) {
			EugenioAssert.notBlank(tenant);
			this.eugenioClientConfig.tenant = tenant;
			return new EugenioConfigBuilder(this.eugenioClientConfig);
		}
	}

	/**
	 * Eugenio config builder.
	 */
	public static class EugenioConfigBuilder {

		private EugenioClientConfig eugenioClientConfig;

		private EugenioConfigBuilder(EugenioClientConfig eugenioClientConfig) {
			this.eugenioClientConfig = eugenioClientConfig;
		}

		/**
		 * Build.
		 * @return {@link EugenioClientConfig}.
		 */
		public EugenioClientConfig build() {
			return this.eugenioClientConfig;
		}
	}

}
