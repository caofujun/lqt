package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("zg002Byks")
public class Zg002Byks extends BaseEntity implements Serializable {
	private Integer ifbedicu;
	private String id;
	private String deptId;
	private String deptName;
	private String deptTypeid;
	private String deptTypename;
	private String standDeptId;
	private String hospId;
	private String zjCode;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Integer ifcaseoffice;
	private Integer ifenvoffice;
	private Integer ificu;
	private Integer ifchildoffice;
	private Integer ifoperoffice;
	private Integer ifHospdept;
	private Integer ifmzoffice;
	private Integer flag;
	private Date lastAt;
	private String tel;
	private Integer hosinfectBaseper;
	private Integer outhosinfectBaseper;

	public Integer getIfbedicu() {
		return this.ifbedicu;
	}

	public void setIfbedicu(Integer ifbedicu) {
		this.ifbedicu = ifbedicu;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptTypeid() {
		return this.deptTypeid;
	}

	public void setDeptTypeid(String deptTypeid) {
		this.deptTypeid = deptTypeid;
	}

	public String getDeptTypename() {
		return this.deptTypename;
	}

	public void setDeptTypename(String deptTypename) {
		this.deptTypename = deptTypename;
	}

	public String getStandDeptId() {
		return this.standDeptId;
	}

	public void setStandDeptId(String standDeptId) {
		this.standDeptId = standDeptId;
	}

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getZjCode() {
		return this.zjCode;
	}

	public void setZjCode(String zjCode) {
		this.zjCode = zjCode;
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

	public Integer getIfcaseoffice() {
		return this.ifcaseoffice;
	}

	public void setIfcaseoffice(Integer ifcaseoffice) {
		this.ifcaseoffice = ifcaseoffice;
	}

	public Integer getIfenvoffice() {
		return this.ifenvoffice;
	}

	public void setIfenvoffice(Integer ifenvoffice) {
		this.ifenvoffice = ifenvoffice;
	}

	public Integer getIficu() {
		return this.ificu;
	}

	public void setIficu(Integer ificu) {
		this.ificu = ificu;
	}

	public Integer getIfchildoffice() {
		return this.ifchildoffice;
	}

	public void setIfchildoffice(Integer ifchildoffice) {
		this.ifchildoffice = ifchildoffice;
	}

	public Integer getIfoperoffice() {
		return this.ifoperoffice;
	}

	public void setIfoperoffice(Integer ifoperoffice) {
		this.ifoperoffice = ifoperoffice;
	}

	public Integer getIfHospdept() {
		return this.ifHospdept;
	}

	public void setIfHospdept(Integer ifHospdept) {
		this.ifHospdept = ifHospdept;
	}

	public Integer getIfmzoffice() {
		return this.ifmzoffice;
	}

	public void setIfmzoffice(Integer ifmzoffice) {
		this.ifmzoffice = ifmzoffice;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getHosinfectBaseper() {
		return this.hosinfectBaseper;
	}

	public void setHosinfectBaseper(Integer hosinfectBaseper) {
		this.hosinfectBaseper = hosinfectBaseper;
	}

	public Integer getOuthosinfectBaseper() {
		return this.outhosinfectBaseper;
	}

	public void setOuthosinfectBaseper(Integer outhosinfectBaseper) {
		this.outhosinfectBaseper = outhosinfectBaseper;
	}
}