package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("xn015Trlyjl")
public class Xn015Trlyjl extends BaseEntity implements Serializable {
	private String testOrderNo;
	private String pathoCode;
	private String pathoName;
	private String pathogenSn;
	private String antiCode;
	private String antiName;
	private Date recordDt;

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public String getPathoCode() {
		return this.pathoCode;
	}

	public void setPathoCode(String pathoCode) {
		this.pathoCode = pathoCode;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public String getAntiCode() {
		return this.antiCode;
	}

	public void setAntiCode(String antiCode) {
		this.antiCode = antiCode;
	}

	public String getAntiName() {
		return this.antiName;
	}

	public void setAntiName(String antiName) {
		this.antiName = antiName;
	}

	public Date getRecordDt() {
		return this.recordDt;
	}

	public void setRecordDt(Date recordDt) {
		this.recordDt = recordDt;
	}
}