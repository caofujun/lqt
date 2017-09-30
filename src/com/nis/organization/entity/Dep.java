package com.nis.organization.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("dep")
public class Dep extends BaseEntity implements Serializable {
	private String id;
	private String deptId;
	private String deptName;
	private String deptTypeId;
	private String deptTypeName;
	private String standDeptId;
	private String hospId;
	private String hospName;
	private String zjCode;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Long ifcaseoffice;
	private Long ifenvoffice;
	private Long ificu;
	private Long ifbedicu;
	private Long ifchildoffice;
	private Long ifoperoffice;
	private Long ifhospdept;
	private Long ifmzoffice;
	private Long iffocus;
	private Long flag;
	private Date lastAt;
	private String tel;
	private Long hosinfectBaseper;
	private Long outhosinfectBaseper;
	private String isHavegrant;
	private Long showOrder;
	private String showDesc;
	private String showStandDeptId;
	private String deptClassify;
	private Integer isCaseOrEnvo;
	private String deptClassifyName;
	private String chargeManId;
	private Double baseInfect;
	private List<Dep> deptList;
	private String depPorp;

	public String getDepPorp() {
		return this.depPorp;
	}

	public void setDepPorp(String depPorp) {
		this.depPorp = depPorp;
	}

	public String getChargeManId() {
		return this.chargeManId;
	}

	public void setChargeManId(String chargeManId) {
		this.chargeManId = chargeManId;
	}

	public String getShowStandDeptId() {
		return this.showStandDeptId;
	}

	public void setShowStandDeptId(String showStandDeptId) {
		this.showStandDeptId = showStandDeptId;
	}

	public String getShowDesc() {
		return this.showDesc;
	}

	public void setShowDesc(String showDesc) {
		this.showDesc = showDesc;
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

	public String getDeptTypeId() {
		return this.deptTypeId;
	}

	public void setDeptTypeId(String deptTypeId) {
		this.deptTypeId = deptTypeId;
	}

	public String getDeptTypeName() {
		return this.deptTypeName;
	}

	public void setDeptTypeName(String deptTypeName) {
		this.deptTypeName = deptTypeName;
	}

	public String getStandDeptId() {
		return this.standDeptId;
	}

	public void setStandDeptId(String standDeptId) {
		this.standDeptId = standDeptId;
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

	public Long getIfcaseoffice() {
		return this.ifcaseoffice;
	}

	public void setIfcaseoffice(Long ifcaseoffice) {
		this.ifcaseoffice = ifcaseoffice;
	}

	public Long getIfenvoffice() {
		return this.ifenvoffice;
	}

	public void setIfenvoffice(Long ifenvoffice) {
		this.ifenvoffice = ifenvoffice;
	}

	public Long getIficu() {
		return this.ificu;
	}

	public void setIficu(Long ificu) {
		this.ificu = ificu;
	}

	public Long getIfchildoffice() {
		return this.ifchildoffice;
	}

	public void setIfchildoffice(Long ifchildoffice) {
		this.ifchildoffice = ifchildoffice;
	}

	public Long getIfoperoffice() {
		return this.ifoperoffice;
	}

	public void setIfoperoffice(Long ifoperoffice) {
		this.ifoperoffice = ifoperoffice;
	}

	public Long getIfhospdept() {
		return this.ifhospdept;
	}

	public void setIfhospdept(Long ifhospdept) {
		this.ifhospdept = ifhospdept;
	}

	public Long getIfmzoffice() {
		return this.ifmzoffice;
	}

	public void setIfmzoffice(Long ifmzoffice) {
		this.ifmzoffice = ifmzoffice;
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

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getHosinfectBaseper() {
		return this.hosinfectBaseper;
	}

	public void setHosinfectBaseper(Long hosinfectBaseper) {
		this.hosinfectBaseper = hosinfectBaseper;
	}

	public Long getOuthosinfectBaseper() {
		return this.outhosinfectBaseper;
	}

	public void setOuthosinfectBaseper(Long outhosinfectBaseper) {
		this.outhosinfectBaseper = outhosinfectBaseper;
	}

	public String getIsHavegrant() {
		return this.isHavegrant;
	}

	public void setIsHavegrant(String isHavegrant) {
		this.isHavegrant = isHavegrant;
	}

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public Long getIfbedicu() {
		return this.ifbedicu;
	}

	public void setIfbedicu(Long ifbedicu) {
		this.ifbedicu = ifbedicu;
	}

	public Long getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}

	public Long getIffocus() {
		return this.iffocus;
	}

	public void setIffocus(Long iffocus) {
		this.iffocus = iffocus;
	}

	public Integer getIsCaseOrEnvo() {
		return this.isCaseOrEnvo;
	}

	public void setIsCaseOrEnvo(Integer isCaseOrEnvo) {
		this.isCaseOrEnvo = isCaseOrEnvo;
	}

	public String getDeptClassify() {
		return this.deptClassify;
	}

	public void setDeptClassify(String deptClassify) {
		this.deptClassify = deptClassify;
	}

	public String getDeptClassifyName() {
		return this.deptClassifyName;
	}

	public void setDeptClassifyName(String deptClassifyName) {
		this.deptClassifyName = deptClassifyName;
	}

	public Double getBaseInfect() {
		return this.baseInfect;
	}

	public void setBaseInfect(Double baseInfect) {
		this.baseInfect = baseInfect;
	}

	public List<Dep> getDeptList() {
		return this.deptList;
	}

	public void setDeptList(List<Dep> deptList) {
		this.deptList = deptList;
	}

	public String getHospName() {
		return this.hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public String toString() {
		return "Dep [id=" + this.id + ", deptId=" + this.deptId + ", deptName=" + this.deptName + ", deptTypeId="
				+ this.deptTypeId + ", deptTypeName=" + this.deptTypeName + ", standDeptId=" + this.standDeptId
				+ ", hospId=" + this.hospId + ", hospName=" + this.hospName + ", zjCode=" + this.zjCode + ", bhCode="
				+ this.bhCode + ", spCode=" + this.spCode + ", wbCode=" + this.wbCode + ", ifcaseoffice="
				+ this.ifcaseoffice + ", ifenvoffice=" + this.ifenvoffice + ", ificu=" + this.ificu + ", ifbedicu="
				+ this.ifbedicu + ", ifchildoffice=" + this.ifchildoffice + ", ifoperoffice=" + this.ifoperoffice
				+ ", ifhospdept=" + this.ifhospdept + ", ifmzoffice=" + this.ifmzoffice + ", iffocus=" + this.iffocus
				+ ", flag=" + this.flag + ", lastAt=" + this.lastAt + ", tel=" + this.tel + ", hosinfectBaseper="
				+ this.hosinfectBaseper + ", outhosinfectBaseper=" + this.outhosinfectBaseper + ", isHavegrant="
				+ this.isHavegrant + ", showOrder=" + this.showOrder + ", showDesc=" + this.showDesc
				+ ", showStandDeptId=" + this.showStandDeptId + ", deptClassify=" + this.deptClassify
				+ ", isCaseOrEnvo=" + this.isCaseOrEnvo + ", deptClassifyName=" + this.deptClassifyName
				+ ", chargeManId=" + this.chargeManId + ", baseInfect=" + this.baseInfect + ", deptList="
				+ this.deptList + ", depPorp=" + this.depPorp + "]";
	}
}