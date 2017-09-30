package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("icd9")
public class Icd9 extends BaseEntity implements Serializable {
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
	private String showOpesysId;
	private String showOpepartKindid;
	private String showImpOpeId;

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

	public String getShowOpesysId() {
		return this.showOpesysId;
	}

	public void setShowOpesysId(String showOpesysId) {
		this.showOpesysId = showOpesysId;
	}

	public String getShowOpepartKindid() {
		return this.showOpepartKindid;
	}

	public void setShowOpepartKindid(String showOpepartKindid) {
		this.showOpepartKindid = showOpepartKindid;
	}

	public String getShowImpOpeId() {
		return this.showImpOpeId;
	}

	public void setShowImpOpeId(String showImpOpeId) {
		this.showImpOpeId = showImpOpeId;
	}

	public String toString() {
		return "Icd9 [icdId=" + this.icdId + ", operId=" + this.operId + ", operaCnname=" + this.operaCnname
				+ ", operEnName=" + this.operEnName + ", bhCode=" + this.bhCode + ", spCode=" + this.spCode
				+ ", wbCode=" + this.wbCode + ", opesysId=" + this.opesysId + ", opepartKindid=" + this.opepartKindid
				+ ", memo=" + this.memo + ", flag=" + this.flag + ", lastAt=" + this.lastAt + ", impOpeId="
				+ this.impOpeId + ", showOpesysId=" + this.showOpesysId + ", showOpepartKindid="
				+ this.showOpepartKindid + ", showImpOpeId=" + this.showImpOpeId + "]";
	}
}