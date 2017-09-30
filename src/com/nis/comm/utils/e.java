package com.nis.comm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class e {
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		System.err.println("请输入查询月份");
		Scanner sc = new Scanner(System.in);
		String dateValue = sc.next();
		Date date = sf.parse(dateValue);
		System.err.println(sf.format(date));
		if (sc != null) {
			sc.close();
		}

		c(date);
	}

	public static void c(Date nowDate) {
		Calendar cad = Calendar.getInstance();
		cad.setTime(nowDate);
		int day_month = cad.getActualMaximum(5);
		int[][] array = new int[6][7];

		int i;
		int j;
		for (int weeks = 0; weeks <= day_month - 1; ++weeks) {
			cad.set(5, weeks + 1);
			i = cad.get(4);
			j = cad.get(7);
			array[i - 1][j - 1] = weeks + 1;
		}

		String[] arg8 = new String[]{"日", "一", "二", "三", "四", "五", "六"};
		String[] arg7 = arg8;
		int arg6 = arg8.length;

		for (j = 0; j < arg6; ++j) {
			String arg9 = arg7[j];
			System.err.print(arg9 + "\t");
		}

		System.err.println();

		for (i = 0; i <= array.length - 1; ++i) {
			for (j = 0; j <= array[i].length - 1; ++j) {
				if (array[i][j] != 0) {
					System.err.print(array[i][j]);
				}

				System.err.print("\t");
				if ((j + 1) % 7 == 0) {
					System.err.println();
				}
			}
		}

	}
}