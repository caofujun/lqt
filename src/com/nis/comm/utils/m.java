package com.nis.comm.utils;

import java.util.ArrayList;
import java.util.List;

public class m {
	public static <T> List<List<T>> b(List<T> source, int n) {
		ArrayList result = new ArrayList();
		int remaider = source.size() % n;
		int number = source.size() / n;
		int offset = 0;

		for (int i = 0; i < n; ++i) {
			List value = null;
			if (remaider > 0) {
				value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
				--remaider;
				++offset;
			} else {
				value = source.subList(i * number + offset, (i + 1) * number + offset);
			}

			result.add(value);
		}

		return result;
	}
}