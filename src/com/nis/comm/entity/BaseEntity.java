package com.nis.comm.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -4495987129630008126L;
	public static Integer defaultPage = Integer.valueOf(1);
	public static Integer defaultSize = Integer.valueOf(10);
	private Integer page;
	private Integer size;
	private Integer rows;
	private Integer firstPage;
	private Integer orclBegNum;
	private Integer orclEndNum;
	private Date queryStartDate;
	private Date queryEndDate;
	private String searchString;
	private int isInHospDateFlag;
	private int isOutHospDateFlag;
	private String searchOpt;

	public int getIsInHospDateFlag() {
		return this.isInHospDateFlag;
	}

	public void setIsInHospDateFlag(int isInHospDateFlag) {
		this.isInHospDateFlag = isInHospDateFlag;
	}

	public int getIsOutHospDateFlag() {
		return this.isOutHospDateFlag;
	}

	public void setIsOutHospDateFlag(int isOutHospDateFlag) {
		this.isOutHospDateFlag = isOutHospDateFlag;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		if (size == null) {
			size = defaultSize;
		} else if (size.intValue() < 0) {
			size = null;
		}

		this.size = size;
	}

	public Integer getRows() {
		return this.rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
		if (rows != null) {
			this.size = rows;
		}

	}

	public Integer getFirstPage() {
		return this.firstPage != null
				? this.firstPage
				: (this.firstPage == null && this.page != null && this.size != null
						? Integer.valueOf((this.page.intValue() - 1) * this.size.intValue())
						: this.firstPage);
	}

	public void setFirstPage(Integer firstPage) {
		if (firstPage != null) {
			this.firstPage = firstPage;
		} else if (firstPage == null && this.page != null && this.size != null) {
			this.firstPage = Integer.valueOf((this.page.intValue() - 1) * this.size.intValue());
		}

	}

	public Integer getOrclBegNum() {
		if (this.orclBegNum == null && this.page != null && this.size != null) {
			this.orclBegNum = Integer.valueOf((this.page.intValue() - 1) * this.size.intValue() + 1);
		}

		return this.orclBegNum;
	}

	public void setOrclBegNum(Integer orclBegNum) {
		this.orclBegNum = orclBegNum;
	}

	public Integer getOrclEndNum() {
		if (this.orclEndNum == null && this.page != null && this.size != null) {
			this.orclEndNum = Integer.valueOf(this.page.intValue() * this.size.intValue());
		}

		return this.orclEndNum;
	}

	public void setOrclEndNum(Integer orclEndNum) {
		this.orclEndNum = orclEndNum;
	}

	public String getSearchString() {
		return this.searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Date getQueryStartDate() {
		return this.queryStartDate;
	}

	public void setQueryStartDate(Date queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public Date getQueryEndDate() {
		return this.queryEndDate;
	}

	public void setQueryEndDate(Date queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	public String getSearchOpt() {
		return this.searchOpt;
	}

	public void setSearchOpt(String searchOpt) {
		this.searchOpt = searchOpt;
	}
}