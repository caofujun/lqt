package com.nis.comm.utils;

import java.util.UUID;

public class af {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	public static String getUUID30() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24, 34);
	}

	public static void main(String[] args) {
		System.out.println("-uuid=" + getUUID());
		System.out.println("-uuid=" + getUUID32());
		System.out.println("-uuid=" + getUUID30());
	}
}