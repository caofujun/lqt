package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientVisit")
public class JkPatientVisit extends BaseEntity implements Serializable {
	private String zyid;
	private Integer visitId;
	private String patientId;
	private String patientName;
	private String sex;
	private Date birthDate;
	private String weight;
	private String idCardId;
	private String age;
	private String ageUnit;
	private String deptCode;
	private String deptName;
	private Date inHospAt;
	private String inDeptId;
	private String inDeptName;
	private String outDeptId;
	private String outDeptName;
	private Date outAt;
	private String bedNo;
	private String chargeDrId;
	private String chargeDrName;
	private String chargeNrId;
	private String chargeNrName;
	private String address;
	private String tel;
	private String birthPlace;
	private String workAddr;
	private String marriage;
	private String relationship;
	private String lxrName;
	private String lxrPhone;
	private String nation;
	private String education;
	private String job;
	private Integer inDays;
	private Double cost;
	private String memo;
	private String outcome;
	private Date updTime;
	private Long updFlag;

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getIdCardId() {
		return this.idCardId;
	}

	public void setIdCardId(String idCardId) {
		this.idCardId = idCardId;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getInHospAt() {
		return this.inHospAt;
	}

	public void setInHospAt(Date inHospAt) {
		this.inHospAt = inHospAt;
	}

	public String getInDeptId() {
		return this.inDeptId;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}

	public String getInDeptName() {
		return this.inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public String getOutDeptId() {
		return this.outDeptId;
	}

	public void setOutDeptId(String outDeptId) {
		this.outDeptId = outDeptId;
	}

	public String getOutDeptName() {
		return this.outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	public Date getOutAt() {
		return this.outAt;
	}

	public void setOutAt(Date outAt) {
		this.outAt = outAt;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getChargeDrId() {
		return this.chargeDrId;
	}

	public void setChargeDrId(String chargeDrId) {
		this.chargeDrId = chargeDrId;
	}

	public String getChargeDrName() {
		return this.chargeDrName;
	}

	public void setChargeDrName(String chargeDrName) {
		this.chargeDrName = chargeDrName;
	}

	public String getChargeNrId() {
		return this.chargeNrId;
	}

	public void setChargeNrId(String chargeNrId) {
		this.chargeNrId = chargeNrId;
	}

	public String getChargeNrName() {
		return this.chargeNrName;
	}

	public void setChargeNrName(String chargeNrName) {
		this.chargeNrName = chargeNrName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getWorkAddr() {
		return this.workAddr;
	}

	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getLxrName() {
		return this.lxrName;
	}

	public void setLxrName(String lxrName) {
		this.lxrName = lxrName;
	}

	public String getLxrPhone() {
		return this.lxrPhone;
	}

	public void setLxrPhone(String lxrPhone) {
		this.lxrPhone = lxrPhone;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getInDays() {
		return this.inDays;
	}

	public void setInDays(Integer inDays) {
		this.inDays = inDays;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOutcome() {
		return this.outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Long getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Long updFlag) {
		this.updFlag = updFlag;
	}
}