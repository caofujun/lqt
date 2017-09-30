package com.nis.comm.utils;

public class j {
	private static String[] pE = new String[]{"png", "jpg"};

	public static boolean ap(String imageType) {
		String[] arg3 = pE;
		int arg2 = pE.length;

		for (int arg1 = 0; arg1 < arg2; ++arg1) {
			String type = arg3[arg1];
			if (type.equalsIgnoreCase(imageType)) {
				return true;
			}
		}

		return false;
	}
}