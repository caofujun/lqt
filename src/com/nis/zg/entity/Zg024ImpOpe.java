package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Zg024ImpOpe extends BaseEntity implements Serializable {
	private String impOpeId;
	private String impOpeName;
	private String impOpeKey;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private String memo;
	private Long flag;
	private Date lastAt;

	public String getImpOpeId() {
		return this.impOpeId;
	}

	public void setImpOpeId(String impOpeId) {
		this.impOpeId = impOpeId;
	}

	public String getImpOpeName() {
		return this.impOpeName;
	}

	public void setImpOpeName(String impOpeName) {
		this.impOpeName = impOpeName;
	}

	public String getImpOpeKey() {
		return this.impOpeKey;
	}

	public void setImpOpeKey(String impOpeKey) {
		this.impOpeKey = impOpeKey;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}
}