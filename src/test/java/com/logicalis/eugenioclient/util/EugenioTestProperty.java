package com.logicalis.eugenioclient.util;

import java.io.FileInputStream;
import java.util.Properties;

public class EugenioTestProperty {

	private static Properties appProps;

	private static void loadFile() throws RuntimeException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "eugenio-client.properties";

		appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
		} catch (Exception ex) {
			throw new RuntimeException("File not found");
		}
	}

	public static String getProperty(String name) {
		if (appProps == null) {
			loadFile();
		}
		return appProps.getProperty(name);
	}

}
