package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class NyBbDict extends BaseEntity implements Serializable {
	private String grtype;
	private String zdyj;
	private String pcqk;
	private String vcmemo;
	private String infectCode;
	private String infectName;
	private String elementId;
	private Long typeId;
	private String bbid;
	private String bbmc;
	private String dybw;

	public String getGrtype() {
		return this.grtype;
	}

	public void setGrtype(String grtype) {
		this.grtype = grtype;
	}

	public String getZdyj() {
		return this.zdyj;
	}

	public void setZdyj(String zdyj) {
		this.zdyj = zdyj;
	}

	public String getPcqk() {
		return this.pcqk;
	}

	public void setPcqk(String pcqk) {
		this.pcqk = pcqk;
	}

	public String getVcmemo() {
		return this.vcmemo;
	}

	public void setVcmemo(String vcmemo) {
		this.vcmemo = vcmemo;
	}

	public String getInfectCode() {
		return this.infectCode;
	}

	public void setInfectCode(String infectCode) {
		this.infectCode = infectCode;
	}

	public String getInfectName() {
		return this.infectName;
	}

	public void setInfectName(String infectName) {
		this.infectName = infectName;
	}

	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getBbid() {
		return this.bbid;
	}

	public void setBbid(String bbid) {
		this.bbid = bbid;
	}

	public String getBbmc() {
		return this.bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	public String getDybw() {
		return this.dybw;
	}

	public void setDybw(String dybw) {
		this.dybw = dybw;
	}
}