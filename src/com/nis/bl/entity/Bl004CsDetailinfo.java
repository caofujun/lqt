package com.nis.bl.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Bl004CsDetailinfo extends BaseEntity implements Serializable, Cloneable {
	private String csmId;
	private String csdId;
	private String csdName;
	private Long flag;
	private String createMen;
	private Date lastAt;
	private String spCode;
	private String wbCode;
	private String bz;
	private String csmName;
	private String itemName;
	private Date fcDate;
	private String isChoose;

	public String getCsmId() {
		return this.csmId;
	}

	public void setCsmId(String csmId) {
		this.csmId = csmId;
	}

	public String getCsdId() {
		return this.csdId;
	}

	public void setCsdId(String csdId) {
		this.csdId = csdId;
	}

	public String getCsdName() {
		return this.csdName;
	}

	public void setCsdName(String csdName) {
		this.csdName = csdName;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public String getCreateMen() {
		return this.createMen;
	}

	public void setCreateMen(String createMen) {
		this.createMen = createMen;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
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

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCsmName() {
		return this.csmName;
	}

	public void setCsmName(String csmName) {
		this.csmName = csmName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getFcDate() {
		return this.fcDate;
	}

	public void setFcDate(Date fcDate) {
		this.fcDate = fcDate;
	}

	public String getIsChoose() {
		return this.isChoose;
	}

	public void setIsChoose(String isChoose) {
		this.isChoose = isChoose;
	}

	public Bl004CsDetailinfo clone() {
		Bl004CsDetailinfo bl004CsDetailinfo;
		try {
			bl004CsDetailinfo = (Bl004CsDetailinfo) super.clone();
		} catch (CloneNotSupportedException arg2) {
			bl004CsDetailinfo = new Bl004CsDetailinfo();
			arg2.printStackTrace();
		}

		return bl004CsDetailinfo;
	}
}