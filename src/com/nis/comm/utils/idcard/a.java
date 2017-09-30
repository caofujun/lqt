package com.nis.comm.utils.idcard;

import com.nis.comm.utils.idcard.b;
import com.nis.comm.utils.idcard.a.1;
import com.nis.comm.utils.idcard.a.2;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class a {
	private String province;
	private String city;
	private String region;
	private int year;
	private int month;
	private int day;
	private String qj;
	private Date birthday;
	private int qk;
	private String register;
	private Map<String, String> ql = new 1(this);
	private Map<String, String> qm = new 2(this);
	private b qn = null;

	public a(String idcard) {
		try {
			this.qn = new b();
			if (this.qn.bk(idcard)) {
				if (idcard.length() == 15) {
					idcard = this.qn.bn(idcard);
				}

				String e = idcard.substring(0, 2);
				this.province = (String) this.ql.get(e);
				String registerId = idcard.substring(0, 6);
				this.setRegister((String) this.qm.get(registerId));
				String id17 = idcard.substring(16, 17);
				if (Integer.parseInt(id17) % 2 != 0) {
					this.qj = "男";
				} else {
					this.qj = "女";
				}

				String birthday = idcard.substring(6, 14);
				Date birthdate = (new SimpleDateFormat("yyyyMMdd")).parse(birthday);
				this.birthday = birthdate;
				GregorianCalendar currentDay = new GregorianCalendar();
				currentDay.setTime(birthdate);
				this.year = currentDay.get(1);
				this.month = currentDay.get(2) + 1;
				this.day = currentDay.get(5);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
				String year = simpleDateFormat.format(new Date());
				this.qk = Integer.parseInt(year) - this.year;
			}
		} catch (Exception arg9) {
			arg9.printStackTrace();
		}

	}

	public String getProvince() {
		return this.province;
	}

	public String getCity() {
		return this.city;
	}

	public String getRegion() {
		return this.region;
	}

	public int getYear() {
		return this.year;
	}

	public int getMonth() {
		return this.month;
	}

	public int getDay() {
		return this.day;
	}

	public String getGender() {
		return this.qj;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public String toString() {
		return "省份：" + this.province + ",性别：" + this.qj + ",出生日期：" + this.birthday;
	}

	public static void main(String[] args) {
		String idcard = "";
		a ie = new a(idcard);
		System.out.println(ie.getProvince());
		System.out.println(ie.getRegister());
	}

	public int getAge() {
		return this.qk;
	}

	public void setAge(int age) {
		this.qk = age;
	}

	public String getRegister() {
		return this.register;
	}

	public void setRegister(String register) {
		this.register = register;
	}
}