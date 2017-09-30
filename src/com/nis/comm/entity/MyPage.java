package com.nis.comm.entity;

import java.util.ArrayList;
import java.util.List;

public class MyPage<T> {
	private String result = "success";
	private int page;
	private int size;
	private int totalPage;
	private int total;
	private List<T> rows;
	private List<T> footer;

	public MyPage(int page, int size, int total, List<T> rows) {
		this.page = page;
		this.size = size;
		this.total = total;
		this.rows = (List) (rows == null ? new ArrayList() : rows);
		this.totalPage = this.getTotalPage();
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		if (this.size > 0) {
			this.totalPage = (int) Math.ceil((double) this.total / (double) this.size);
		} else {
			this.totalPage = 1;
		}

		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getRows() {
		return this.rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<T> getFooter() {
		return this.footer;
	}

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}