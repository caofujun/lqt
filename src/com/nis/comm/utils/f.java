package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import com.nis.comm.utils.s;
import com.nis.comm.utils.idcard.a;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class f {
	public static final String pz = "yyyy-MM-dd";
	public static final String pA = "yyyy-MM-dd HH:mm:ss";

	public static Timestamp getNowDatetime() {
		Timestamp datetime = new Timestamp(System.currentTimeMillis());
		return datetime;
	}

	public static int a(Date date1, Date date2) {
		long second = (date1.getTime() - date2.getTime()) / 1000L;
		return (int) (second / 24L / 60L / 60L);
	}

	public static int b(Date date1, Date date2) {
		long second = (date1.getTime() - date2.getTime()) / 1000L;
		return (int) (second / 60L);
	}

	public static int c(Date date1, Date date2) {
		long second = (date1.getTime() - date2.getTime()) / 1000L;
		return (int) (second / 60L / 60L);
	}

	public static Date a(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(5, days);
		return c.getTime();
	}

	public static Date b(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(2, days);
		return c.getTime();
	}

	public static Date c(Date date, int years) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(1, years);
		return c.getTime();
	}

	public static Date d(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(11, hour);
		return c.getTime();
	}

	public static Date e(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(12, minute);
		return c.getTime();
	}

	public static Date addSeconds(Date date, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(13, seconds);
		return c.getTime();
	}

	public static String a(Date time, String separator) {
		if (separator == null) {
			separator = "-";
		}

		String formatStr = "";
		if (separator.equals("年月日")) {
			formatStr = "yyyy年MM月dd日";
		} else {
			formatStr = "yyyy" + separator + "MM" + separator + "dd";
		}

		return c(time, formatStr);
	}

	public static String b(Date time, String separator) {
		if (separator == null) {
			separator = "-";
		}

		String formatStr = "";
		if (separator.equals("月日")) {
			formatStr = "MM月dd日";
		} else {
			formatStr = "MM" + separator + "dd";
		}

		return c(time, formatStr);
	}

	public static String d(Date time) {
		if (time == null) {
			return "";
		} else {
			int t = d(new Date(), time);
			return t == 0 ? "今天" : (t == 1 ? "昨天" : c(time, "yyyy-MM-dd"));
		}
	}

	public static String e(Date time) {
		return c(time, "yyyy-MM-dd HH:mm");
	}

	public static String f(Date time) {
		return c(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static String c(Date time, String format) {
		if (time != null) {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
			return DATE_FORMAT.format(time);
		} else {
			return "";
		}
	}

	public static String g(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date) {
		if (date != null) {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
			return DATE_FORMAT.format(date);
		} else {
			return "";
		}
	}

	public static String X(String date) {
		return formatDate((String) date, (String) null);
	}

	public static String formatDate(String date, String format) {
		if (date != null) {
			String format1 = date.length() == 10 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";
			l(date, format);
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(StringUtils.isBlank(format) ? "yyyy-MM-dd" : format,
					Locale.SIMPLIFIED_CHINESE);
			return DATE_FORMAT.format(l(date, format1));
		} else {
			return "";
		}
	}

	public static String formatDate(Date date, String format) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(StringUtils.isBlank(format) ? "yyyy-MM-dd" : format,
					Locale.SIMPLIFIED_CHINESE);
			return dateFormat.format(date);
		} else {
			return "";
		}
	}

	public static String a(int second) {
		StringBuffer r = new StringBuffer();
		int days = second / 86400;
		int hours = second % 86400 / 3600;
		int minutes = second % 3600 / 60;
		int s = second % 60;
		if (days > 0) {
			r.append(days).append("天");
		}

		if (hours > 0) {
			r.append(hours).append("小时");
		}

		if (minutes > 0) {
			r.append(minutes).append("分");
		}

		if (s > 0) {
			r.append(s).append("秒");
		}

		return r.toString();
	}

	public static String h(Date date) {
		return a((int) ((System.currentTimeMillis() - date.getTime()) / 1000L));
	}

	public static int d(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			boolean r = false;
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(date1);
			c2.setTime(date2);
			c1.set(11, 0);
			c1.set(12, 0);
			c1.set(13, 0);
			c1.set(14, 0);
			c2.set(11, 0);
			c2.set(12, 0);
			c2.set(13, 0);
			c2.set(14, 0);
			int r1 = c1.compareTo(c2);
			if (r1 != 0) {
				r1 = (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 24L / 3600L / 1000L);
			}

			return r1;
		} else {
			return 0;
		}
	}

	public static Timestamp i(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		c.set(14, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp f(Date date, int year) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(1, year);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp g(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(2, month);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp h(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(5, day);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getCurrentDay() {
		Calendar c = Calendar.getInstance();
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		c.set(14, 0);
		Timestamp time = new Timestamp(c.getTimeInMillis());
		return time;
	}

	public static Timestamp getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		c.set(5, 1);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		c.set(14, 0);
		Timestamp time = new Timestamp(c.getTimeInMillis());
		return time;
	}

	public static Date f(boolean isDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(5, 1);
		Date date = cal.getTime();
		String dateStr = "";
		if (isDate) {
			dateStr = c(date, "yyyy-MM-dd HH:mm:ss");
			date = Z(dateStr);
		}

		return date;
	}

	public static Timestamp Y(String s) {
		return k(s, "yyyy-MM-dd");
	}

	public static Timestamp k(String s, String format) {
		if (s != null && s.trim().length() != 0) {
			try {
				SimpleDateFormat e = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
				return new Timestamp(e.parse(s).getTime());
			} catch (Exception arg2) {
				arg2.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public static Date l(String string, String format) {
		if (ab.isEmpty(string)) {
			return null;
		} else {
			if (format == null) {
				format = "yyyy-MM-dd";
			}

			Date date = null;
			SimpleDateFormat dateformat = new SimpleDateFormat(format);

			try {
				date = dateformat.parse(string);
			} catch (Exception arg4) {
				arg4.printStackTrace();
			}

			return date;
		}
	}

	public static Date Z(String string) {
		return l(string, (String) null);
	}

	public static int aa(String birthYear) {
		return a((String) birthYear, (Date) null);
	}

	public static int d(Date birth, String cardNo) {
		if (birth == null) {
			if (ab.isEmpty(cardNo)) {
				return 0;
			} else {
				a cald1 = new a(cardNo);
				return cald1.getAge();
			}
		} else {
			Calendar cald = Calendar.getInstance();
			cald.setTime(new Date(birth.getTime()));
			String year = "" + cald.get(1);
			return a((String) year, (Date) null);
		}
	}

	public static int a(String birthYear, Date currentDay) {
		Calendar n = Calendar.getInstance();
		if (currentDay == null) {
			n.setTime(getNowDatetime());
		} else {
			n.setTime(currentDay);
		}

		return n.get(1) - s.az(birthYear).intValue();
	}

	public static Date ab(String cardNo) {
		a idcardInfo = new a(cardNo);
		return idcardInfo.getBirthday();
	}

	public static String j(Date time) {
		return c(time, "yyyy年MM月dd日");
	}

	public static String k(Date time) {
		return c(time, "yyyy年MM月");
	}

	public static String l(Date time) {
		return c(time, "yyyy-MM-dd");
	}

	public static String m(Date time) {
		return c(time, "yyyy-MM");
	}

	public static Date a(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		return c.getTime();
	}

	public static Date b(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, 23);
		c.set(12, 59);
		c.set(13, 59);
		return c.getTime();
	}

	public static int getCurHour() {
		Calendar c = Calendar.getInstance();
		return c.get(11);
	}

	public static int getCurYear() {
		Calendar c = Calendar.getInstance();
		return c.get(1);
	}

	public static int getCurSeason() {
		return n((Date) null);
	}

	public static int n(Date date) {
		byte season = 0;
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}

		int month = c.get(2);
		switch (month) {
			case 0 :
			case 1 :
			case 2 :
				season = 1;
				break;
			case 3 :
			case 4 :
			case 5 :
				season = 2;
				break;
			case 6 :
			case 7 :
			case 8 :
				season = 3;
				break;
			case 9 :
			case 10 :
			case 11 :
				season = 4;
		}

		return season;
	}

	public static int getCurMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(2) + 1;
	}

	public static int e(Date date1, Date date2) {
		return date1 == null && date2 == null
				? 0
				: (date1 == null
						? -1
						: (date2 == null
								? 1
								: (date1.getTime() - date2.getTime() == 0L
										? 0
										: ((int) (date1.getTime() - date2.getTime()) > 0 ? 1 : -1))));
	}

	public static Date o(Date date) {
		String datefm = formatDate(date);
		datefm = datefm + " 23:59";
		return k(datefm, "yyyy-MM-dd HH:mm");
	}

	public static Date p(Date date) {
		String datefm = formatDate(date);
		datefm = datefm + " 23:59:59";
		return k(datefm, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date e(Date date, String time) {
		String datefm = formatDate(date);
		datefm = datefm + " " + (Integer.parseInt(time) - 1) + ":59:59";
		return k(datefm, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date q(Date date) {
		String datefm = formatDate(date);
		datefm = datefm + " 00:00:00";
		return k(datefm, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date y() {
		String datefm = formatDate(getCurDate());
		datefm = datefm + " 00:00:00";
		return k(datefm, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date g(boolean isDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(5, 1);
		Object date = cal.getTime();
		if (isDate) {
			String datefm = formatDate((Date) date);
			date = k(datefm, "yyyy-MM-dd");
		}

		return (Date) date;
	}

	public static Date getCurDate() {
		return Calendar.getInstance().getTime();
	}

	public static Date a(Date date, boolean isDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(5, 1);
		Object date1 = cal.getTime();
		if (isDate) {
			String datefm = formatDate((Date) date1);
			date1 = k(datefm, "yyyy-MM-dd");
		}

		return (Date) date1;
	}

	public static Date b(Date date, boolean isDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, 1);
		cal.set(5, 0);
		cal.set(11, 23);
		cal.set(12, 59);
		cal.set(13, 59);
		cal.set(14, 999);
		Object date1 = cal.getTime();
		if (isDate) {
			String datefm = formatDate((Date) date1);
			date1 = k(datefm, "yyyy-MM-dd");
		}

		return (Date) date1;
	}

	public static Date h(boolean isDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(5, cal.getActualMaximum(5));
		Object date = cal.getTime();
		if (isDate) {
			String datefm = formatDate((Date) date);
			date = k(datefm, "yyyy-MM-dd");
		}

		return (Date) date;
	}

	public static String[] r(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int maxDay = cal.getActualMaximum(5);
		String[] days = new String[maxDay];

		for (int day = 1; day <= maxDay; ++day) {
			days[day - 1] = String.valueOf(day);
		}

		return days;
	}

	public static int[] getMonths() {
		int[] months = new int[12];

		for (int i = 0; i < months.length; ++i) {
			months[i] = i + 1;
		}

		return months;
	}

	public static int[] getYears() {
		int[] years = new int[10];

		for (int i = 0; i < 10; ++i) {
			years[i] = getCurYear() - i;
		}

		return years;
	}

	public static String ac(String date) {
		if (ab.isEmpty(date)) {
			return null;
		} else {
			String[] days = date.split("-");
			return days.length > 2 ? days[2] : null;
		}
	}

	public static String b(Object date) {
		return date == null ? null : ac(String.valueOf(date));
	}

	public static Date getYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(1);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(1, currentYear);
		return calendar.getTime();
	}

	public static Date getYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(1);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(1, currentYear);
		calendar.roll(6, -1);
		return calendar.getTime();
	}

	public static boolean s(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(5, calendar.get(5) + 1);
		return calendar.get(5) == 1;
	}

	public static boolean t(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int today = calendar.get(5);
		return today == 1;
	}

	public static Date u(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int[] months = new int[]{1, 4, 7, 10};
		int month = calendar.get(2);
		calendar.set(5, 1);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		if (month >= 2 && month <= 4) {
			calendar.set(2, months[0]);
		} else if (month >= 5 && month <= 7) {
			calendar.set(2, months[1]);
		} else if (month >= 8 && month <= 10) {
			calendar.set(2, months[2]);
		} else {
			calendar.set(2, months[3]);
		}

		return calendar.getTime();
	}

	public static Date v(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int[] months = new int[]{3, 6, 9, 12};
		int month = calendar.get(2);
		calendar.set(5, 0);
		calendar.set(11, 23);
		calendar.set(12, 59);
		calendar.set(13, 59);
		calendar.set(14, 999);
		if (month >= 2 && month <= 4) {
			calendar.set(2, months[0]);
		} else if (month >= 5 && month <= 7) {
			calendar.set(2, months[1]);
		} else if (month >= 8 && month <= 10) {
			calendar.set(2, months[2]);
		} else {
			calendar.set(2, months[3]);
		}

		return calendar.getTime();
	}

	public static Date f(Date date, Date date1) {
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.setTime(date);
		cal1.setTime(date1);
		cal.set(11, cal1.get(11));
		cal.set(12, cal1.get(12));
		cal.set(13, cal1.get(13));
		return cal.getTime();
	}

	public static void main(String[] args) {
		System.out.println(b(new Date(), false));
	}
}