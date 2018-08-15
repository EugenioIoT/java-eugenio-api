package com.logicalis.eugenio.client.util;

public class EugenioAssert {

	private EugenioAssert() {
	}

	public static void notNull(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
	}

	public static void notBlank(String str) {
		notNull(str);
		if (str.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
	}

}
