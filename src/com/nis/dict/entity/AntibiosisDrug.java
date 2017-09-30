package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("antibiosisDrug")
public class AntibiosisDrug extends BaseEntity implements Serializable {
	private String wbCode;
	private Long ifCommon;
	private Long flag;
	private Long ifReport;
	private String zjCode;
	private Date lastAt;
	private String drugId;
	private String drugName;
	private String drugEnname;
	private String drugTypeid;
	private String subClassid;
	private String drugTypeName;
	private String bhCode;
	private String spCode;
	private String showDrugTypeid;
	private String subclassName;

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public Long getIfCommon() {
		return this.ifCommon;
	}

	public void setIfCommon(Long ifCommon) {
		this.ifCommon = ifCommon;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Long getIfReport() {
		return this.ifReport;
	}

	public void setIfReport(Long ifReport) {
		this.ifReport = ifReport;
	}

	public String getZjCode() {
		return this.zjCode;
	}

	public void setZjCode(String zjCode) {
		this.zjCode = zjCode;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getDrugId() {
		return this.drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugEnname() {
		return this.drugEnname;
	}

	public void setDrugEnname(String drugEnname) {
		this.drugEnname = drugEnname;
	}

	public String getDrugTypeid() {
		return this.drugTypeid;
	}

	public void setDrugTypeid(String drugTypeid) {
		this.drugTypeid = drugTypeid;
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

	public String getShowDrugTypeid() {
		return this.showDrugTypeid;
	}

	public void setShowDrugTypeid(String showDrugTypeid) {
		this.showDrugTypeid = showDrugTypeid;
	}

	public String getDrugTypeName() {
		return this.drugTypeName;
	}

	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}

	public String toString() {
		return "AntibiosisDrug [wbCode=" + this.wbCode + ", ifCommon=" + this.ifCommon + ", flag=" + this.flag
				+ ", ifReport=" + this.ifReport + ", zjCode=" + this.zjCode + ", lastAt=" + this.lastAt + ", drugId="
				+ this.drugId + ", drugName=" + this.drugName + ", drugEnname=" + this.drugEnname + ", drugTypeid="
				+ this.drugTypeid + ", bhCode=" + this.bhCode + ", spCode=" + this.spCode + ", showDrugTypeid="
				+ this.showDrugTypeid + "]";
	}

	public String getSubClassid() {
		return this.subClassid;
	}

	public void setSubClassid(String subClassid) {
		this.subClassid = subClassid;
	}

	public String getSubclassName() {
		return this.subclassName;
	}

	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}
}