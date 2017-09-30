package com.nis.user.entity;

import java.io.Serializable;

public class Sms4RandomNum implements Serializable {
	private String randomNum;
	private int count;

	public String getRandomNum() {
		return this.randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}