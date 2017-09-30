package com.nis.analysis.model;

import java.util.ArrayList;
import java.util.List;

public class a {
	private boolean success = false;
	private List<String> Y = new ArrayList();
	private String info = "";
	private int Z = 0;
	private int ab = 0;
	private int ac = 0;

	public boolean b() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getTotals() {
		return this.Z;
	}

	public void setTotals(int totals) {
		this.Z = totals;
	}

	public int getSucTotals() {
		return this.ab;
	}

	public void setSucTotals(int sucTotals) {
		this.ab = sucTotals;
	}

	public int getErrTotals() {
		return this.ac;
	}

	public void setErrTotals(int errTotals) {
		this.ac = errTotals;
	}

	public List<String> getErrMsgList() {
		return this.Y;
	}

	public void setErrMsgList(List<String> errMsgList) {
		this.Y = errMsgList;
	}
}