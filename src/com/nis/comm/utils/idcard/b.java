package com.nis.comm.utils.idcard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class b {
	protected String[][] qp = new String[][]{{"11", "北京"}, {"12", "天津"}, {"13", "河北"}, {"14", "山西"}, {"15", "内蒙古"},
			{"21", "辽宁"}, {"22", "吉林"}, {"23", "黑龙江"}, {"31", "上海"}, {"32", "江苏"}, {"33", "浙江"}, {"34", "安徽"},
			{"35", "福建"}, {"36", "江西"}, {"37", "山东"}, {"41", "河南"}, {"42", "湖北"}, {"43", "湖南"}, {"44", "广东"},
			{"45", "广西"}, {"46", "海南"}, {"50", "重庆"}, {"51", "四川"}, {"52", "贵州"}, {"53", "云南"}, {"54", "西藏"},
			{"61", "陕西"}, {"62", "甘肃"}, {"63", "青海"}, {"64", "宁夏"}, {"65", "新疆"}, {"71", "台湾"}, {"81", "香港"},
			{"82", "澳门"}, {"91", "国外"}};
	private String[] qq = new String[]{"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35",
			"36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65",
			"71", "81", "82", "91"};
	private int[] qr = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
	private String[] qs = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

	public boolean bk(String idcard) {
		if (idcard.length() == 15) {
			idcard = this.bn(idcard);
		}

		return this.bl(idcard);
	}

	public boolean bl(String idcard) {
		if (idcard.length() != 18) {
			return false;
		} else {
			String idcard17 = idcard.substring(0, 17);
			String idcard18Code = idcard.substring(17, 18);
			Object c = null;
			String checkCode = "";
			if (this.br(idcard17)) {
				char[] c1 = idcard17.toCharArray();
				if (c1 != null) {
					int[] bit = new int[idcard17.length()];
					bit = this.a(c1);
					boolean sum17 = false;
					int sum171 = this.a(bit);
					checkCode = this.c(sum171);
					if (checkCode == null) {
						return false;
					}

					if (!idcard18Code.equalsIgnoreCase(checkCode)) {
						return false;
					}
				}

				return true;
			} else {
				return false;
			}
		}
	}

	public boolean bm(String idcard) {
		if (idcard.length() != 15) {
			return false;
		} else if (!this.br(idcard)) {
			return false;
		} else {
			String provinceid = idcard.substring(0, 2);
			String birthday = idcard.substring(6, 12);
			int year = Integer.parseInt(idcard.substring(6, 8));
			int month = Integer.parseInt(idcard.substring(8, 10));
			int day = Integer.parseInt(idcard.substring(10, 12));
			boolean flag = false;
			String[] year2bit = this.qq;
			int curYear = this.qq.length;

			for (int curDay = 0; curDay < curYear; ++curDay) {
				String birthdate = year2bit[curDay];
				if (birthdate.equals(provinceid)) {
					flag = true;
					break;
				}
			}

			if (!flag) {
				return false;
			} else {
				Date arg13 = null;

				try {
					arg13 = (new SimpleDateFormat("yyMMdd")).parse(birthday);
				} catch (ParseException arg12) {
					arg12.printStackTrace();
				}

				if (arg13 != null && !(new Date()).before(arg13)) {
					GregorianCalendar arg14 = new GregorianCalendar();
					curYear = arg14.get(1);
					int arg15 = Integer.parseInt(String.valueOf(curYear).substring(2));
					if (year < 50 && year > arg15) {
						return false;
					} else if (month >= 1 && month <= 12) {
						boolean mflag = false;
						arg14.setTime(arg13);
						switch (month) {
							case 1 :
							case 3 :
							case 5 :
							case 7 :
							case 8 :
							case 10 :
							case 12 :
								mflag = day >= 1 && day <= 31;
								break;
							case 2 :
								if (arg14.isLeapYear(arg14.get(1))) {
									mflag = day >= 1 && day <= 29;
								} else {
									mflag = day >= 1 && day <= 28;
								}
								break;
							case 4 :
							case 6 :
							case 9 :
							case 11 :
								mflag = day >= 1 && day <= 30;
						}

						return mflag;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
	}

	public String bn(String idcard) {
		String idcard17 = null;
		if (idcard.length() != 15) {
			return null;
		} else if (this.br(idcard)) {
			String birthday = idcard.substring(6, 12);
			Date birthdate = null;

			try {
				birthdate = (new SimpleDateFormat("yyMMdd")).parse(birthday);
			} catch (ParseException arg10) {
				arg10.printStackTrace();
			}

			Calendar cday = Calendar.getInstance();
			cday.setTime(birthdate);
			String year = String.valueOf(cday.get(1));
			idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);
			char[] c = idcard17.toCharArray();
			String checkCode = "";
			if (c != null) {
				int[] bit = new int[idcard17.length()];
				bit = this.a(c);
				boolean sum17 = false;
				int sum171 = this.a(bit);
				checkCode = this.c(sum171);
				if (checkCode == null) {
					return null;
				}

				idcard17 = idcard17 + checkCode;
			}

			return idcard17;
		} else {
			return null;
		}
	}

	public boolean bo(String idcard) {
		return idcard != null && !"".equals(idcard)
				? Pattern.matches("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard)
				: false;
	}

	public boolean bp(String idcard) {
		return idcard != null && !"".equals(idcard)
				? Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$", idcard)
				: false;
	}

	public boolean bq(String idcard) {
		return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
				idcard);
	}

	public boolean br(String str) {
		return str != null && !"".equals(str) ? str.matches("^[0-9]*$") : false;
	}

	public int a(int[] bit) {
		int sum = 0;
		if (this.qr.length != bit.length) {
			return sum;
		} else {
			for (int i = 0; i < bit.length; ++i) {
				for (int j = 0; j < this.qr.length; ++j) {
					if (i == j) {
						sum += bit[i] * this.qr[j];
					}
				}
			}

			return sum;
		}
	}

	public String c(int sum17) {
		String checkCode = null;
		switch (sum17 % 11) {
			case 0 :
				checkCode = "1";
				break;
			case 1 :
				checkCode = "0";
				break;
			case 2 :
				checkCode = "x";
				break;
			case 3 :
				checkCode = "9";
				break;
			case 4 :
				checkCode = "8";
				break;
			case 5 :
				checkCode = "7";
				break;
			case 6 :
				checkCode = "6";
				break;
			case 7 :
				checkCode = "5";
				break;
			case 8 :
				checkCode = "4";
				break;
			case 9 :
				checkCode = "3";
				break;
			case 10 :
				checkCode = "2";
		}

		return checkCode;
	}

	public int[] a(char[] c) throws NumberFormatException {
		int[] a = new int[c.length];
		int k = 0;
		char[] arg6 = c;
		int arg5 = c.length;

		for (int arg4 = 0; arg4 < arg5; ++arg4) {
			char temp = arg6[arg4];
			a[k++] = Integer.parseInt(String.valueOf(temp));
		}

		return a;
	}

	public static void main(String[] args) throws Exception {
		String idcard15 = "";
		String idcard18 = "";
		b iv = new b();
		boolean flag = false;
		flag = iv.bl(idcard18);
		System.out.println(flag);
		flag = iv.bm(idcard15);
		System.out.println(flag);
		System.out.println(iv.bn(idcard15));
		flag = iv.bl(iv.bn(idcard15));
		System.out.println(flag);
		System.out.println(iv.bk(idcard18));
	}
}