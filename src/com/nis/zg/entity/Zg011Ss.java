package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Zg011Ss extends BaseEntity implements Serializable {
	private String icdId;
	private String operId;
	private String operaCnname;
	private String operEnName;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private String opesysId;
	private String opepartKindid;
	private String memo;
	private Long flag;
	private Date lastAt;
	private String impOpeId;
	private String impOpeName;
	private String isImp;

	public String getIcdId() {
		return this.icdId;
	}

	public void setIcdId(String icdId) {
		this.icdId = icdId;
	}

	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getOperaCnname() {
		return this.operaCnname;
	}

	public void setOperaCnname(String operaCnname) {
		this.operaCnname = operaCnname;
	}

	public String getOperEnName() {
		return this.operEnName;
	}

	public void setOperEnName(String operEnName) {
		this.operEnName = operEnName;
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

	public String getOpesysId() {
		return this.opesysId;
	}

	public void setOpesysId(String opesysId) {
		this.opesysId = opesysId;
	}

	public String getOpepartKindid() {
		return this.opepartKindid;
	}

	public void setOpepartKindid(String opepartKindid) {
		this.opepartKindid = opepartKindid;
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

	public String getImpOpeId() {
		return this.impOpeId;
	}

	public void setImpOpeId(String impOpeId) {
		this.impOpeId = impOpeId;
	}

	public String getIsImp() {
		return this.isImp;
	}

	public void setIsImp(String isImp) {
		this.isImp = isImp;
	}

	public String getImpOpeName() {
		return this.impOpeName;
	}

	public void setImpOpeName(String impOpeName) {
		this.impOpeName = impOpeName;
	}
}