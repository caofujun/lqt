package com.nis.analysis.until;

import com.nis.comm.utils.r;
import org.apache.commons.lang.StringUtils;

public class a {
	private static String cN = "*10^";
	private static String cO = "\\*10\\^";

	public static double r(String result) {
		double value = 0.0D;
		if (result.indexOf(cN) > 0) {
			String[] results = result.split(cO);
			if (results.length > 1 && StringUtils.isNotBlank(results[0]) && StringUtils.isNotBlank(results[1])) {
				double res1 = r.e(results[0]).doubleValue();
				double res2 = 1.0D;
				String[] results1 = results[1].split("/");
				if (results1.length > 1) {
					res2 = r.e(results1[0]).doubleValue();
				} else {
					res2 = r.e(results[1]).doubleValue();
				}

				value = Math.pow(10.0D, res2);
				value = res1 * value;
			}
		} else {
			value = r.e(result).doubleValue();
		}

		return value;
	}

	public static double j(String result, String unit) {
		if (StringUtils.isNotBlank(unit) && unit.indexOf("10^") >= 0) {
			result = result + unit;
		}

		return r(result);
	}
}